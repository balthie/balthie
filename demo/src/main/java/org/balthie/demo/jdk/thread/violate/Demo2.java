package org.balthie.demo.jdk.thread.violate;

// 使用 violate修饰 count，仍然达不到线程 变量同步的效果
// http://www.cnblogs.com/aigongsi/archive/2012/04/01/2429166.html
// 但是这一些操作并不是原子性，也就是 在read load之后，如果主内存count变量发生修改之后，线程工作内存中的值由于已经加载，不会产生对应的变化，所以计算出来的结果会和预期不一样

// 与 ThreadLocal的区别： violate变量最终会与主线程同步， threadlocal变量每个线程中都是独立的
public class Demo2
{
    public static int count = 0;
    
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
        count++;
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
                    Demo2.inc();
                }
            }).start();
        }
        
        // 这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Demo2.count);
    }
}
