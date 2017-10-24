package org.balthie.demo.jdk.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * http://blog.csdn.net/kiss_the_sun/article/details/49930491
 * http://donald-draper.iteye.com/blog/2370912
 * 
 * @author：balthie@126.com
 * @createtime ： 2017年10月19日 下午5:23:46
 * @description TCP连接NIO实现
 *              - Connect: 某个channel成功连接到另一个服务器称为“连接就绪”,对应于SelectionKey.OP_CONNECT
 *              - Accept: 一个server socket channel准备好接收新进入的连接称为“接收就绪”，对应于SelectionKey.OP_ACCEPT
 *              - Read： 一个有数据可读的通道可以说是“读就绪”。 对应于 SelectionKey.OP_READ
 *              - Write：等待写数据的通道可以说是“写就绪”。 对应于SelectionKey.OP_WRITE
 */
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
        // 设置通道为非阻塞，与Selector一起使用时，Channel必须处于非阻塞模式下。
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个事件多路管理器
        this.selector = Selector.open();
        System.out.println("server selector id " + selector);
        
        // 将事件多路管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当客户端请求事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        SelectionKey serverSelectKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("serverChannel key " + serverSelectKey);
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
            System.out.println(String.format("server selector select begin", ""));
            System.out.println(String.format("server selector select end [%s]", selector.select()));
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator ite = this.selector.selectedKeys().iterator();
            
            while (ite.hasNext())
            {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                
                System.out.println(String.format("SelectionKey [%s] readyOps[%s]", key, key.readyOps()));
                // 客户端请求事件
                if(key.isAcceptable())
                {
                    System.out.println(String.format("key [%s] isAcceptable[%s]", key, key.isAcceptable()));
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    // 获得和客户端连接的通道，此处的clientChannel不是真实的客户端socketclient而是多路复用器
                    // accept方法主要是调用accept0（native）方法接受连接，并根据接受来接 文件描述的地址构造SocketChannelImpl
                    SocketChannel clientChannel = serverChannel.accept();
                    // 设置成非阻塞
                    clientChannel.configureBlocking(false);
                    
                    // 异步写消息到buffer中，buffer中的数据由 （???）在 (???)时通过socket发送
                    ByteBuffer buffer = ByteBuffer.allocate(200);
                    clientChannel.read(buffer);
                    System.out.println("accept clientChannel msg " + new String(buffer.array()));
                    
                    // 服务端多次的clientChannel.write, 在客户端的select监听 read时，可能合并成一次事件
//                    clientChannel.write(ByteBuffer.wrap(new String(" 这是来自服务端的一条信息 ").getBytes()));
//                    System.out.println(" res msg 这是来自服务端的一条信息");
                    // 通知select监听clientChannel的可读事件，用于获取客户端的消息
                    SelectionKey clientKey = clientChannel.register(this.selector, SelectionKey.OP_READ);
                    System.out.println("server.accept() clientKey " + clientKey);
                }
                else if(key.isReadable())
                {
                    // 监听到了可读的事件
                    System.out.println(String.format("key [%s] isReadable[%s]", key, key.isReadable()));
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
        // 客户端退出时 （channel.close 或者system.exit 都会将client可以状态更新为 readyOps[1]，并且抛出异常
        // java.io.IOException: 远程主机强迫关闭了一个现有的连接。）
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data, "UTF-8").trim();
        System.out.println("receive msg " + msg);
        if(msg.indexOf("[") != -1)
        {
            String clientName = msg.substring(msg.indexOf("["), msg.indexOf("]"));
            msg = "你好" + clientName + " 服务端收到你信息";
        }
        System.out.println("res msg " + msg);
        
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
//        channel.write(outBuffer);// 将消息回送给客户端
        channel.write(ByteBuffer.wrap("exit".getBytes()));// 同时发送两条将消息回送给客户端
        
        System.out.println(String.format(" key readyOps[%s]", key.readyOps()));
    }
    
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
