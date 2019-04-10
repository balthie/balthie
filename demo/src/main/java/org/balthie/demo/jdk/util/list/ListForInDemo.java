package org.balthie.demo.jdk.util.list;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author：balthie@126.com
 * @createtime ： 2019年4月8日 下午3:36:58
 * @description for in 循环乱序测试
 */
public class ListForInDemo
{
    public static void main(String[] args)
    {
        List<Integer> list = IntStream.range(-10, 10).mapToObj(i -> i).collect(Collectors.toList());
        list.sort(Comparator.comparingInt(Integer::intValue).reversed());
        
        list.forEach(System.out::println);
    }
}
