package org.balthie.demo.jdk.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SocketChannelServer
{
    // 通道管理器
    private Selector selector;
    
    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
     * 
     * @param port
     *            绑定的端口号
     * @throws IOException
     */
    public void initServer(int port) throws IOException
    {
        // 获得一个ServerSocket通道
        // ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        System.out.println("serverChannel " + serverChannel);
        // 设置通道为非阻塞
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个事件多路管理器
        this.selector = Selector.open();
        System.out.println("server selector id " + selector);
        
        // 将事件多路管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当客户端请求事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    
    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     * 
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException
    {
        System.out.println("服务端启动成功！");
        // 轮询访问selector
        while (true)
        {
            // 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator ite = this.selector.selectedKeys().iterator();
            
            while (ite.hasNext())
            {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                
                System.out.println("SelectionKey " + key);
                // 客户端请求事件
                if(key.isAcceptable())
                {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    // 设置成非阻塞
                    channel.configureBlocking(false);
                    
                    // 异步写消息到buffer中，buffer中的数据由 （???）在 (???)时通过socket发送
                    channel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));
                    // 向客户端的socket channel注册可读事件，让客户端触发读取数据操作
                    channel.register(this.selector, SelectionKey.OP_READ);
                    System.out.println("server.accept() SocketChannel " + channel);
                    // 获得了可读的事件
                }
                else if(key.isReadable())
                {
                    read(key);
                }
            }
        }
    }
    
    /**
     * 处理读取客户端发来的信息 的事件
     * 
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException
    {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        
        byte[] data = buffer.array();
        String msg = new String(data, "UTF-8").trim();
        System.out.println("服务端收到信息：" + msg);
        /*
         * ByteBuffer outBuffer = ByteBuffer.wrap("服务端收到了你的信息".getBytes());
         * channel.write(outBuffer);// 将消息回送给客户端
         */}
        
    /**
     * 启动服务端测试
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        SocketChannelServer server = new SocketChannelServer();
        server.initServer(8000);
        server.listen();
    }
}
