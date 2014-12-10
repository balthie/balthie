package org.balthie.demo.jdk.io.byteArrayStream;

import java.io.ByteArrayOutputStream;

public class Demo
{  
    // 小端模式short转化为2字节数
    public static byte[] transLittleEndianShort(short s)
    {
        System.out.println(" max short = " +s );
        byte[] bArr = new byte[2];
        bArr[1] = (byte) ((s & 0xFF00) >> 8);
        bArr[0] = (byte) s;
        
        for(byte b : bArr)
        {
            System.out.print(ProtocolTransfer.toHexString(b) + ", ");
        }
        System.out.println();
        
        // 验证 小端 short 转换结果
        byte[] temp = new byte[] { (byte) 0x96, 0x0c };
        System.out.println(ProtocolTransfer.getLittleEndianShort(temp, 0));
        return bArr;
    }
    
    public static byte[] transLittleEndianInteger(int i)
    {
        System.out.println(" max int =  " + Integer.MAX_VALUE );
        System.out.println("int = " + i );
        
        byte[] iArr = ProtocolTransfer.transLittleEndianInteger(i);
        
        for(byte b : iArr)
        {
            System.out.print(ProtocolTransfer.toHexString(b) + ", ");
        }
        System.out.println();
        
        System.out.println(" unsign int =  "+ ProtocolTransfer.getLittleEndianUnsignInteger(iArr, 0));

        return iArr;
    }
    
    public static void main(String[] args)
    {
        ByteArrayOutputStream bs = new ByteArrayOutputStream(256);
        
        bs.write(0x86);
        
        bs.write(new Integer(256));
        
        transLittleEndianShort((short) 0xffffff);
        
        transLittleEndianInteger((int) 0xff345678);
    }
}
