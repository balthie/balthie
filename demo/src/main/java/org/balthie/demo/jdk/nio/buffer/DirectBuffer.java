package org.balthie.demo.jdk.nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * 直接缓冲区是为加快I/O速度，使用一种特殊方式为其分配内存的缓冲区，JDK文档中的描述为：给定一个直接字节缓冲区，Java虚拟机将尽最大努
 * 力直接对它执行本机I/O操作。也就是说，它会在每一次调用底层操作系统的本机I/O操作之前(或之后)，尝试避免将缓冲区的内容拷贝到一个中间缓冲区中
 * 或者从一个中间缓冲区中拷贝数据。要分配直接缓冲区，需要调用allocateDirect()方法，而不是allocate()方法，使用方式与普通缓冲区并无区别，
 */
public class DirectBuffer
{
    static public void main(String args[]) throws Exception
    {
        String infile = "c:\\test.txt";
        FileInputStream fin = new FileInputStream(infile);
        FileChannel fcin = fin.getChannel();
        
        String outfile = String.format("c:\\testcopy.txt");
        FileOutputStream fout = new FileOutputStream(outfile);
        FileChannel fcout = fout.getChannel();
        
        // 使用allocateDirect，而不是allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        
        while (true)
        {
            buffer.clear();
            
            int r = fcin.read(buffer);
            
            if(r == -1)
            {
                break;
            }
            
            buffer.flip();
            
            fcout.write(buffer);
        }
    }
}
