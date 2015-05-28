package org.balthie.demo.jdk.util.compare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo
{
    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<Integer>();
        
        list.add(5);
        list.add(98);
        list.add(33);
        list.add(1);
        list.add(47);
        
        System.out.println(list);
        
        list.sort(new Comparator<Integer>() {
            
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o1 - o2;
            }
        });
        System.out.println(list);
    }
}
