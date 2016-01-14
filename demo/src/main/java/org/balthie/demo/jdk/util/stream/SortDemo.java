package org.balthie.demo.jdk.util.stream;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortDemo
{
    public static void main(String[] args)
    {
        Random r = new Random();
        
        List<Foo> list = IntStream.range(1, 10).mapToObj(i -> new Foo(r.nextInt(i * 10), "name" + i))
                .collect(Collectors.toList());
                
        list.forEach(e -> System.out.println(e.id + "  --> " + e.name));
        
        list.sort((o1, o2) -> o1.id - o2.id);
        System.out.println();
        list.forEach(e -> System.out.println(e.id + "  --> " + e.name));
    }
    
    static class Foo
    {
        int id;
        
        String name;
        
        public Foo(int id, String name)
        {
            super();
            this.id = id;
            this.name = name;
        }
        
    }
}
