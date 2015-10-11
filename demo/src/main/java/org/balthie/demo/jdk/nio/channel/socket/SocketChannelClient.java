package org.balthie.demo.jdk.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

//http://ifeve.com/socket-channel/
public class SocketChannelClient
{
    // 通道管理器
    private Selector selector;
    
    /**
     * 获得一个Socket通道，并对该通道做一些初始化的工作
     * 
     * @param ip
     *            连接的服务器的ip
     * @param port
     *            连接的服务器的端口号
     * @throws IOException
     */
    public void initClient(String ip, int port) throws IOException
    {
        // 获得一个Socket通道
        SocketChannel channel = SocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 获得一个通道管理器
        this.selector = Selector.open();
        System.out.println("client selector id " + selector);
        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
        // 用channel.finishConnect();才能完成连接
        channel.connect(new InetSocketAddress(ip, port));
        // 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
    }
    
    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     * 
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException
    {
        // 轮询访问selector
        while (true)
        {
            // 选择一组可以进行I/O操作的事件，放在selector中,客户端的该方法不会阻塞，
            // 这里和服务端的方法不一样，查看api注释可以知道，当至少一个通道被选中时，
            // selector的wakeup方法被调用，方法返回，而对于客户端来说，通道一直是被选中的
            // 阻塞到至少有一个通道在你注册的事件上就绪了。
            selector.select();
            // 一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了，然后可以通过调用selector的selectedKeys()方法，访问“已选择键集（selected key set）”中的就绪通道。
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext())
            {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 连接事件发生
                if(key.isConnectable())
                {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 如果正在连接，则完成连接
                    if(channel.isConnectionPending())
                    {
                        channel.finishConnect();
                        
                    }
                    // 设置成非阻塞
                    channel.configureBlocking(false);
                    
                    // 在这里可以给服务端发送信息
                    channel.write(ByteBuffer.wrap(new String("向服务端发送了一条信息").getBytes()));
                    
                    // 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    // 注册通道的 读就绪事件
                    //一旦向Selector注册了一或多个通道，就可以调用几个重载的select()方法。这些方法返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道。
                    SelectionKey k = channel.register(this.selector, SelectionKey.OP_READ);
                    //可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。
                    //SelectionKey k = channel.register(this.selector, SelectionKey.OP_READ, new Object());
                    //k.attach(new Object());
                }
                else if(key.isReadable())
                {
                    read(key);
                }
            }
        }
    }
    
    /**
     * 处理读取服务端发来的信息 的事件
     * 
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException
    {
        // 和服务端的read方法一样
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data, "UTF-8").trim();
        System.out.println("客户端收到信息：" + msg);
        
        if("exit".equalsIgnoreCase(msg))
        {
            System.out.println("接收到服务器响应，客户端退出监听");
            channel.finishConnect();
            channel.close();
        }
        
    }
    
    /**
     * 启动客户端测试
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        SocketChannelClient client = new SocketChannelClient();
        client.initClient("localhost", 8000);
        client.listen();
    }
}
