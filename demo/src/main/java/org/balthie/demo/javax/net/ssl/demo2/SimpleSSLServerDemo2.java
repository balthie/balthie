/**
 * author��DELL
 * createtime �� 2013-2013-9-9 ����9:55:37
 * TODO һ�仰����
 */
package org.balthie.demo.javax.net.ssl.demo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore;
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

/**
 * ������������֤
 */
public class SimpleSSLServerDemo2
{
private static String kpath = "D:/Workspaces/Balthie/bin/serverKeys";
    
    private static String tpath = "D:/Workspaces/Balthie/bin/serverTrustNotExists";
    
    private static char[] password = "123456".toCharArray();
    
    public static void main(String[] args) throws IOException
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
            
            /*// ��Ȩ��Կ��
            KeyStore ts = KeyStore.getInstance(KeyStore.getDefaultType());
            ts.load(new FileInputStream(tpath), password);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ts);
            TrustManager[] tm = tmf.getTrustManagers();*/
            
            context = SSLContext.getInstance("SSL");
            context.init(km, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace(); // �����쳣
        }
        
        ServerSocketFactory ssf = context.getServerSocketFactory();   
        SSLServerSocket sslSocket = (SSLServerSocket) ssf.createServerSocket(10000);
        
        sslSocket.setNeedClientAuth(false);
        
        BufferedReader reader = null;
        PrintWriter writer = null;
        while (true)
        {
            System.out.println("�ȴ��ͻ������ӡ�����");
            
            SSLSocket socket = (SSLSocket) sslSocket.accept();
            
            try
            {
                System.out.println("Accept the Client: " + socket);
                
                // ����IO���
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                
                while (true)
                {
                    String str = reader.readLine();
                    if(str.equals("byebye"))
                    {
                        break;
                    }
                    System.out.println("In Server reveived the info: " + str);
                    writer.println(str);
                }
            }
            finally
            {
                System.out.println("close the Server socket and the io.");
                socket.close();
            }
        }
    }
}
