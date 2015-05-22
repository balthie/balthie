package org.balthie.demo.jdk.lang.ref;

import java.lang.ref.SoftReference;

// 对于软引用关联着的对象，只有在内存不足的时候JVM才会回收该对象。因此，这一点可以很好地用来解决OOM的问题，并且这个特性很适合用来实现缓存：比如网页缓存、图片缓存等。
public class SoftReferenceDemo
{
    
    public static void main(String[] args)
    {
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
        
        sr = new SoftReference<String>(null);
        System.out.println(sr.get());
    }
}
