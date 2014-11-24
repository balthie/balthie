package org.balthie.demo.jdk.thread.executor.demo1;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable
{
    private Date date;
    
    private String name;
    
    public Task(Date date, String name)
    {
        super();
        this.date = date;
        this.name = name;
        System.out.println(MessageFormat.format("【{0}】Task【{1}】: created on 【{2,time, HH:mm:ss:ms}】", Thread.currentThread().getName(), name, date));
    }

    @Override
    public void run()
    {
        System.out.println(MessageFormat.format("【{0}】Task【{1}】: begin task at 【{2,time, HH:mm:ss:ms}】", Thread.currentThread().getName(), name, new Date()));
        
        try
        {
            Long duration = (long) (Math.random()*10);
            System.out.println(MessageFormat.format("【{0}】Task【{1}】: doing a task during 【{2,number,###}】", Thread.currentThread().getName(), name, duration));
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        System.out.println(MessageFormat.format("【{0}】Task【{1}】: end task at 【{2,time, HH:mm:ss:ms}】", Thread.currentThread().getName(), name, new Date()));
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
