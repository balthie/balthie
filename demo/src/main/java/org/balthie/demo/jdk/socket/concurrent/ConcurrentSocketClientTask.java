/**
 */
package org.balthie.demo.jdk.socket.concurrent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 */
public class ConcurrentSocketClientTask implements Runnable
{
    String clientName = "balthie";
    
    public static int portNo = 10000;
    
    /**
     * @param string
     */
    public ConcurrentSocketClientTask(String clientName)
    {
        this.clientName = clientName;
    }
    
    public static void main(String[] args) throws IOException
    {
        ConcurrentSocketClientTask task = new ConcurrentSocketClientTask("balthie");
        task.execute();
    }
    
    public void execute() throws UnknownHostException, IOException
    {
        InetAddress addr = InetAddress.getByName("localhost");
        Socket socket = new Socket(addr, portNo);
        try
        {
            System.out.println(String.format("[%s] task begin", this.clientName));
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            
            // 不间断打印
            for(int i=0;i<1000;i++)
            {
                out.println(clientName);
            }
            
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
    
    public String getClientName()
    {
        return clientName;
    }
    
    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }
    
    public static int getPortNo()
    {
        return portNo;
    }
    
    public static void setPortNo(int portNo)
    {
        ConcurrentSocketClientTask.portNo = portNo;
    }

    @Override
    public void run()
    {
        /*try
        {
            System.out.println(String.format("%s task wait for invoke ", clientName));
            Thread.currentThread().wait();
        }
        catch (InterruptedException e1)
        {
            
        }*/
        try
        {
            
            System.out.println(String.format("%s task invoked ", clientName));
            execute();
        }
        catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
