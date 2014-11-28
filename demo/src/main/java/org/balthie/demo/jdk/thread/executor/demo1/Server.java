/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月26日 上午10:16:44
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.executor.demo1;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月26日 上午10:16:44
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class Server
{
    private ThreadPoolExecutor executor;
    
    /**
     * @param executor
     */
    public Server()
    {
        super();
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        
        // 任务调度执行器
        // ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(2);
        // scheduleService.schedule(callable, delay, unit);
        // scheduleService.scheduleAtFixedRate(command, initialDelay, period, unit);
    }
    
    public void executeTask(Task task)
    {
        System.out.println(MessageFormat.format("Server: Task【{0}】 arrived at 【{1, time, HH:mm:ss:ms}】", task.getName(),
                new Date()));
        
        executor.execute(task);
        
        // 线程池总线程数量
        System.out.println(MessageFormat.format("Server: pool size【{0}】", executor.getPoolSize()));
        // 正在执行的线程数量
        System.out.println(MessageFormat.format("Server: Active count【{0}】", executor.getActiveCount()));
        // 执行器已完成的线程数量
        System.out.println(MessageFormat.format("Server: completed Tasks count【{0}】", executor.getCompletedTaskCount()));
        
    }
    
    public void endServer()
    {
        executor.shutdown();
        // 如果调用了executor的 shutDown或 shutdownNow ，并且执行完成关闭过程，将返回true
        System.out.println(MessageFormat.format("Server: isTerminated【{0}】", executor.isTerminated()));
        
        try
        {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(MessageFormat.format("Server: isTerminated【{0}】", executor.isTerminated()));
    }
    
    public static void main(String[] args)
    {
        Server server = new Server();
        for(int i = 0; i < 100; i++)
        {
            Task t = new Task(new Date(), " Task " + i);
            server.executeTask(t);
            
            if(i % 10 == 0)
            {
                try
                {
                    // 此方法将堵塞未开始执行的线程，直到所有？？已经开始执行的线程？？执行完成，或者到达超时时间
                    System.out.println(MessageFormat.format("Server: awaitTermination begin at 【{0, time, HH:mm:ss:ms}】",
                            new Date()));
                    server.executor.awaitTermination(10, TimeUnit.SECONDS);
                    System.out.println(MessageFormat.format("Server: awaitTermination end at 【{0, time, HH:mm:ss:ms}】",
                            new Date()));
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        server.endServer();
    }
    
}
