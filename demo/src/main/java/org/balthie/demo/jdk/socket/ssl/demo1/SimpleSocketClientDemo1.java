/**
 * author��DELL
 * createtime �� 2013-2013-9-12 ����10:51:46
 * TODO һ�仰����
 */
package org.balthie.demo.jdk.socket.ssl.demo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author DELL
 * 
 */
public class SimpleSocketClientDemo1
{
    static String clientName = "Mike";
    
    // �˿ں�
    public static int portNo = 10000;
    
    public static void main(String[] args) throws IOException
    {
        // �������ӵ�ַ��,���ӱ���
        InetAddress addr = InetAddress.getByName("192.168.69.14");
        // Ҫ��Ӧ�������˵�10000�˿ں�
        Socket socket = new Socket(addr, portNo);
        try
        {
            System.out.println("socket = " + socket);
            
            // ����IO���
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            out.println("Hello Server, I am " + clientName);
            
            String str = in.readLine();
            System.out.println(str);
            
            out.println("byebye");
        }
        finally
        {
            System.out.println("close the Client socket and the io.");
            socket.close();
        }
    }
}
