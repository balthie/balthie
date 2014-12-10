package org.balthie.demo.jdk.lang.system;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RuntimeDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        Runtime r = Runtime.getRuntime();
        
        printMaxMemory(r);
        printMemoryInfo(r);
        
        for(int i=0;i<10000;i++)
        {
            Object o = new Date();
        }
        printMemoryInfo(r);
        r.runFinalization();
        TimeUnit.MILLISECONDS.sleep(1000);
        
        printMemoryInfo(r);
    }
    
    public static void printMaxMemory(Runtime r)
    {
        System.out.println("最大内存 = " + r.maxMemory());
    }
    
    public static void printMemoryInfo(Runtime r)
    {
        long used = r.totalMemory();
        long free = r.freeMemory();
        System.out.println("已分配内存 = " + used + "  剩余可用内存 = "+free);
    }
}
