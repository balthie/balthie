/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:29:15
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.lock;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:29:15
 * @description 模拟一个单线程的打印机
 * @since version 初始于版本 TODO
 */
public class Printer
{
    private final Lock lock = new ReentrantLock(true);
    
    public void print(Object document)
    {
        // 一直等待，直到获得锁
        // lock.lock();
        
        // 获取锁失败，立即抛出中断异常
        // lock.lockInterruptibly();
         
        // 在超时时间内等待获取锁，超时抛出中断异常
        // lock.tryLock(expTimeMills, TimeUnit.MILLISECONDS);
         
        lock.lock();
        
        try
        {
            Long duration = (long) (Math.random() * 10);
            System.out.println(MessageFormat.format("【{0}】printJob begin at【{1, time, HH:mm:ss:ms}】, duration 【{2,number,##}】",
                    Thread.currentThread().getName(), new Date(), duration));
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
        
        System.out.println(MessageFormat.format("【{0}】printJob end at【{1, time, HH:mm:ss:ms}】", Thread.currentThread().getName(),
                new Date()));
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
