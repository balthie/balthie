package org.balthie.demo.jdk.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// http://www.cnblogs.com/ironPhoenix/p/4199728.html
// 缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存(channel绑定的内存)
public class BufferBase
{
//    •capacity 作为一个内存块，Buffer有一个固定的大小值（这个大小是刚开始申请的），叫作“capacity”.你只能往里写capacity个byte、int，char等类型。
//    •position 当写数据到Buffer中时，position表示当前可写的位置。初始的position值为0，最大可为capacity – 1 
//              当读取数据时，也是从某个特定位置读。当从Buffer的position处读取数据完成时，position向前移动到下一个可读的位置。
//    •limit    在写数据时，Buffer的limit表示你最多能往Buffer里写多少数据，position移动到limit写操作停止。
//    capacity的值是根据申请Buffer的大小和种类确定的，所以不能改变。而position和limit就可以根据我的需要而改变了
    
    public static void main(String[] args) throws IOException
    {
//      try (FileInputStream fin = new FileInputStream(new File("").createTempFile("Prefix", "suffix"));)
//      {
//          fin.read("d:\\test.txt".getBytes());
//          FileChannel fc = fin.getChannel();
        
        try (RandomAccessFile aFile = new RandomAccessFile("D://queryFriendTopic", "rw");
                FileChannel fc = aFile.getChannel();)
        {
        
//            fin.read("d:\\test.txt".getBytes());
            
            ByteBuffer buf = ByteBuffer.allocate(5);
            printbuf("allocate", buf);
            // 手动设置buf内容
//            buf.put(new byte[]{1,2,4,2,-13});
            
            readOnce(fc, buf);
            
            // 清理buf， 将position置为0，将limit置为capacity, 使buf可重入数据
//            buf.clear();
//            printbuf("clear", buf);
            
            // 是将position置为0，这样一来就可以将buffer再重新读一遍
            buf.rewind();
            printbuf("rewind", buf);
            readOnce(fc, buf);
            
        }
        
    }
    
    private static void readOnce(FileChannel fc, ByteBuffer buf) throws IOException
    {
        
        // 向buf写入数据
        int readbyte = fc.read(buf);
        System.out.println("read byte " + readbyte);
        printbuf("FileChannel read to buf", buf);
        
        // 将limit设为position的值，再是将position设为0
        buf.flip();
        printbuf("buf flip ", buf);
        while (buf.hasRemaining())
        {
            System.out.print((char) buf.get() + "    ");
            printbuf("buf get ", buf);
        }
        System.out.println("            [printbuf end]");
        
    }
    
    public static void printbuf(String prefix, ByteBuffer buf)
    {
        System.out.println(String.format("buf [%s] capacity[%s] limit[%s] position[%s] remaining[%s] hasRemaining[%s]",
                new Object[] { prefix, buf.capacity(), buf.limit(), buf.position(), buf.remaining(), buf.hasRemaining() }));
                
    }
}
