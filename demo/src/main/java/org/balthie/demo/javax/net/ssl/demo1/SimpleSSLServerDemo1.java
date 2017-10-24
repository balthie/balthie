/**
 * author��DELL
 * createtime �� 2013-2013-9-9 ����9:55:37
 * TODO һ�仰����
 */
package org.balthie.demo.javax.net.ssl.demo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.net.ServerSocketFactory;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 */
public class SimpleSSLServerDemo1
{
    
    public static void main(String[] args) throws IOException
    {
        ServerSocketFactory ssf = SSLServerSocketFactory.getDefault();
        SSLServerSocket sslSocket = (SSLServerSocket) ssf.createServerSocket(10000);
        sslSocket.setNeedClientAuth(false);
        
        int identity = 0;
        String ident = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        while (true)
        {
            ident = String.valueOf(identity++);
            
            SSLSocket socket = (SSLSocket) sslSocket.accept();
            
            HandshakeCompletedListener listener = new SimpleHandshakeCompletedListener(ident);
            
            socket.addHandshakeCompletedListener(listener);
            
            try
            {
                System.out.println("Accept the Client: " + socket);
                
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
