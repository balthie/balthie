/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午4:09:30
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.threadrandom;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午4:09:30
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class TaskLocalRandom implements Runnable
{
    
    
    /**
     * 
     */
    public TaskLocalRandom()
    {
        super();
        ThreadLocalRandom.current();
    }

    @Override
    public void run()
    {
       for(int i=0;i<10;i++)
       {
           System.out.println(MessageFormat.format("【{0}】gen random 【{1}】",
                    Thread.currentThread().getName(), ThreadLocalRandom.current().nextInt(100)));
       }
    }
    
    public static void main(String[] args)
    {
        Thread[] threads = new Thread[3];
        
        for(int i=0;i<3;i++)
        {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }
    }
}
