package org.balthie.demo.javax.net.mail;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

public class MailTest
{
    
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        
        // Get a Properties object
        
        Properties props = System.getProperties();
        /*
         * mail.store.protocol=imaps
         * mail.imap.socketFactory.class=javax.net.ssl.SSLSocketFactory
         * mail.imap.socketFactory.fallback=false
         * mail.imaps.host=imap.qq.com
         * mail.imaps.port=993
         * mail.imap.auth.plain.disable=true
         * mail.imap.auth.login.disable=true
         * mail.smtp.auth=true
         * mail.smtp.debug=true
         * mail.smtp.host=smtp.qq.com
         * mail.smtp.port=465
         * mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
         * mail.smtp.socketFactory.fallback=false
         * mail.smtp.socketFactory.port=465
         * mail.smtp.starttls.enable=true
         * mail.smtp.user=ssssdafasdfad
         */
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imaps.host", "imap.qq.com");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imap.auth.plain.disable", "true");
        props.setProperty("mail.imap.auth.login.disable", "true");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.debug", "true");
        props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.user", "xiangwei@viomi.net");
        props.setProperty("mail.smtp.password", "7ujm6YHN5tgb");
        
        // 以下步骤跟一般的JavaMail操作相同
        
        Session session = Session.getDefaultInstance(props, null);
        
        // 请将红色部分对应替换成你的邮箱帐号和密码
        
        URLName urln = new URLName("pop3", "pop.gmail.com", 995, null,
        
        "邮箱帐号", "邮箱密码");
        
        Store store = session.getStore(urln);
        
        Folder inbox = null;
        
        try
        {
            
            store.connect();
            
            inbox = store.getFolder("INBOX");
            
            inbox.open(Folder.READ_ONLY);
            
            FetchProfile profile = new FetchProfile();
            
            profile.add(FetchProfile.Item.ENVELOPE);
            
            Message[] messages = inbox.getMessages();
            
            inbox.fetch(messages, profile);
            
            System.out.println("收件箱的邮件数：" + messages.length);
            
            for(int i = 0; i < messages.length; i++)
            {
                
                // 邮件发送者
                
                String from = decodeText(messages[i].getFrom()[0].toString());
                
                InternetAddress ia = new InternetAddress(from);
                
                System.out.println("FROM:" + ia.getPersonal() + '(' + ia.getAddress() + ')');
                
                // 邮件标题
                
                System.out.println("TITLE:" + messages[i].getSubject());
                
                // 邮件大小
                
                System.out.println("SIZE:" + messages[i].getSize());
                
                // 邮件发送时间
                
                System.out.println("DATE:" + messages[i].getSentDate());
                
            }
            
        }
        finally
        {
            
            try
            {
                
                inbox.close(false);
                
            }
            catch (Exception e)
            {
            }
            
            try
            {
                
                store.close();
                
            }
            catch (Exception e)
            {
            }
            
        }
        
    }
    
    protected static String decodeText(String text)
            throws UnsupportedEncodingException
    {
        
        if(text == null)
        
        return null;
        
        if(text.startsWith("=?GB") || text.startsWith("=?gb"))
            
            text = MimeUtility.decodeText(text);
            
        else
        
        text = new String(text.getBytes("ISO8859_1"));
        
        return text;
    }
}
