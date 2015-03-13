package org.balthie.demo.operplat.thread;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：姓名 + 工号
 * @createtime ： 2015年3月17日 上午9:22:50
 * @description 测试操作系统能容纳的最大线程数
 */
public class TestThread extends Thread
{
    private static final AtomicInteger count = new AtomicInteger();
    
    public static void main(String[] args)
    {
        while (true)
            (new TestThread()).start();
    }
    
    @Override
    public void run()
    {
        System.out.println(count.incrementAndGet());
        while (true)
            try
            {
                Thread.sleep(Integer.MAX_VALUE);
            }
            catch (InterruptedException e)
            {
                break;
            }
    }
}
