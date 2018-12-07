package org.balthie.demo.jdk.util.stream;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

//流（Stream）仅仅代表着数据流，并没有数据结构，所以他遍历完一次之后便再也无法遍历（这点在编程时候需要注意，不像Collection，遍历多少次里面都还有数据），它的来源可以是Collection、array、io等等。
public class StreamDemo1
{
    // 3.1中间与终点方法
    // 流作用是提供了一种操作大数据接口，让数据操作更容易和更快。它具有过滤、映射以及减少遍历数等方法，
    // 这些方法分两种：中间方法和终端方法，“流”抽象天生就该是持续的，中间方法永远返回的是Stream，因此如果我们要获取最终结果的话，必须使用终点操作才能收集流产生的最终结果。区分这两个方法是看他的返回值，如果是Stream则是中间方法，否则是终点方法。具体请参照Stream的api。
    // 中间方法（filter、map） 终点方法（collect、sum）
    
    public static void main(String[] args)
    {
        Long now = System.currentTimeMillis();
        long range = 1000;
        List<Date> list = LongStream.range(0L, range).mapToObj(l -> new Date(l * 1000 + now)).collect(Collectors.toList());
        System.out.println(list.size());
        
        System.out.println(Integer.MIN_VALUE - 1);
        
        new StreamDemo1().sortedDemo(now);
        
        new StreamDemo1().filter(list);
//        
//        new StreamDemo1().map(list);
//        
//        new StreamDemo1().count(list);
//        
//        new StreamDemo1().collect(list);
//        
//        new StreamDemo1().reduce();
//        
        new StreamDemo1().summaryStatistics();
        
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
                
    }
    
    private void sortedDemo(Long now)
    {
        long range = 10;
        List<Date> list = LongStream.range(0L, range).mapToObj(l -> new Date(l * 100000000 + now)).collect(Collectors.toList());
        
        System.out.println("排序前");
        list.stream().forEach(System.out::println);
        
        System.out.println("_________________ intList sorted begin  ____________________");
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(Integer.MIN_VALUE);
        intList.add(0);
        intList.add(1);
        intList.add(Integer.MAX_VALUE);
        intList.add(Integer.MIN_VALUE + 555);
        intList.add(1000);
        intList.add(-1000);
        System.out.println("_________________ intList original  ____________________");
        intList.stream().forEach(System.out::println);
        System.out.println("_________________ intList stream().sorted  ____________________");
        Stream<Integer> intListSortedStream = intList.stream()
                .sorted((d1, d2) -> Integer.compare(d2, d1));
        intListSortedStream.forEach(System.out::println);
        
        System.out.println("_________________ intList stream().sorted  Comparator ____________________");
        intListSortedStream = intList.stream()
                .sorted(Comparator.comparing(Integer::intValue));
        intListSortedStream.forEach(System.out::println);
        
        System.out.println("_________________ intList List.sort  ____________________");
        intList.sort((d1, d2) -> (d2 - d1));
        intList.stream().forEach(System.out::println);
        
        System.out.println("_________________ orderedBeanList sorted begin  ____________________");
        List<OrderedBean> orderedBeanList = new ArrayList<OrderedBean>();
        orderedBeanList.add(new OrderedBean(Integer.MAX_VALUE));
        orderedBeanList.add(new OrderedBean(0));
        orderedBeanList.add(new OrderedBean(1));
        orderedBeanList.add(new OrderedBean(-1));
        orderedBeanList.add(new OrderedBean(Integer.MIN_VALUE));
        orderedBeanList.add(new OrderedBean(Integer.MIN_VALUE + 1));
        orderedBeanList.add(new OrderedBean(1000));
        orderedBeanList.add(new OrderedBean(-1000));
        System.out.println("_________________ orderedBeanList original  ____________________");
        orderedBeanList.stream().forEach(System.out::println);
        System.out.println("_________________ orderedBeanList stream().sorted  ____________________");
        
        Stream<OrderedBean> orderedBeanSortedStream = orderedBeanList.stream()
                .sorted((d1, d2) -> Integer.compare(d2.getOrder(), d1.getOrder()));
        orderedBeanSortedStream.forEach(System.out::println);
        
        System.out.println("_________________ intList stream().sorted  Comparator ____________________");
        orderedBeanSortedStream = orderedBeanList.stream()
                .sorted(Comparator.comparing(OrderedBean::getOrder));
        orderedBeanSortedStream.forEach(System.out::println);
        System.out.println("_________________ orderedBeanList List.sort  ____________________");
        orderedBeanList.sort((d1, d2) -> (d2.getOrder() - d1.getOrder()));
        orderedBeanList.stream().forEach(System.out::println);
    }
    
    // 计算List中的元素的最大值，最小值，总和及平均值
    private void summaryStatistics()
    {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
    
    // reduce() 是将集合中所有值结合进一个，Reduce类似SQL语句中的sum(), avg() 或count()
    private void reduce()
    {
        // reduce(T, BinaryOperator) T 表示接受归并结果的对象 ， BinaryOperator实现归并逻辑
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for(Integer cost : costBeforeTax)
        {
            double price = cost + .12 * cost;
            total = total + price;
            
        }
        System.out.println("Total : " + total);
        
        // New way:
        costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
        
        BigDecimal totalB = costBeforeTax.stream().map((cost) -> BigDecimal.valueOf(cost + .12 * cost)).reduce(BigDecimal.ZERO,
                BigDecimal::add);
        System.out.println(totalB.toString());
    }
    
    // 统计功能
    private void collect(List<Date> list)
    {
        List<Date> r1 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis()).collect(Collectors.toList());
        
        Set<Date> r2 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis()).collect(Collectors.toSet());
        
        Map<Integer, List<Date>> r3 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis())
                .collect(Collectors.groupingBy(Date::getDay));
                
        r3 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis())
                .collect(Collectors.groupingBy(d -> d.getDay()));
                
        Map<Integer, Map<Integer, List<Date>>> r4 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis())
                .collect(Collectors.groupingBy(Date::getMonth, Collectors.groupingBy(Date::getDay)));
                
        Double r5 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis())
                .collect(Collectors.averagingLong(Date::getTime));
                
        // https://www.cnblogs.com/xujanus/p/6133865.html
        // 如果list中的key有重复，会抛出异常 java.lang.IllegalStateException: Duplicate key
        // 模拟一个重复的key list.add(list.get(0));
        Map<Integer, Date> r6 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis())
                .collect(Collectors.toMap(Date::getDate, d -> d));
                
        // 模拟一个重复的key
        list.add(list.get(0));
        Map<Integer, Date> r7 = list.stream().filter(d -> d.getTime() < System.currentTimeMillis())
                .collect(Collectors.toMap(Date::getDate, d -> d, (d1, d2) -> d2));
    }
    
    // count方法是一个流的终点方法，可使流的结果最终统计，返回int
    private void count(List<Date> list)
    {
        System.out.println(list.stream().filter(d -> d.getTime() < System.currentTimeMillis()).count());
    }
    
    // Map操作允许我们执行一个Function的实现（Function<T,R>的泛型T,R分别表示执行输入和执行结果），它接受入参并返回。
    // 对记录进行处理，并返回处理过的结果
    private void map(List<Date> list)
    {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        
        // lambda 实现
        Stream<String> strResult = list.stream().filter(d -> d.getTime() < System.currentTimeMillis()).map(d -> s.format(d));
        strResult.forEach(d -> System.out.println(d));
        
        // 内部类实现
        strResult = list.stream().filter(d -> d.getTime() < System.currentTimeMillis()).map(new Function<Date, String>() {
            @Override
            public String apply(Date d)
            {
                return s.format(d);
            }
        });
        
        strResult.forEach(d -> System.out.println(d));
    }
    
    // filter方法，它可以接受表示操作的Predicate实现来使用定义了过滤条件的lambda表达式。
    // 返回符合指定条件的记录
    public void filter(List<Date> list)
    {
        // 获取早于当前时间的记录
        Stream<Date> beforeNow = list.stream().filter(d -> d.getTime() < System.currentTimeMillis());
        // 所有当前时间后的记录
        beforeNow.forEach(d -> System.out.println(d));
        System.out.println();
        // stream().filter 没有改变原有集合内部状态
        list.forEach(d -> System.out.println(d));
        
        // 内部类实现
        beforeNow = list.stream().filter(new Predicate<Date>() {
            @Override
            public boolean test(Date d)
            {
                return d.getTime() < System.currentTimeMillis();
            }
        });
        System.out.println();
        // 所有当前时间后的记录
        beforeNow.forEach(d -> System.out.println(d));
        System.out.println();
        // stream().filter 没有改变原有集合内部状态
        list.forEach(d -> System.out.println(d));
        
        list.forEach(System.out::println);
        
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        Integer t = primes.stream().filter(i -> i == 13).findFirst().orElse(null);
        System.out.println(t);
    }
    
}
