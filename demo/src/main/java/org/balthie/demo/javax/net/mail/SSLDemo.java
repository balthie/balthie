package org.balthie.demo.javax.net.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import com.sun.mail.util.MailSSLSocketFactory;

public class SSLDemo
{
    // 邮件发送协议
    private final static String PROTOCOL = "smtp";
    
    // SMTP邮件服务器
    private final static String HOST = "smtp.126.com";
    
    // SMTP邮件服务器默认端口
    private final static String PORT = "465";
    
    // 是否要求身份认证
    private final static String IS_AUTH = "true";
    
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final static String IS_ENABLED_DEBUG_MOD = "true";
    
    // 发件人
    private static String from = "xiangwei@viomi.net";
    
    // 收件人
    private static String to = "xiangwei@viomi.net";
    
    // 初始化连接邮件服务器的会话信息
    private static Properties props = null;
    
    static
    {
        props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.port", PORT);
        props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.debug", "true");
        props.setProperty("mail.smtp.auth", IS_AUTH);
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.starttls.enable", "true");
        // props.setProperty("mail.smtp.user", "xiangwei@viomi.net");
        // props.setProperty("mail.smtp.password", "7ujm6YHN5tgb");
    }
    
    public static void main(String[] args) throws Exception
    {
        System.setProperty("https.protocols", "TLSv1");
        // System.setProperty("https.protocols", "SSLv3");
        // 发送文本邮件
        sendTextQQEmail();
        
        // 发送简单的html邮件
        // sendHtmlEmail();
        
        // 发送带内嵌图片的HTML邮件
        // sendHtmlWithInnerImageEmail();
        
        // 发送混合组合邮件
        // sendMultipleEmail();
        
        // 发送已经生成的eml邮件
        // sendMailForEml();
    }
    
    /**
     * 发送简单的文本邮件
     */
    public static void sendText126Email() throws Exception
    {
        props.setProperty("mail.smtp.host", "smtp.126.com");
        props.setProperty("mail.smtp.port", "465");
        
        // 发件人
        String from = "xiangwei@viomi.net";
        
        // 收件人
        String to = "xiangwei@viomi.net";
        
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);
        // 创建Session实例对象
        Session session = Session.getDefaultInstance(props);
        
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(from));
        // 设置邮件主题
        message.setSubject("使用javamail发送简单文本邮件");
        // 设置收件人
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置纯文本内容为邮件正文
        message.setText("使用SMTP协议发送文本邮件测试!!!");
        // 保存并生成最终的邮件内容
        message.saveChanges();
        
        // 获得Transport实例对象
        Transport transport = session.getTransport();
        // 打开连接
        transport.connect("balthie@126.com", "4rfv5TGB6yhn");
        // 将message对象传递给transport对象，将邮件发送出去
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
    }
    
    public static void sendTextQQEmail() throws Exception
    {
        // 腾讯企业邮箱 SSL 握手失败： http://blog.csdn.net/tzs_1041218129/article/details/52195922
        // 这个问题是jdk导致的，jdk1.8里面有一个jce的包，安全性机制导致的访问https会报错，官网上有替代的jar包，如果替换掉就可以了。问题的解决方法还可以就是在整个项目中把你的jdk换成是1.7去，同样也可以解决这个我问题。
        // http://stackoverflow.com/questions/6353849/received-fatal-alert-handshake-failure-through-sslhandshakeexception
        // JCE是java加密扩展包，由于美国出口限制规定，JCE对部分国家是限制出口的，致使其加密长度有所缩减，例如，DES算法因受到军事出口限制，目前仅提供56位的密钥长度，而实际安全要求至少要128位。对于出口限制，SUN公司通过权限文件做了相应限制（local_policy.jar和US_export_policy.jar），而UnlimitedJCEPolicyJDK7就是用来减少相关限制的相关文件。
        props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        props.setProperty("mail.smtp.port", "465");
        
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);
        // 创建Session实例对象
        Session session = Session.getDefaultInstance(props);
        
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(from));
        // 设置邮件主题
        message.setSubject("使用javamail发送简单文本邮件");
        // 设置收件人
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置纯文本内容为邮件正文
        message.setText("使用SMTP协议发送文本邮件测试!!!");
        // 保存并生成最终的邮件内容
        message.saveChanges();
        
        // 获得Transport实例对象
        Transport transport = session.getTransport();
        // 打开连接
        transport.connect("xiangwei@viomi.net", "7ujm5TGB6yhn");
        // 将message对象传递给transport对象，将邮件发送出去
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
    }
    
    /**
     * 发送简单的html邮件
     */
    public static void sendHtmlEmail() throws Exception
    {
        // 创建Session实例对象
        Session session = Session.getInstance(props, new MyAuthenticator());
        
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置邮件主题
        message.setSubject("html邮件主题");
        // 设置发送人
        message.setFrom(new InternetAddress(from));
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置收件人
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
        // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk
        message.setContent("<span style='color:red;'>html邮件测试...</span>", "text/html;charset=gbk");
        
        // 保存并生成最终的邮件内容
        message.saveChanges();
        
        // 发送邮件
        Transport.send(message);
    }
    
    /**
     * 发送带内嵌图片的HTML邮件
     */
    public static void sendHtmlWithInnerImageEmail() throws MessagingException
    {
        // 创建Session实例对象
        Session session = Session.getDefaultInstance(props, new MyAuthenticator());
        
        // 创建邮件内容
        MimeMessage message = new MimeMessage(session);
        // 邮件主题,并指定编码格式
        message.setSubject("带内嵌图片的HTML邮件", "utf-8");
        // 发件人
        message.setFrom(new InternetAddress(from));
        // 收件人
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
        // 抄送
        message.setRecipient(RecipientType.CC, new InternetAddress("java_test@sohu.com"));
        // 密送 (不会在邮件收件人名单中显示出来)
        message.setRecipient(RecipientType.BCC, new InternetAddress("417067629@qq.com"));
        // 发送时间
        message.setSentDate(new Date());
        
        // 创建一个MIME子类型为“related”的MimeMultipart对象
        MimeMultipart mp = new MimeMultipart("related");
        // 创建一个表示正文的MimeBodyPart对象，并将它加入到前面创建的MimeMultipart对象中
        MimeBodyPart htmlPart = new MimeBodyPart();
        mp.addBodyPart(htmlPart);
        // 创建一个表示图片资源的MimeBodyPart对象，将将它加入到前面创建的MimeMultipart对象中
        MimeBodyPart imagePart = new MimeBodyPart();
        mp.addBodyPart(imagePart);
        
        // 将MimeMultipart对象设置为整个邮件的内容
        message.setContent(mp);
        
        // 设置内嵌图片邮件体
        DataSource ds = new FileDataSource(new File("resource/firefoxlogo.png"));
        DataHandler dh = new DataHandler(ds);
        imagePart.setDataHandler(dh);
        imagePart.setContentID("firefoxlogo.png"); // 设置内容编号,用于其它邮件体引用
        
        // 创建一个MIME子类型为"alternative"的MimeMultipart对象，并作为前面创建的htmlPart对象的邮件内容
        MimeMultipart htmlMultipart = new MimeMultipart("alternative");
        // 创建一个表示html正文的MimeBodyPart对象
        MimeBodyPart htmlBodypart = new MimeBodyPart();
        // 其中cid=androidlogo.gif是引用邮件内部的图片，即imagePart.setContentID("androidlogo.gif");方法所保存的图片
        htmlBodypart.setContent("<span style='color:red;'>这是带内嵌图片的HTML邮件哦！！！<img src=\"cid:firefoxlogo.png\" /></span>",
                "text/html;charset=utf-8");
        htmlMultipart.addBodyPart(htmlBodypart);
        htmlPart.setContent(htmlMultipart);
        
        // 保存并生成最终的邮件内容
        message.saveChanges();
        
        // 发送邮件
        Transport.send(message);
    }
    
    /**
     * 发送带内嵌图片、附件、多收件人(显示邮箱姓名)、邮件优先级、阅读回执的完整的HTML邮件
     */
    public static void sendMultipleEmail() throws Exception
    {
        String charset = "utf-8"; // 指定中文编码格式
        // 创建Session实例对象
        Session session = Session.getInstance(props, new MyAuthenticator());
        
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置主题
        message.setSubject("使用JavaMail发送混合组合类型的邮件测试");
        // 设置发送人
        message.setFrom(new InternetAddress(from, "新浪测试邮箱", charset));
        // 设置收件人
        message.setRecipients(RecipientType.TO,
                new Address[] {
                        // 参数1：邮箱地址，参数2：姓名（在客户端收件只显示姓名，而不显示邮件地址），参数3：姓名中文字符串编码
                        new InternetAddress("java_test@sohu.com", "张三_sohu", charset),
                        new InternetAddress("xyang0917@163.com", "李四_163", charset),
                });
        // 设置抄送
        message.setRecipient(RecipientType.CC, new InternetAddress("xyang0917@gmail.com", "王五_gmail", charset));
        // 设置密送
        message.setRecipient(RecipientType.BCC, new InternetAddress("xyang0917@qq.com", "赵六_QQ", charset));
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置回复人(收件人回复此邮件时,默认收件人)
        message.setReplyTo(InternetAddress.parse("\"" + MimeUtility.encodeText("田七") + "\" <417067629@qq.com>"));
        // 设置优先级(1:紧急 3:普通 5:低)
        message.setHeader("X-Priority", "1");
        // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读)
        message.setHeader("Disposition-Notification-To", from);
        
        // 创建一个MIME子类型为"mixed"的MimeMultipart对象，表示这是一封混合组合类型的邮件
        MimeMultipart mailContent = new MimeMultipart("mixed");
        message.setContent(mailContent);
        
        // 附件
        MimeBodyPart attach1 = new MimeBodyPart();
        MimeBodyPart attach2 = new MimeBodyPart();
        // 内容
        MimeBodyPart mailBody = new MimeBodyPart();
        
        // 将附件和内容添加到邮件当中
        mailContent.addBodyPart(attach1);
        mailContent.addBodyPart(attach2);
        mailContent.addBodyPart(mailBody);
        
        // 附件1(利用jaf框架读取数据源生成邮件体)
        DataSource ds1 = new FileDataSource("resource/Earth.bmp");
        DataHandler dh1 = new DataHandler(ds1);
        attach1.setFileName(MimeUtility.encodeText("Earth.bmp"));
        attach1.setDataHandler(dh1);
        
        // 附件2
        DataSource ds2 = new FileDataSource("resource/如何学好C语言.txt");
        DataHandler dh2 = new DataHandler(ds2);
        attach2.setDataHandler(dh2);
        attach2.setFileName(MimeUtility.encodeText("如何学好C语言.txt"));
        
        // 邮件正文(内嵌图片+html文本)
        MimeMultipart body = new MimeMultipart("related"); // 邮件正文也是一个组合体,需要指明组合关系
        mailBody.setContent(body);
        
        // 邮件正文由html和图片构成
        MimeBodyPart imgPart = new MimeBodyPart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        body.addBodyPart(imgPart);
        body.addBodyPart(htmlPart);
        
        // 正文图片
        DataSource ds3 = new FileDataSource("resource/firefoxlogo.png");
        DataHandler dh3 = new DataHandler(ds3);
        imgPart.setDataHandler(dh3);
        imgPart.setContentID("firefoxlogo.png");
        
        // html邮件内容
        MimeMultipart htmlMultipart = new MimeMultipart("alternative");
        htmlPart.setContent(htmlMultipart);
        MimeBodyPart htmlContent = new MimeBodyPart();
        htmlContent.setContent(
                "<span style='color:red'>这是我自己用java mail发送的邮件哦！" +
                        "<img src='cid:firefoxlogo.png' /></span>",
                "text/html;charset=gbk");
        htmlMultipart.addBodyPart(htmlContent);
        
        // 保存邮件内容修改
        message.saveChanges();
        
        /*
         * File eml = buildEmlFile(message);
         * sendMailForEml(eml);
         */
        
        // 发送邮件
        Transport.send(message);
    }
    
    /**
     * 将邮件内容生成eml文件
     * 
     * @param message
     *            邮件内容
     */
    public static File buildEmlFile(Message message) throws MessagingException, FileNotFoundException, IOException
    {
        File file = new File("c:\\" + MimeUtility.decodeText(message.getSubject()) + ".eml");
        message.writeTo(new FileOutputStream(file));
        return file;
    }
    
    /**
     * 发送本地已经生成好的email文件
     */
    public static void sendMailForEml(File eml) throws Exception
    {
        // 获得邮件会话
        Session session = Session.getInstance(props, new MyAuthenticator());
        // 获得邮件内容,即发生前生成的eml文件
        InputStream is = new FileInputStream(eml);
        MimeMessage message = new MimeMessage(session, is);
        // 发送邮件
        Transport.send(message);
    }
    
    /**
     * 向邮件服务器提交认证信息
     */
    static class MyAuthenticator extends Authenticator
    {
        
        private String username = "xyang0917";
        
        private String password = "123456abc";
        
        public MyAuthenticator()
        {
            super();
        }
        
        public MyAuthenticator(String username, String password)
        {
            super();
            this.username = username;
            this.password = password;
        }
        
        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            
            return new PasswordAuthentication(username, password);
        }
    }
}
