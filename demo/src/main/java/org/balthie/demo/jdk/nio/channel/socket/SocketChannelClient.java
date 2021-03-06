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
            System.out.println(String.format("client selector select begin", ""));
            
            System.out.println(String.format("client selector select end [%s]", selector.select()));
            // 一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了，然后可以通过调用selector的selectedKeys()方法，访问“已选择键集（selected
            // key set）”中的就绪通道。
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext())
            {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 客户端发送连接请求
                if(key.isConnectable())
                {
                    System.out.println(String.format("key[%s] connected", key));
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 如果正在连接
                    if(channel.isConnectionPending())
                    {
                        // 确认通道连接已建立(对应channel.connect , 这里才真实连接服务器)
                        // 1，channel阻塞模式下，调用connect()进行连接操作时，会一直阻塞到连接建立完成
                        // 2，非阻塞模式下，connect()操作是调用后直接返回结果
                        // 如果连接失败 ，channel将被关闭
                        // 不调用此方法，服务端channel.read 报错 java.io.IOException: 远程主机强迫关闭了一个现有的连接。
                        // 客户端调用channel.write报错 java.nio.channels.NotYetConnectedException
                        channel.finishConnect();
                    }
                    // 设置成非阻塞，与Selector一起使用时，Channel必须处于非阻塞模式下。
                    channel.configureBlocking(false);
                    
                    // 在这里可以给服务端发送信息
                    channel.write(ByteBuffer.wrap(new String("服务端你好，我是[" + this + "]").getBytes()));
                    
                    // 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给select注册read事件。
                    // 注册通道的 读就绪事件
                    // 一旦向Selector注册了一或多个通道，就可以调用几个重载的select()方法。这些方法返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道。
                    
                    // 向服务端发送数据后，等待服务端的响应，通知客户端selector监听此通道的可读事件
                    SelectionKey k = channel.register(this.selector, SelectionKey.OP_READ);
                    
                    // 可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。
                    // SelectionKey k = channel.register(this.selector, SelectionKey.OP_READ, new
                    // Object());
                    // k.attach(new Object());
                }
                // 通道有可读数据
                else if(key.isReadable())
                {
                    System.out.println(String.format("key[%s] readable", key));
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
        System.out.println(String.format("read begin key[%s] readyops[%s]", key, key.readyOps()));
        
        // 和服务端的read方法一样
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data, "UTF-8").trim();
        System.out.println("客户端收到信息：" + msg);
        
        System.out.println(String.format("read end key[%s] readyops[%s]", key, key.readyOps()));
        
        if(msg.contains("exit"))
        {
            System.out.println("接收到服务器响应，客户端退出监听");
            channel.finishConnect();
            channel.close();
//            this.selector.close();
            System.exit(0);
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
