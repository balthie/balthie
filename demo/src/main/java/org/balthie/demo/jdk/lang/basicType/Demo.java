package org.balthie.demo.jdk.lang.basicType;

public class Demo
{
    public static void main(String[] args)
    {
        double unitPrice = 100.02d;
        int count = 1999;
        
        System.out.println(unitPrice * count);
        // 结果 990000.94 ，小数部分溢出
        System.out.println(Math.round(unitPrice * count * 100)/100d);
    }
}
