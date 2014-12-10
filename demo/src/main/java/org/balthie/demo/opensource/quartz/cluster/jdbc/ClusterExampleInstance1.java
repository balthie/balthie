package org.balthie.demo.opensource.quartz.cluster.jdbc;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 问题1： Exception in thread "main" org.quartz.ObjectAlreadyExistsException: Unable to store Job :
 * 'instance_one.job_1', because one already exists with this identification.
 * 解决：执行计划异常终止后，重新启动时需要清理未执行的任务 需要调用 sched.clear()方法，清理
 * 
 */
public class ClusterExampleInstance1
{
    private static Logger _log = LoggerFactory.getLogger(ClusterExampleInstance1.class);
    
    public void run(boolean inClearJobs, boolean inScheduleJobs) throws Exception
    {
        SchedulerFactory sf = new StdSchedulerFactory("quartz/cluster/jdbc/instance1.properties");
        Scheduler sched = sf.getScheduler();
        
        
        if(inClearJobs)
        {
            _log.warn("***** Deleting existing jobs/triggers *****");
            sched.clear();
        }
        
        _log.info("------- Initialization Complete ----------- ClusterExampleInstance1 Scheduler = " + sched);
        
        if(inScheduleJobs)
        {
            _log.info("------- Scheduling Jobs ------------------");
            
            int count = 1;
            // job1
            scheduleJob(sched, count);
            // job2
            scheduleJob(sched, ++count);
            // job3
            scheduleJob(sched, ++count);
            // job4
            scheduleJob(sched, ++count);
            // job5
            scheduleJob(sched, ++count);
        }
        
        _log.info("------- Starting Scheduler ---------------");
        sched.start();
        _log.info("------- Started Scheduler ----------------");
        
        _log.info("------- Waiting for one hour... ----------");
        try
        {
            Thread.sleep(3600000L);
        }
        catch (Exception e)
        {
        }
        _log.info("------- Shutting Down --------------------");
        sched.shutdown();
        _log.info("------- Shutdown Complete ----------------");
    }
    
    public void scheduleJob(Scheduler sched, int count) throws SchedulerException
    {
        String schedId = sched.getSchedulerInstanceId();
        
        JobDetail job = JobBuilder.newJob(SimpleRecoveryStatefulJob.class).withIdentity("job_" + count, schedId).requestRecovery().usingJobData("count", 0)
                .build();
        
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();
        
        _log.info(job.getKey()
                + " will run at: " + trigger.getNextFireTime() + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + (trigger.getRepeatInterval() / 1000L) + " seconds");
        
        sched.scheduleJob(job, trigger);
    }
    
    public static void main(String[] args) throws Exception
    {
        boolean clearJobs = true;
        boolean scheduleJobs = true;
        
        for(String arg : args)
        {
            if(arg.equalsIgnoreCase("clearJobs"))
                clearJobs = true;
            else if(arg.equalsIgnoreCase("dontScheduleJobs"))
            {
                scheduleJobs = false;
            }
        }
        
        ClusterExampleInstance1 example = new ClusterExampleInstance1();
        example.run(clearJobs, scheduleJobs);
    }
}