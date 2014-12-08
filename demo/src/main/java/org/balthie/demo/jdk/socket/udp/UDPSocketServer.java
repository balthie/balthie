package org.balthie.demo.jdk.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UDPSocketServer
{
private static final Logger LOGGER = LoggerFactory.getLogger(UDPSocketServer.class);
    
    private byte[] buffer = new byte[10240];
    
    private DatagramSocket ds = null;
    
    private DatagramPacket packet = null;
    
    private InetSocketAddress socketAddress = null;
    
    private String orgIp;
    
    public UDPSocketServer()
    {
        super();
    }

    /**
     * 构造函数，绑定主机和端口.
     * 
     * @param host 主机
     * @param port 端口
     * @throws Exception
     */
    public UDPSocketServer(String host, int port) throws Exception
    {
        socketAddress = new InetSocketAddress(host, port);
        ds = new DatagramSocket(socketAddress);
        LOGGER.debug("服务端启动!");
    }
    
    public final String getOrgIp()
    {
        return orgIp;
    }
    
    /**
     * @description 设置超时时间，该方法必须在bind方法之后使用.
     * @param timeout 超时时间
     * @throws Exception
     */
    public final void setSoTimeout(int timeout) throws Exception
    {
        ds.setSoTimeout(timeout);
    }
    
    /**
     * @description 获得超时时间.
     * @return 返回超时时间.
     * @throws Exception 下午10:34:36
     */
    public final int getSoTimeout() throws Exception
    {
        return ds.getSoTimeout();
    }
    
    /**
     * @description 绑定监听地址和端口.
     * @param host 主机IP
     * @param port 端口
     * @throws SocketException Creation date: 2007-8-16 - 下午10:36:17
     */
    public final void bind(String host, int port) throws SocketException
    {
        socketAddress = new InetSocketAddress(host, port);
        ds = new DatagramSocket(socketAddress);
    }
    
    /**
     * @description 接收数据包，该方法会造成线程阻塞.
     * @return 返回接收的数据串信息
     * @throws IOException Creation date: 2007-8-16 - 下午10:38:24
     */
    public final String receive() throws IOException
    {
        packet = new DatagramPacket(buffer, buffer.length);
        ds.receive(packet);
        orgIp = packet.getAddress().getHostAddress();
        String info = new String(packet.getData(), 0, packet.getLength());
        LOGGER.debug("接收信息：" + info);
        System.out.println(info + " at " + System.currentTimeMillis() );
        return info;
    }
    
    /**
     * @description 将响应包发送给请求端.
     * @param bytes 回应报文
     * @throws IOException Creation date: 2007-8-16 - 下午11:05:31
     */
    public final void response(String info) throws IOException
    {
        LOGGER.debug("客户端地址 : " + packet.getAddress().getHostAddress() + ",端口：" + packet.getPort());
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
        dp.setData(info.getBytes());
        ds.send(dp);
    }
    
    /**
     * @description 设置报文的缓冲长度.
     * @param bufsize 缓冲长度 Creation date: 2007-8-16 - 下午10:47:49
     */
    public final void setLength(int bufsize)
    {
        packet.setLength(bufsize);
    }
    
    /**
     * @description 获得发送回应的IP地址.
     * @return 返回回应的IP地址 Creation date: 2007-8-16 - 下午10:48:27
     */
    public final InetAddress getResponseAddress()
    {
        return packet.getAddress();
    }
    
    /**
     * @description 获得回应的主机的端口.
     * @return 返回回应的主机的端口. Creation date: 2007-8-16 - 下午10:48:56
     */
    public final int getResponsePort()
    {
        return packet.getPort();
    }
    
    /**
     * @description 关闭udp监听口.
     * Creation date: 2007-8-16 - 下午10:49:23
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
     * @description 测试方法.
     * @param args
     * @throws Exception Creation date: 2007-8-16 - 下午10:49:50
     */
    public static void main(String[] args) throws Exception
    {
        String serverHost = "127.0.0.1";
        int serverPort = 3344;
        UDPSocketServer udpServerSocket = new UDPSocketServer(serverHost, serverPort);
        while (true)
        {
            udpServerSocket.receive();
            udpServerSocket.response("你好,sterning!");
        }
    }
    
    /**
     * @createtime ： 2014年12月10日 下午9:13:07
     * @description 启动UDP端口监听
      * @throws Exception
     */
    public void start() throws Exception
    {
        String serverHost = "127.0.0.1";
        int serverPort = 3344;
        UDPSocketServer udpServerSocket = new UDPSocketServer(serverHost, serverPort);
        while (true)
        {
            LOGGER.debug(" server receive ");
            udpServerSocket.receive();
            udpServerSocket.response("你好,sterning!");
        }
    }
}
