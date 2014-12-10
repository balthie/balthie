/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月5日 下午5:50:30
 * @description TODO 一句话描述
 */
package org.balthie.demo.opensource.quartz.helloworld;

import java.util.Date;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月5日 下午5:50:30
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class SimpleExample
{
    public void run() throws Exception
    {
        Logger log = LoggerFactory.getLogger(SimpleExample.class);
        
        log.info("------- Initializing ----------------------");
        
        // getting an instance of the Scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        log.info("------- Initialization Complete -----------");
        
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        
        log.info("------- Scheduling Job  -------------------");
        
        // JobDetail： 真正的任务内容，任务本身是集成Job接口的，但是真正的任务是JobBuilder通过反射的方式实例化的，
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
        
        // Trigger：
        // 触发器，定义任务应当开始的时间，主要分为两类SimpleTrigger,CronTrigger，当前例子的就是简单触发器，CronTrigger主要用于处理quartz表达式定义的任务，比如每个月20号，每个星期一之类的。
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
        
        // 将任务和Trigger放入scheduler    
        sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + runTime);
        
        sched.start();
        
        log.info("------- Started Scheduler -----------------");
        
        log.info("------- Waiting 65 seconds... -------------");
        try
        {
            Thread.sleep(65000L);
        }
        catch (Exception e)
        {
        }
        
        log.info("------- Shutting Down ---------------------");
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");
    }
    
    public static void main(String[] args) throws Exception
    {
        SimpleExample example = new SimpleExample();
        example.run();
    }
}
