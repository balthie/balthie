package org.balthie.demo.jdk.nio.channel.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//http://ifeve.com/file-channel/
// FileChannel 无法设置为非堵塞式
public class FileChannelTest
{
    public static void main(String[] args) throws IOException
    {
        read();
//        write();
    }
    
    private static void read() throws IOException
    {
        try (RandomAccessFile aFile = new RandomAccessFile("D://queryFriendTopic", "rw");
                FileChannel inChannel = aFile.getChannel();)
        {
        
//        try (FileInputStream fin = new FileInputStream(new File("").createTempFile("Prefix", "suffix"));)
//        {
//            fin.read("d:\\test.txt".getBytes());
//            FileChannel inChannel = fin.getChannel();
            
            ByteBuffer buf = ByteBuffer.allocate(48);
            
            // 从数据源加载到缓存区
            int bytesRead = -1;
            do
            {
                // 继续从数据源加载到缓存区
                bytesRead = inChannel.read(buf);
                if(bytesRead != -1)
                {
                    System.out.println("Read " + bytesRead);
                    buf.flip();
                    while (buf.hasRemaining())
                    {
                        System.out.print((char) buf.get());
                    }
                    buf.clear();
                }
            }
            while (bytesRead != -1);
        }
    }
    
    public static void write() throws IOException
    {
        int capacity = 48;
        StringBuilder newData = new StringBuilder("New String to write to file...");
        
        // 超过 capacity byte的数据,报错 java.nio.BufferOverflowException
        for(int i = 0; i < 2; i++)
        {
            newData.append(System.currentTimeMillis());
        }
        
        RandomAccessFile aFile = new RandomAccessFile("D://fileChannelOut.txt", "rw");
        FileChannel outChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(capacity);
        buf.clear();
        buf.put(newData.toString().getBytes());
        buf.flip();
        try
        {
            
            /*
             * FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。出于性能方面的考虑，操作系统会将数据缓存在内存中，
             * 所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。要保证这一点，需要调用force()方法。
             * force()方法有一个boolean类型的参数，指明是否同时将文件元数据（权限信息等）写到磁盘上。 下面的例子同时将文件数据和元数据强制写到磁盘上：
             */
            // outChannel.force(true);
            
            while (buf.hasRemaining())
            {
                outChannel.write(buf);
            }
            
            // 获取文件大小
            long fileSize = outChannel.size();
            System.out.println(" fileSize = " + fileSize);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            outChannel.close();
        }
        
    }
}
