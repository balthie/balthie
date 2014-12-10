package org.balthie.demo.jdk.lang.bit;

public class BitDemo
{  
    public static void main(String[] args)
    {
        System.out.println((byte) 0x81);
        // 返回无符号的2进制表示 1110011
        System.out.println(Integer.toBinaryString(-127));
        System.out.println(Byte.toUnsignedInt((byte) 0x81));
        
        
        // 返回无符号的2进制表示 1110011
        String hex = Integer.toBinaryString(115);
        System.out.println(hex);
        // 返回2进制的字符串1110011对应的值 115
        System.out.println(Integer.valueOf("1110011", 2));
        
        // 16进制值转换成二进制
        System.out.println(Long.parseLong("49", 16));
        System.out.println(Long.parseLong("2F", 16));
    }
}
