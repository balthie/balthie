package org.balthie.demo.jdk.lang.basicType;

public class Demo
{
    public static void main(String[] args)
    {
        double unitPrice = 100.02d;
        int count = 1999;
        
        System.out.println(unitPrice * count);
        System.out.println(Math.round(unitPrice * count * 100)/100d);
        
        System.out.println(100.01D - 100.02D);
    }
}
