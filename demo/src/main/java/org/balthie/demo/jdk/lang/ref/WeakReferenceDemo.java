package org.balthie.demo.jdk.lang.ref;

import java.lang.ref.WeakReference;

// 弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象。在java中，用java.lang.ref.WeakReference类来表示。
public class WeakReferenceDemo
{
    public static void main(String[] args)
    {
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
        System.out.println(sr.get());
        System.gc(); // 通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }
}
