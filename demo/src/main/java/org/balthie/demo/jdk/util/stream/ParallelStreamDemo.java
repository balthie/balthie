package org.balthie.demo.jdk.util.stream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

// 每个Stream都有两种模式：顺序执行和并行执行。 Stream则是利用多核技术可将大数据通过多核并行处理
public class ParallelStreamDemo
{
    public static void main(String[] args)
    {
        Long now = System.currentTimeMillis();
        
        List<Date> list = LongStream.range(0L, 10000000L).mapToObj(l -> new Date(l + now)).collect(Collectors.toList());
        System.out.println(list.size());
        
        // parallelStream
        long begin = System.currentTimeMillis();
        long t1 = System.currentTimeMillis();
        System.out.println(list.parallelStream().filter(d -> d.getTime() < begin).count());
        long t2 = System.currentTimeMillis();
        System.out.println(String.format(" parallelStream [%s]", t2 - t1));
        
        // stream.parallel()
        System.out.println(list.stream().parallel().filter(d -> d.getTime() < begin).count());
        long t3 = System.currentTimeMillis();
        System.out.println(String.format(" stream.parallel [%s]", t3 - t2));
        
        // stream
        System.out.println(list.stream().filter(d -> d.getTime() < begin).count());
        long t4 = System.currentTimeMillis();
        System.out.println(String.format(" stream [%s]", t4 - t3));
        
        // loop
        List<Date> ccc = new ArrayList<Date>();
        for(Date d : list)
        {
            if(d.getTime() < begin) ccc.add(d);
        }
        System.out.println(ccc.size());
        
        long t5 = System.currentTimeMillis();
        System.out.println(String.format(" loop [%s]", t5 - t4));
        
    }
}
