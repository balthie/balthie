package org.balthie.demo.opensource.quartz.cron;

import java.util.Date;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CronTriggerExample
{
    Logger log = LoggerFactory.getLogger(CronTriggerExample.class);
    
    public void run() throws Exception
    {
        
        log.info("------- Initializing -------------------");
        
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        log.info("------- Initialization Complete --------");
        
        log.info("------- Scheduling Jobs ----------------");
        
        // 每月最后一日
        addJob2Scheduler(sched, "job0", "trigger0", "group1", "0 1 16 L * ?");
        
        // 创建 group1 同组任务 job1 Job #1 is scheduled to run every 20 seconds
        addJob2Scheduler(sched, "job1", "trigger1", "group1", "0/20 * * * * ?");
        
        // 创建 group1 同组任务 job2 Job #2 is scheduled to run every other minute starting at 15 seconds
        // past the minute.
        addJob2Scheduler(sched, "job2", "trigger2", "group1", "15 0/2 * * * ?");
        
        // 创建 group1 同组任务 job3 Job #3 is scheduled to run every other minute between 8am and 5pm (17
        // :00).
        addJob2Scheduler(sched, "job3", "trigger3", "group1", "0 0/2 8-17 * * ?");
        
        // 创建 group1 同组任务 job4 Job #4 is scheduled to run every three minutes, but only between 5pm
        // and 11pm
        addJob2Scheduler(sched, "job4", "trigger4", "group1", "0 0/3 17-23 * * ?");
        
        // 创建 group1 同组任务 job5 Job #5 is scheduled to run at 10am on the 1st and 15th days of the
        // month
        addJob2Scheduler(sched, "job5", "trigger5", "group1", "0 0 10am 1,15 * ?");
        
        // 创建 group1 同组任务 job6 Job #6 is scheduled to run every 30 seconds on weekdays (Monday
        // through Friday)
        addJob2Scheduler(sched, "job6", "trigger6", "group1", "0,30 * * ? * MON-FRI");
        
        // 创建 group1 同组任务 job7 Job #7 is scheduled to run every 30 seconds on weekends (Saturday and
        // Sunday)
        addJob2Scheduler(sched, "job7", "trigger7", "group1", "0,30 * * ? * SAT,SUN");
        
        log.info("------- Starting Scheduler ----------------");
        
        sched.start();
        
        log.info("------- Started Scheduler -----------------");
        
        log.info("------- Waiting five minutes... ------------");
        try
        {
            Thread.sleep(300000L);
        }
        catch (Exception e)
        {
        }
        
        log.info("------- Shutting Down ---------------------");
        
        sched.shutdown(true);
        
        log.info("------- Shutdown Complete -----------------");
        
        // 执行计划 数据类
        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2015年1月6日 上午10:03:07
     */
    public void addJob2Scheduler(Scheduler sched, String jobName, String triggerName, String groupName, String cronExp)
            throws SchedulerException
    {
        JobDetail job;
        CronTrigger trigger;
        Date ft;
        job = JobBuilder.newJob(SimpleJob.class).withIdentity(jobName, groupName).build();
        
        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(triggerName, groupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExp)).build();
                
        // TODO 返回首次任务首次触发时间 computeFirstFireTime
        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey()
                + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
    }
    
    public static void main(String[] args) throws Exception
    {
        CronTriggerExample example = new CronTriggerExample();
        example.run();
    }
}