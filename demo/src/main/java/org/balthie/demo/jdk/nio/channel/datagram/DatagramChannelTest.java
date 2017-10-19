/**
 * @author：balthie@126.com
 * @createtime ： 2017年10月19日 下午4:57:37
 * @description Java NIO中的DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。
 *              http://ifeve.com/datagram-channel/
 * @since version 初始于版本 TODO
 */
package org.balthie.demo.jdk.nio.channel.datagram;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class DatagramChannelTest
{
    public void openChannel() throws IOException
    {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9090));
    }
    
    public static void main(String[] args)
    {
    
    }
}