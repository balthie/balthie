package org.balthie.demo.jdk.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 只读缓冲区非常简单，可以读取它们，但是不能向它们写入数据。可以通过调用缓冲区的asReadOnlyBuffer()方法，将任何常规缓冲区转
 * 换为只读缓冲区，这个方法返回一个与原缓冲区完全相同的缓冲区，并与原缓冲区共享数据，只不过它是只读的。如果原缓冲区的内容发生了变化，只读缓冲区的内容也随之发生变化
 */
public class ReadOnlyBuffer
{
    static public void main( String args[] ) throws Exception {  
        ByteBuffer buffer = ByteBuffer.allocate( 10 );  
          
        // 缓冲区中的数据0-9  
        for (int i=0; i<buffer.capacity(); ++i) {  
            buffer.put( (byte)i );  
        }  
  
        // 创建只读缓冲区  
        ByteBuffer readonly = buffer.asReadOnlyBuffer();  
          
        // 改变原缓冲区的内容  
        for (int i=0; i<buffer.capacity(); ++i) {  
            byte b = buffer.get( i );  
            b *= 10;  
            buffer.put( i, b );  
        }  
          
        readonly.position(0);  
        readonly.limit(buffer.capacity());  
          
        // 只读缓冲区的内容也随之改变  
        while (readonly.remaining()>0) {  
            System.out.println( readonly.get());  
        }  
    }
}
