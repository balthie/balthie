/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午5:21:12
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.delayqueue;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午5:21:12
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class BootStrap
{
    public static void main(String[] args) throws InterruptedException
    {
        DelayQueue<Event> queue = new DelayQueue<Event>();
        
        Thread[] threads = new Thread[5];
        for(int i = 0; i < threads.length; i++)
        {
            Task task = new Task(i + 1, queue);
            threads[i] = new Thread(task);
        }
        for(int i = 0; i < threads.length; i++)
        {
            threads[i].start();
            threads[i].join();
        }
        
        do
        {
            int counter = 0;
            Event event;
            do
            {
                event = queue.poll();
                if(event != null)
                {
                    counter++;
                }
            }
            while (event != null);
            // 当达到元素的激活时间以后，poll方法才能取得元素
            System.out.println(MessageFormat.format("at【{0, time, HH:mm:ss:ms}】  read 【{1}】 events , queue size【{2}】",
                    new Date(), counter, queue.size()));
            TimeUnit.MILLISECONDS.sleep(500);
        }
        while (queue.size() > 0);
    }
}
