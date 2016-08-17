/**
 * author��DELL
 * createtime �� 2013-2013-9-9 ����2:49:24
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SimpleSSLClientDemo2
{
    private static String kpath = "D:/Workspaces/Balthie/bin/clientKeysNotExists";
    
    private static String tpath = "D:/Workspaces/Balthie/bin/clientTrust";
    
    private static char[] password = "123456".toCharArray();
    
    public static int portNo = 10000;
    
    /**
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException
    {
        InetAddress addr = InetAddress.getByName("smtp.exmail.qq.com");
        
        SSLContext context = null;
        try
        {
            /*
             * KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
             * 
             * keyStore.load(new FileInputStream(kpath), password);
             * KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
             * kmf.init(keyStore, password);
             * KeyManager[] km = kmf.getKeyManagers();
             */
            
            // KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            // trustStore.load(new FileInputStream(tpath), password);
            // TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            // tmf.init(trustStore);
            // TrustManager[] tm = tmf.getTrustManagers();
            // context = SSLContext.getInstance("SSL");
            // context.init(null, tm, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.setProperty("https.protocols", "TLSv1");
        System.out.println(System.getProperty("https.protocols"));
        SocketFactory ssf = SSLSocketFactory.getDefault();
        SSLSocket sslSocket = null;
        try
        {
            sslSocket = (SSLSocket) ssf.createSocket(addr, 465);
            System.out.println("socket = " + sslSocket);
            
            sslSocket.startHandshake();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())), true);
            out.println("Hello Server, I am  balthie ");
            
            String str = in.readLine();
            System.out.println("out " + str);
            
            out.println("byebye");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                sslSocket.close();
            }
            catch (IOException e1)
            {
                sslSocket = null;
            }
        }
    }
}
