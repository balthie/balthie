package org.balthie.demo.opensource.quartz.cluster.jdbc;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleRecoveryJob implements Job
{
    private static Logger _log = LoggerFactory.getLogger(SimpleRecoveryJob.class);
    
    private static final String COUNT = "count";
    
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        JobKey jobKey = context.getJobDetail().getKey();
        
        String threadName = Thread.currentThread().getName();
        Scheduler schedule = context.getScheduler();
        
        if(context.isRecovering())
            _log.info( schedule +" : "+ threadName + "  ----> SimpleRecoveryJob: " + jobKey + " RECOVERING ");
        else
        {
            _log.info(schedule +" : "+ threadName + "  ----> SimpleRecoveryJob: " + jobKey + " starting ");
        }
        
        long delay = 10000L;
        try
        {
            Thread.sleep(delay);
        }
        catch (Exception e)
        {
        }
        JobDataMap data = context.getJobDetail().getJobDataMap();
        int count;
        if(data.containsKey(COUNT))
            count = data.getInt(COUNT);
        else
        {
            count = 0;
        }
        ++count;
        data.put("count", count);
        
        _log.info(schedule +" : " + threadName + "  ----> SimpleRecoveryJob: " + jobKey + " done Execution #" + count);
    }
}