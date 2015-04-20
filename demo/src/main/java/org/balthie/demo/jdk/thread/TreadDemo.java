package org.balthie.demo.jdk.thread;

public class TreadDemo
{
    public static void main(String[] args)
    {
        StackTraceElement[] stackArr=Thread.currentThread().getStackTrace();
        for(StackTraceElement ele : stackArr)
        {
            System.out.println(ele);
        }
    }
}
