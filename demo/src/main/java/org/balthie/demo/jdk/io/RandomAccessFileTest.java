package org.balthie.demo.jdk.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/*
 * 操作大文件
 * 结合了NIO和内存映射（将文件内容索引加载到内存），不是一次性读取整个文件
 * http://blog.csdn.net/akon_vm/article/details/7429245
 * 真正调入内存的只是其中的一小部分，其余部分则被放在交换文件上。这样你就可以很方便地修改超大型的文件了(最大可以到2 GB)。注意，Java是调用操作系统的"文件映射机制"来提升性能的。
 * 
 * 内存映射文件I/O是一种读和写文件数据的方法，它可以比常规的基于流或者基于通道的I/O快的多。内存映射文件I/O是通过使文件中的数据出现为 内存数组的内容来完成的，这其初听起来似乎不过就是将整个文件读到内存中，但是事实上并不是这样。一般来说，只有文件中实际读取或者写入的部分才会映射到内存中。
 */
public class RandomAccessFileTest
{
    static int length = 0x8000000; // 128 Mb
    
    public static void read() throws Exception
    {
        // 为了以可读可写的方式打开文件，这里使用RandomAccessFile来创建文件。
        FileChannel fc = new RandomAccessFile("test.dat", "rw").getChannel();
        // 注意，文件通道的可读可写要建立在文件流本身可读写的基础之上
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, length);
        // 写128M的内容
        for(int i = 0; i < length; i++)
        {
            out.put((byte) 'x');
        }
        System.out.println("Finished writing");
        // 读取文件中间6个字节内容
        for(int i = length / 2; i < length / 2 + 6; i++)
        {
            System.out.print((char) out.get(i));
        }
        fc.close();
    }
    
    /*
     * 程序功能：演示了RandomAccessFile类的操作，同时实现了一个文件复制操作。
     */
    public void copyFile() throws IOException
    {
        RandomAccessFile file = new RandomAccessFile("file", "rw");
        // 以下向file文件中写数据
        file.writeInt(20);// 占4个字节
        file.writeDouble(8.236598);// 占8个字节
        file.writeUTF("这是一个UTF字符串");// 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
        file.writeBoolean(true);// 占1个字节
        file.writeShort(395);// 占2个字节
        file.writeLong(2325451l);// 占8个字节
        file.writeUTF("又是一个UTF字符串");
        file.writeFloat(35.5f);// 占4个字节
        file.writeChar('a');// 占2个字节
        
        file.seek(0);// 把文件指针位置设置到文件起始处
        
        // 以下从file文件中读数据，要注意文件指针的位置
        System.out.println("——————从file文件指定位置读数据——————");
        System.out.println(file.readInt());
        System.out.println(file.readDouble());
        System.out.println(file.readUTF());
        
        file.skipBytes(3);// 将文件指针跳过3个字节，本例中即跳过了一个boolean值和short值。
        System.out.println(file.readLong());
        
        file.skipBytes(file.readShort()); // 跳过文件中“又是一个UTF字符串”所占字节，注意readShort()方法会移动文件指针，所以不用加2。
        System.out.println(file.readFloat());
        
        // 以下演示文件复制操作
        System.out.println("——————文件复制（从file到fileCopy）——————");
        file.seek(0);
        RandomAccessFile fileCopy = new RandomAccessFile("fileCopy", "rw");
        int len = (int) file.length();// 取得文件长度（字节数）
        byte[] b = new byte[len];
        file.readFully(b);
        fileCopy.write(b);
        System.out.println("复制完成！");
    }
    
    /**
     * 插入写示例：
     * 
     * @param skip
     *            跳过多少过字节进行插入数据
     * @param str
     *            要插入的字符串
     * @param fileName
     *            文件路径
     */
    public static void insert(long skip, String str, String fileName)
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
            if(skip < 0 || skip > raf.length())
            {
                System.out.println("跳过字节数无效");
                return;
            }
            byte[] b = str.getBytes();
            raf.setLength(raf.length() + b.length);
            for(long i = raf.length() - 1; i > b.length + skip - 1; i--)
            {
                raf.seek(i - b.length);
                byte temp = raf.readByte();
                raf.seek(i);
                raf.writeByte(temp);
            }
            raf.seek(skip);
            raf.write(b);
            raf.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
