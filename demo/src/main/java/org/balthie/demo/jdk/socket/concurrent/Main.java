package org.balthie.demo.jdk.socket.concurrent;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年6月11日 下午2:35:10
 * @description 模拟多个客户端同时通过socket向server传递数据
 * @since version 初始于版本 TODO 
 */
public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ConcurrentSocketClientTask task1 = new ConcurrentSocketClientTask("a");
        ConcurrentSocketClientTask task2 = new ConcurrentSocketClientTask("b");
        
        
        ExecutorService executor = Executors.newFixedThreadPool(20);
        System.out.println("begin task");
       
        // TODO 此处应该优化为所有线程同时启动
        executor.submit(task1);
        executor.submit(task2);
        for(int i=0;i<20;i++)
        {
            executor.submit(new ConcurrentSocketClientTask(i+""));
        }
        
        
        //executor.notifyAll();
        
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
