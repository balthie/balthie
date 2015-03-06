package org.balthie.demo.jdk.nio.buffer;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 缓冲区对象本质上是一个数组，但它其实是一个特殊的数组，缓冲区对象内置了一些机制，能够跟踪和记录缓冲区的状态变化情况
 * 
 * @desc position：指定了下一个将要被写入或者读取的元素索引，它的值由get()/put()方法自动更新，在新创建一个Buffer对象时，position被初始化为0。
 * @desc limit：指定还有多少数据需要取出(在从缓冲区写入通道时)，或者还有多少空间可以放入数据(在从通道读入缓冲区时)。
 * @desc capacity：指定了可以存储在缓冲区中的最大数据容量，实际上，它指定了底层数组的大小，或者至少是指定了准许我们使用的底层数组的容量。
 * 
 */
public class BufferStatus
{
    public static void main(String args[]) throws Exception
    {
        FileInputStream fin = new FileInputStream("d:\\test.txt");
        FileChannel fc = fin.getChannel();
        // 初始化缓冲区
       /* // 分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        
        // 包装一个现有的数组
        byte array[] = new byte[10];
        ByteBuffer buffer2 = ByteBuffer.wrap(array);*/
        
        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化", buffer);
        
        fc.read(buffer);
        output("调用read()", buffer);
        
        buffer.flip();
        output("调用flip()", buffer);
        
        while (buffer.remaining() > 0)
        {
            byte b = buffer.get();
            // System.out.print(((char)b));
        }
        output("调用get()", buffer);
        
        buffer.clear();
        output("调用clear()", buffer);
        
        fin.close();
    }
    
    public static void output(String step, Buffer buffer)
    {
        System.out.println(step + " : ");
        System.out.print("capacity: " + buffer.capacity() + ", ");
        System.out.print("position: " + buffer.position() + ", ");
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }
}