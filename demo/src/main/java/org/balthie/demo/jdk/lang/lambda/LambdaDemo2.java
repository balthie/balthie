package org.balthie.demo.jdk.lang.lambda;

import java.util.Date;
import java.util.function.Supplier;

// 构造函数 和  supplier 接口
public class LambdaDemo2
{
    public static void main(String[] args)
    {
        MyInterface i = Date::new;
        MyInterface i2 = ()-> new Date();
        System.out.println(i.getMeMyObject());
        System.out.println(i2.getMeMyObject());
        
        Supplier<Date> dateSupplier = Date::new;
        
        MyInterface2 j = Date::new;
        System.out.println(j.getMeMyObject(12367890123456L));
        
        Converter<String, Integer> converter = Integer::valueOf;
        Converter<String, Integer> converter2 = (from) -> Integer.valueOf(from);
    }
    
    @FunctionalInterface
    interface Converter<F, T>
    {
        T convert(F from);
    }
    
    @FunctionalInterface
    interface MyInterface
    {
        Date getMeMyObject();
    }
    
    @FunctionalInterface
    interface MyInterface2
    {
        Date getMeMyObject(long time);
    }
}
