package org.balthie.demo.javax.net.ssl.demo3;

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
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SimpleSSLClientDemo3
{
    private static String kpath = "D:/JSSE/keydemo/clientKeys";
    
    private static String tpath = "D:/JSSE/keydemo/clientTrust";
    
    private static char[] password = "123456".toCharArray();
    
    static String clientName = "Mike";
    
    // �˿ں�
    public static int portNo = 10000;
    
    /**
     * @param args
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws UnknownHostException
    {
        InetAddress addr = InetAddress.getByName("192.168.69.14");
        
        SSLContext context = null;
        try
        {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            
            keyStore.load(new FileInputStream(kpath), password);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keyStore, password);
            KeyManager[] km = kmf.getKeyManagers();
            
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(tpath), password);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(trustStore);
            TrustManager[] tm = tmf.getTrustManagers();
            context = SSLContext.getInstance("SSL");
            context.init(km, tm, null);
        }
        catch (Exception e)
        {
            e.printStackTrace(); // �����쳣
        }
        SSLSocketFactory ssf = context.getSocketFactory();
        SSLSocket sslSocket = null;
        
        try
        {
            sslSocket = (SSLSocket) ssf.createSocket(addr, 10000);
            System.out.println("socket = " + sslSocket);
            
            sslSocket.startHandshake();
            
            // ����IO���
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())), true);
            out.println("Hello Server, I am " + clientName);
            
            String str = in.readLine();
            System.out.println(str);
            
            out.println("byebye");
        }
        catch (Exception e)
        {
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