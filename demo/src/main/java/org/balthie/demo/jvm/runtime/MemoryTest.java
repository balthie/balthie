package org.balthie.demo.jvm.runtime;

import java.util.ArrayList;
import java.util.List;

public class MemoryTest
{
    public static void main(String[] args)
    {
        Runtime r = Runtime.getRuntime();
        r.gc();
        long startMem = r.freeMemory(); // 开始时的剩余内存
        List l = new ArrayList<Long>(10);
        for(long i=0;i<1000000;i++)
        {
            l.add(i);
        }
        long orz = startMem - r.freeMemory(); // 剩余内存 现在 - 开始 = o 的大小
        System.out.println(orz/1024/1024);
    }
}
