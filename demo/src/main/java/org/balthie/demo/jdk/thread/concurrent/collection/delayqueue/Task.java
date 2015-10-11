/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午4:26:03
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.delayqueue;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午4:26:03
 * @description 像queue中添加100条随机失效时间的数据
 * @since version 初始于版本 TODO
 */
public class Task implements Runnable
{
    private int id;
    
    private DelayQueue<Event> queue;
    
    public Task(int id, DelayQueue<Event> queue)
    {
        super();
        this.id = id;
        this.queue = queue;
    }
    
    @Override
    public void run()
    {
        Date activatedTime = new Date();
        activatedTime.setTime(System.currentTimeMillis() + (id*1000));
        System.out.println(MessageFormat.format("【{0}】Task create 100 event at【{1, time, HH:mm:ss:ms}】, activatedTime【{2, time, HH:mm:ss:ms}】 ",
                Thread.currentThread().getName(), new Date(), activatedTime));
        for(int i=0;i<100;i++)
        {
            Event event = new Event(activatedTime);
            queue.add(event);
        }
    }
    
}
