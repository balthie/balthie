package org.balthie.demo.jdk.socket.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 并发的socket 和端口
// http://kangzye.blog.163.com/blog/static/3681922320110872810313/
// http://bbs.csdn.net/topics/340085584
// http://kakaluyi.iteye.com/blog/1124567
public class SimpleSocketServer
{
    public static int portNo = 10000;
    
    public static void main(String[] args) throws IOException
    {
        ServerSocket s = new ServerSocket(portNo);
        System.out.println("The Server is start: " + s);
        
        Socket socket = null;
        
        Socket oldSocket = null;
        while (true)
        {
            // accept可以产生多个不同的socket，而这些socket里包含的宿IP和宿端口是不变的，变化的只是源IP和源端口。
            // 这样的话，这些socket宿端口就可以都是80，而Socket层还是能根据源/宿对来准确地分辨出IP包和socket的归属关系，从而完成对TCP /IP协议的操作封装！
            socket = s.accept();
            
            if(oldSocket == null)
            {
                oldSocket = socket;
            }
            else
            {
                // 同一个端口可以同时容纳多个socket，并且可以同时读写
                // 可行的原因是系统在 socket上有加锁：http://blog.csdn.net/flymachine/article/details/9534633
                System.out.println(oldSocket);
                System.out.println(socket);
                
                /*
                 * Socket[addr=/127.0.0.1,port=62124,localport=10000]
                 * Socket[addr=/127.0.0.1,port=62126,localport=10000]
                 */
                
            }
            
            try
            {
                System.out.println("Accept the Client: " + socket);
                
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                
                while (true)
                {
                    String str = in.readLine();
                    System.out.println("In Server reveived the info: " + str);
                    if(str.equals("byebye") || str == null)
                    {
                        break;
                    }
                    out.println(" this is server response   " + str);
                }
            }
            finally
            {
                System.out.println("close the Server socket and the io.");
                // socket.close();
                // s.close();
            }
        }
    }
}
