/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:29:15
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.lock;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:29:15
 * @description  模拟一个单线程的打印机
 * @since version 初始于版本 TODO 
 * 
 * http://blog.csdn.net/arkblue/article/details/6138598
 * 
 * java concurrent包 ReentrantLock锁的 cas实现
http://www.cnblogs.com/MichaelPeng/archive/2010/02/12/1667947.html

 * @1 reentrant 锁意味着什么呢？简单来说，它有一个与锁相关的获取计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放。
 * @2 锁机制采用了操作系统 cas原语实现
 * @3 lock 必须在 finally 块中释放。否则，如果受保护的代码将抛出异常，锁就有可能永远得不到释放！
 */
public class TimeOutPrinter extends Printer
{
    private final Lock lock = new ReentrantLock(true);
    
    public void print(Object document)
    {
        trylock();
        
        try
        {
            Long duration = (long) (Math.random() *10);
            System.out.println(MessageFormat.format("【{0}】printJob begin at【{1, time, HH:mm:ss:ms}】, duration 【{2,number,##}】", Thread
                        .currentThread().getName(), new Date(), duration));
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                lock.unlock();
            }
            catch (Exception e)
            {
            }
        }
        
        System.out.println(MessageFormat.format("【{0}】printJob end at【{1, time, HH:mm:ss:ms}】", Thread
                .currentThread().getName(), new Date()));
    }

    private void trylock()
    {
        boolean f = false;
        try
        {
            f = lock.tryLock(3,TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if(!f)
        {
            System.out.println(MessageFormat.format("【{0}】printJob getLock timeout at【{1, time, HH:mm:ss:ms}】, duration 【{2,number,##}】", Thread
                    .currentThread().getName(), new Date()));
        }
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        TimeOutPrinter printer = new TimeOutPrinter();
        
        Thread[] thread = new Thread[10];
        
        for(int i=0;i<10;i++)
        {
            thread[i] = new Thread(new PrintJob(printer));
        }
        
        for(int i=0;i<10;i++)
        {
            TimeUnit.MILLISECONDS.sleep(50);
            thread[i].start();
        }
    }
}
