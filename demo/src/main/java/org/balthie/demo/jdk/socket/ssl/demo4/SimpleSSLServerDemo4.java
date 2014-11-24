package org.balthie.demo.jdk.socket.ssl.demo4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * ͨ��jre֤���ʵ��˫��У��
 * @author��balthie + 10050
 * @createtime �� 2014-3-19 ����7:24:22
 * @description TODO һ�仰����
 */
public class SimpleSSLServerDemo4
{
    private static String kpath = "D:/JSSE/jreDemo1/serverKeys.keystore";
    
    private static String tpath = "D:/JSSE/jreDemo1/serverKeys.keystore";
    
    private static char[] password = "changeit".toCharArray();
    
    public static void main(String[] args)
    {
        SSLContext context = null;
        try
        {
            // �����Կ��
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new FileInputStream(kpath), password);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);
            KeyManager[] km = kmf.getKeyManagers();
            
            // ��Ȩ��Կ��
            KeyStore ts = KeyStore.getInstance(KeyStore.getDefaultType());
            ts.load(new FileInputStream(tpath), password);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ts);
            TrustManager[] tm = tmf.getTrustManagers();
            
            context = SSLContext.getInstance("SSL");
            context.init(km, tm, null);
        }
        catch (Exception e)
        {
            e.printStackTrace(); // �����쳣
        }
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) context.getServerSocketFactory();
        try
        {
            SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(10000);
            
            // Ҫ�� �ͻ�����֤
            ss.setNeedClientAuth(true);
            
            // �׽���Ӧ���Կͻ���ģʽ���Ƿ�����ģʽ��ʼ����
             //ss.setUseClientMode(true);

            
            BufferedReader reader = null;
            PrintWriter writer = null;
            while(true)
            {
                System.out.println("�ȴ��ͻ������ӡ�����");
                
                SSLSocket socket = (SSLSocket) ss.accept();
                
                try
                {
                    System.out.println("Accept the Client: " + socket);
                    
                    // ����IO���
                    reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    
                    while (true)
                    {
                        String str = reader.readLine();
                        if(str.equals("byebye"))
                        {
                            break;
                        }
                        System.out.println("In Server reveived the info: " + str);
                        writer.println(" hello  i am server , i receive your message : " +  str);
                    }
                }
                finally
                {
                    System.out.println("close the Server socket and the io. \r\n");
                    socket.close();
                    /*ss.close();*/
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}