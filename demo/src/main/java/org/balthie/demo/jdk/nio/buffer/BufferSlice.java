package org.balthie.demo.jdk.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 
 缓冲区分片 在NIO中，除了可以分配或者包装一个缓冲区对象外，还可以根据现有的缓冲区对象来创建一个子缓冲区，即在现有缓冲区上切出一片来作为一个新的缓冲区，
 * 但现有的缓冲区与创建的子缓冲区在底层数组层面上是数据共享的，也就是说，子缓冲区相当于是现有缓冲区的一个视图窗口。调用slice()方法可以创建一个子缓冲区
 * 
 * 只有子缓冲区“可见的”那部分数据发生了变化，并且说明子缓冲区与原缓冲区是数据共享的
 */
public class BufferSlice
{
    static public void main(String args[]) throws Exception
    {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        
        // 缓冲区中的数据0-9
        for(int i = 0; i < buffer.capacity(); ++i)
        {
            buffer.put((byte) i);
        }
        
        // 创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();
        
        // 改变子缓冲区的内容
        for(int i = 0; i < slice.capacity(); ++i)
        {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }
        
        buffer.position(0);
        buffer.limit(buffer.capacity());
        
        while (buffer.remaining() > 0)
        {
            System.out.println(buffer.get());
        }
    }
}
