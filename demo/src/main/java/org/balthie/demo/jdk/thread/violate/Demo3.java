package org.balthie.demo.jdk.thread.violate;

// ThreadLocal 变量，每个线程都是独立的
// static ThreadLocal 变量，线程内部是共享的
public class Demo3
{
    public static ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue()
        {
            return 0;
        }
    };
    
    public static void inc()
    {
        // 这里延迟1毫秒，使得结果明显
        try
        {
            Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
        
        }
        count.set(count.get() + 1);
        System.out.println(String.format("thread[%s] count[%s]", Thread.currentThread().getName(), count.get()));
    }
    
    public static void main(String[] args)
    {
        // 同时启动1000个线程，去进行i++计算，看看实际结果
        for(int i = 0; i < 1000; i++)
        {
            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    Demo3.inc();
                }
            }).start();
        }
        
        // 这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Demo3.count.get());
    }
}
