package org.balthie.demo.jdk.io.byteArrayStream;


// 报文翻译
public class ProtocolTransfer
{  
    public static String toHexString(byte b)
    {
        int v = b & 0xFF;
        String hv = Integer.toHexString(v);
        return " 0x" + hv + " ";
    }
    
    public static Byte getByte(byte[] datagram, int offset)
    {
        if(datagram != null && datagram.length > offset)
        {
            return datagram[0];
        }
        return null;
    }
    
    public static Character getChar(byte[] datagram, int offset)
    {
        if(datagram != null && datagram.length > offset)
        {
            char value = (char) datagram[offset];
            return value;
        }
        return null;
    }
    
    public static Short getUnsignByte(byte[] datagram, int offset)
    {
        if(datagram != null && datagram.length > offset)
        {
            return (short) (datagram[0] & 0xFF);
        }
        return null;
    }
    
    // 小端模式2字节数 转化为short
    public static Short getLittleEndianShort(byte[] frameDataGram, int offset)
    {
        if(frameDataGram != null && frameDataGram.length > offset)
        {
            // 小端模式2字节数 转化为short
            return (short) ((frameDataGram[offset] & 0xFF) | ((frameDataGram[offset + 1] << 8) & 0xFF00));
        }
        
        return null;
    }
    
    // 小端模式short转化为2字节数
    public static byte[] transLittleEndianShort(short s)
    {
        byte[] bArr = new byte[2];
        short tempS = 1;
        
        // 使用 short 1   验证大小端
        // 大端模式下 1 =  0x00, 0x01
        // 小端模式下 1 =  0x01, 0x00
        if(((tempS & 0xFF00) >> 8) == 0)
        {
            // 高位为0大端模式，需转换为小端模式
            bArr[0] = (byte) (s & 0x00FF);
            bArr[1] = (byte) ((s & 0xFF00) >> 8);
        }
        else
        {
            // 小端模式 高位为 1
            bArr[0] = (byte) ((s & 0xFF00) >> 8);
            bArr[1] = (byte) ((s & 0x00FF));
        }
       
        return bArr;
    }
    
    // 小端模式4字节数 转化为int
    public static Integer getLittleEndianInteger(byte[] frameDataGram, int offset)
    {
        if(frameDataGram != null && frameDataGram.length > offset)
        {
            int value = (int) ((frameDataGram[offset] & 0xFF)
                    | ((frameDataGram[offset + 1] << 8) & 0xFF00) | ((frameDataGram[offset + 2] << 16) & 0xFF0000) | ((frameDataGram[offset + 3] << 24) & 0xFF000000));
            return value;
        }
        return null;
    }
    
    public static Long getLittleEndianUnsignInteger(byte[] frameDataGram, int offset)
    {
        if(frameDataGram != null && frameDataGram.length > offset)
        {
            long value = (long) ((frameDataGram[offset] & 0xFF)
                    | ((frameDataGram[offset + 1] << 8) & 0xFF00) | ((frameDataGram[offset + 2] << 16) & 0xFF0000) | ((frameDataGram[offset + 3] << 24) & 0xFF000000L));
            return value;
        }
        return null;
    }
    
    public static byte count(byte[] frameDataGram)
    {
        byte plus = 0;
        if(frameDataGram != null && frameDataGram.length > 0)
        {
            for(byte b : frameDataGram)
            {
                plus += b;
            }
        }
        return plus;
    }
    
    public static byte[] transLittleEndianInteger(int i)
    {
        byte[] bArr = new byte[4];
        
        int tempI = 19088743;
        
        // 使用 short 1   验证大小端
        // 大端模式下 1 =  0x01, 0x23, 0x45, 0x67
        // 小端模式下 1 =  0x67, 0x45, 0x23, 0x01
        if((tempI & 0x000000FF) == 0x67)
        {
            // 低位为1： 大端模式，需转换为小端模式
            bArr[0] = (byte) ((i & 0x000000FF));
            bArr[1] = (byte) ((i & 0x0000FF00) >> 8);
            bArr[2] = (byte) ((i & 0x00FF0000) >> 16);
            bArr[3] = (byte) ((i & 0xFF000000) >> 24);
        }
        else
        {
            // 高位为 1: 小端模式
            bArr[0] = (byte) ((i & 0xFF000000) >> 24);
            bArr[1] = (byte) ((i & 0x00FF0000) >> 16);
            bArr[2] = (byte) ((i & 0x0000FF00) >> 8);
            bArr[3] = (byte) ((i & 0x000000FF));
        }
       
        return bArr;
    }
}
