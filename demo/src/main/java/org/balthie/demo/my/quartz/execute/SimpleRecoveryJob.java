package org.balthie.demo.my.quartz.execute;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SimpleRecoveryJob implements Job
{
    private static Logger LOGGER = LoggerFactory.getLogger(SimpleRecoveryJob.class);
    
    protected static final String COUNT = "count";
    
    public abstract void doJobBusiness();
    
    protected long beforeExecute(JobExecutionContext context)
    {
        JobKey jobKey = context.getJobDetail().getKey();
        String threadName = Thread.currentThread().getName();
        Scheduler schedule = context.getScheduler();
        
        if(context.isRecovering())
            LOGGER.debug(String.format(" %s【%s】  ----> %s RECOVERING ", schedule, threadName, jobKey));
        else
        {
            LOGGER.debug(String.format(" %s【%s】  ----> %s STARTING ", schedule, threadName, jobKey));
        }
        
        return System.currentTimeMillis();
    }
    
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        long begin = beforeExecute(context);
        JobKey jobKey = context.getJobDetail().getKey();
        
        LOGGER.info(String.format(" job【%s】 starting ", jobKey));
        doJobBusiness();
        
        long end = afterExecute(context);
        
        LOGGER.info(String.format(" job【%s】 ended , during【%d】ms ", jobKey, (end - begin)));
    }
    
    protected long afterExecute(JobExecutionContext context)
    {
        JobKey jobKey = context.getJobDetail().getKey();
        String threadName = Thread.currentThread().getName();
        Scheduler schedule = context.getScheduler();
        
        JobDataMap data = context.getJobDetail().getJobDataMap();
        int count;
        if(data.containsKey(COUNT))
            count = data.getInt(COUNT);
        else
        {
            count = 0;
        }
        ++count;
        data.put(COUNT, count);
        LOGGER.info(String.format(" %s【%s】  ----> %s done Execution times #【%d】 ", schedule, threadName, jobKey, count));
        
        return System.currentTimeMillis();
    }
}