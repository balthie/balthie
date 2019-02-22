package org.balthie.demo.jdk.lang.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//  https://github.com/alibaba/transmittable-thread-local#-%E5%8A%9F%E8%83%BD
public class InheritableThreadLocalTest
{
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>();
    
    static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService excute = Executors.newFixedThreadPool(3);
        // 如果线程是在         inheritableThreadLocal 设值前初始化的，那本线程无法获取 设值
        excute.execute(new Runnable() {
            
            @Override
            public void run()
            {
                System.out.println(
                        Thread.currentThread().getName() + " -----  inheritable  ------ " + inheritableThreadLocal.get());
                
                System.out.println(Thread.currentThread().getName() + " ------ threadLocal  ----- " + threadLocal.get());
            }
        });
        
        inheritableThreadLocal.set("inheritableThreadLocal");
        System.out.println(Thread.currentThread().getName() + " -----  inheritable  ------ " + inheritableThreadLocal.get());
        
        threadLocal.set("threadLocal");
        System.out.println(Thread.currentThread().getName() + " ------ threadLocal  ----- " + threadLocal.get());
        
        new Thread(new Runnable() {
            
            @Override
            public void run()
            {
                System.out.println(
                        Thread.currentThread().getName() + " -----  inheritable  ------ " + inheritableThreadLocal.get());
                
                System.out.println(Thread.currentThread().getName() + " ------ threadLocal  ----- " + threadLocal.get());
            }
        }).start();
        ;
        
        for(int i = 0; i < 5; i++)
        {
            excute.execute(new Runnable() {
                
                @Override
                public void run()
                {
                    System.out.println(
                            Thread.currentThread().getName() + " -----  inheritable  ------ " + inheritableThreadLocal.get());
                    
                    System.out.println(Thread.currentThread().getName() + " ------ threadLocal  ----- " + threadLocal.get());
                }
            });
        }
        
        TimeUnit.SECONDS.sleep(2);
        excute.shutdown();
    }
}
