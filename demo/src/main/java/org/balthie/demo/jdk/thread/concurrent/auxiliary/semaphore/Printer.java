package org.balthie.demo.jdk.thread.concurrent.auxiliary.semaphore;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Printer
{
    private final Semaphore semaphore;

    public Printer()
    {
        super();
        // 提供两个资源访问计数器，最多同时有两个线程访问本资源
        this.semaphore = new Semaphore(2, true);
    }
    
    public void print(Object document)
    {
        try
        {
            // 获取信号量，当信号量为0时，线程被阻塞，直到有可用信号量
            semaphore.acquire();
            
            // 同时获取permits个信号量时，才允许继续访问
            //semaphore.acquire(permits);
            
            // 线程被阻塞的时间中，有可能中断导致acquire抛出 InterruptedException， 本方法不抛出任何异常 
            //semaphore.acquireUninterruptibly();
            long duration = (long) (Math.random()*10);
            System.out.println(MessageFormat.format("【{0}】printJob begin at【{1, time, HH:mm:ss:ms}】, duration 【{2,number,##}】",
                    Thread.currentThread().getName(), new Date(), duration));
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            semaphore.release();
            //释放permits个信号量
            //semaphore.release(permits);
        }
    }
    public static void main(String[] args) throws InterruptedException
    {
        Printer printer = new Printer();
        
        Thread[] thread = new Thread[10];
        
        for(int i = 0; i < 10; i++)
        {
            thread[i] = new Thread(new PrintJob(printer));
        }
        
        for(int i = 0; i < 10; i++)
        {
            TimeUnit.MILLISECONDS.sleep(50);
            thread[i].start();
        }
    }
}
