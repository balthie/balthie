package org.balthie.demo.jvm.keyword.volatiletest;

// http://www.cnblogs.com/tangyanbo/p/6538488.html
// jvm指定 -server模式，会出现死循环 ，可能是允许重排 

//1. 对volatile变量的写会立即刷新到主存
//2. 对volatile变量的读会读主存中的新值
// 但是volatile并不会通知各个线程去改变 线程中该变量的值，所以volatile 并不会有锁的特性

// volatile不负责线程间变量状态变更的通信（不会通知线程变量已变更）
public class WontCommunicateThreadTest implements Runnable
{
    boolean running = true;
    
    int i = 0;
    
    public static void main(String[] args) throws InterruptedException
    {
        WontCommunicateThreadTest test = new WontCommunicateThreadTest();
        Thread t = new Thread(test);
        
        t.start();
        Thread.sleep(10);
        test.running = false;
        Thread.sleep(100);
        System.out.println(test.i);
        System.out.println("stop");
    }
    
    @Override
    public void run()
    {
        while (running)
        {
            i++;
        }
    }
}
