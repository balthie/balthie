package org.balthie.demo.jdk.socket.concurrent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 测试ServerSocket并发性，多个client同时持续写入，在server端监控结果
 */
public class ConcurrentSocketServer
{
    public static int portNo = 10000;
    
    public static void main(String[] args) throws IOException
    {
        ConcurrentSocketServer server = new ConcurrentSocketServer();
        server.startServer();
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2015年6月11日 下午2:36:08
     * @since version 初始于版本 TODO
     * @throws IOException
     */
    public void startServer() throws IOException
    {
        ServerSocket s = new ServerSocket(portNo);
        System.out.println("The Server is start: " + s);
        
        Socket socket = null;
        
        while (true)
        {
            // 对每一个接入的socket创建一个线程，读取socket传入中的内容
            socket = s.accept();
            
            System.out.println("Accept the Client: " + socket);
            
            new Thread(new ConcurrentSocketServer.ReadTask(socket)).start();
        }
    }
    
    class ReadTask implements Runnable
    {
        Socket socket;
        
        public ReadTask(Socket socket)
        {
            super();
            this.socket = socket;
        }
        
        @Override
        public void run()
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
                
                String str = null;
                while ((str = in.readLine()) != null)
                {
                    System.out.println(String.format(" [%s] reveived [%s] at [%3$s]", Thread.currentThread().getName(), str, System.currentTimeMillis()));
                    if(str.equals("byebye") || str == null)
                    {
                        break;
                    }
                    out.println(" this is server response   " + str);
                }
            }
            catch (IOException e)
            {
            }
            finally
            {
                try
                {
                    this.socket.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }
}
