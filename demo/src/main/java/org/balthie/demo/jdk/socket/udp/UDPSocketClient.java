package org.balthie.demo.jdk.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UDPSocketClient
{
     private static final Logger LOGGER = LoggerFactory.getLogger(UDPSocketClient.class);
    
    private byte[] buffer = new byte[1024];
    
    private DatagramSocket ds = null;
    
    /**
     * 构造函数，创建UDP客户端 
     * 
     * @throws Exception
     */
    public UDPSocketClient() throws Exception
    {
        ds = new DatagramSocket();
    }
    
    /**
     * 设置超时时间，该方法必须在bind方法之后使用.
     * 
     * @param timeout
     *            超时时间
     * @throws Exception
     *             Creation date: 2007-8-16 - 下午10:55:12
     */
    public final void setSoTimeout(final int timeout) throws Exception
    {
        ds.setSoTimeout(timeout);
    }
    
    /**
     * 获得超时时间.
     * 
     * @return 返回超时时间
     * @throws Exception
     *             Creation date: 2007-8-16 - 下午10:55:25
     */
    public final int getSoTimeout() throws Exception
    {
        return ds.getSoTimeout();
    }
    
    public final DatagramSocket getSocket()
    {
        return ds;
    }
    
    /**
     * 向指定的服务端发送数据信息.
     * 
     * @param host
     *            服务器主机地址
     * @param port
     *            服务端端口
     * @param bytes
     *            发送的数据信息
     * @return 返回构造后俄数据报
     * @throws IOException
     *             Creation date: 2007-8-16 - 下午11:02:41
     */
    public final DatagramPacket send(final String host, final int port, final byte[] bytes) throws IOException
    {
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);
        ds.send(dp);
        return dp;
    }
    
    /**
     * 接收从指定的服务端发回的数据.
     * 
     * @param lhost
     *            服务端主机
     * @param lport
     *            服务端端口
     * @return 返回从指定的服务端发回的数据.
     * @throws Exception
     *             Creation date: 2007-8-16 - 下午10:52:36
     */
    public final String receive(final String lhost, final int lport) throws Exception
    {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp);
        String info = new String(dp.getData(), 0, dp.getLength());
        return info;
    }
    
    /**
     * 关闭udp连接.
     * 
     * Creation date: 2007-8-16 - 下午10:53:52
     */
    public final void close()
    {
        try
        {
            ds.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * 测试客户端发包和接收回应信息的方法.
     * 
     * @param args
     * @throws Exception
     *             Creation date: 2007-8-16 - 下午11:03:54
     */
    public static void main(String[] args) throws Exception
    {
        UDPSocketClient client = new UDPSocketClient();
        
        System.out.println(" begin at " + System.currentTimeMillis());
        for(int i = 0; i < 1000; i++)
        {
            System.out.println(" UdpClientSocket send ");
            String serverHost = "127.0.0.1";
            int serverPort = 3344;
            client.send(serverHost, serverPort, ("你好，阿蜜果!").getBytes());
            String info = client.receive(serverHost, serverPort);
            LOGGER.debug("服务端回应数据：" + info);
        }
        System.out.println(" end at " + System.currentTimeMillis());
    }
}
