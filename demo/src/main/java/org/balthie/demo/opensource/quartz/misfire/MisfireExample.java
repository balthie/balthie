package org.balthie.demo.opensource.quartz.misfire;

import java.util.Date;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务场景 ： 触发器设定每3秒钟触发一次 ,但是工作需要10秒钟的执行时间.因此，在一次任务结束执行前，触发器已经错失触发
 * 由于丢失触发时，job2的策略是立即触发，而job1是等待下一次机会触发。所以job2会赶在job1的前头，最终运行次数大于job1。 实际执行，两个执行计划 采用的是同一个 misfire
 * 策略， 增加第三个执行计划，手工指定misfire策略为  withMisfireHandlingInstructionNextWithExistingCount
 * 
 * result： 执行到40次以后才有差别， job1和job3保持同步， job2执行次数大于前两者
 * 
 * SimpleTrigger
 * 
 * withMisfireHandlingInstructionFireNow ——以当前时间为触发频率立即触发执行 ——执行至FinalTIme的剩余周期次数
 * ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到 ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值
 * 
 * withMisfireHandlingInstructionIgnoreMisfires ——以错过的第一个频率时间立刻开始执行 ——重做错过的所有频率周期
 * ——当下一次触发频率发生时间大于当前时间以后，按照Interval的依次执行剩下的频率 ——共执行RepeatCount+1次
 * 
 * withMisfireHandlingInstructionNextWithExistingCount ——不触发立即执行 ——等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
 * ——以startTime为基准计算周期频率，并得到FinalTime ——即使中间出现pause，resume以后保持FinalTime时间不变
 * 
 * 
 * withMisfireHandlingInstructionNowWithExistingCount ——以当前时间为触发频率立即触发执行 ——执行至FinalTIme的剩余周期次数
 * ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到 ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值
 * 
 * withMisfireHandlingInstructionNextWithRemainingCount ——不触发立即执行 ——等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
 * ——以startTime为基准计算周期频率，并得到FinalTime ——即使中间出现pause，resume以后保持FinalTime时间不变
 * 
 * withMisfireHandlingInstructionNowWithRemainingCount ——以当前时间为触发频率立即触发执行 ——执行至FinalTIme的剩余周期次数
 * ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到 ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值
 * MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT
 * ——此指令导致trigger忘记原始设置的starttime和repeat-count ——触发器的repeat-count将被设置为剩余的次数
 * ——这样会导致后面无法获得原始设定的starttime和repeat-count值
 */
public class MisfireExample
{
    public void run() throws Exception
    {
        Logger log = LoggerFactory.getLogger(MisfireExample.class);
        
        log.info("------- Initializing -------------------");
        
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        log.info("------- Initialization Complete -----------");
        
        log.info("------- Scheduling Jobs -----------");
        
        /*
         * Job #1 is scheduled to run every 3 seconds indefinitely. An execution delay of 10 seconds
         * is passed into the job:
         */
        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
        
        JobDetail job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob1", "group1")
                .usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
        
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
        /*
         * Job #1 uses the default “smart” misfire policy for simple triggers, which causes the
         * trigger to fire at it’s next normal execution time. 默认的恢复方式
         * MISFIRE_INSTRUCTION_SMART_POLICY =0，具体策略由实现类定义
         */
        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
        
        Date ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey()
                + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + (trigger.getRepeatInterval() / 1000L) + " seconds with MisfireInstruction = " + trigger.getMisfireInstruction());
        
        /*
         * Job #2 is scheduled to run every 3 seconds indefinitely. An execution delay of 10 seconds
         * is passed into the job:
         */
        job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob2", "group1")
        /*
         * usingJobData(key, value) Add the given key-value pair to the JobDetail's JobDataMap.
         */.usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
        
        trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()
                /*
                 * job #2 is set with a misfire instruction that will cause it to reschedule with
                 * the existing repeat count. This policy forces Quartz to refire the trigger as
                 * soon as possible.
                 */
                .withMisfireHandlingInstructionNowWithExistingCount()).build();
        
        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey()
                + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + (trigger.getRepeatInterval() / 1000L) + " seconds with MisfireInstruction = " + trigger.getMisfireInstruction());
        
        /*
         * Job #3 is scheduled to run every 3 seconds indefinitely. An execution delay of 10 seconds
         * is passed into the job:
         */
        job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob3", "group1")
        /*
         * usingJobData(key, value) Add the given key-value pair to the JobDetail's JobDataMap.
         */.usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
        
        trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()
                /*
                 * job #3 is set with a misfire instruction that will cause it to reschedule with
                 * the existing repeat count. This policy forces Quartz to refire the trigger at
                 * next fire time
                 */
                .withMisfireHandlingInstructionNextWithExistingCount()).build();
        
        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey()
                + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + (trigger.getRepeatInterval() / 1000L) + " seconds with MisfireInstruction = " + trigger.getMisfireInstruction());
        
        log.info("------- Starting Scheduler ----------------");
        
        sched.start();
        
        log.info("------- Started Scheduler -----------------");
        try
        {
            Thread.sleep(600000L);
        }
        catch (Exception e)
        {
        }
        log.info("------- Shutting Down ---------------------");
        
        sched.shutdown(true);
        
        log.info("------- Shutdown Complete -----------------");
        
        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }
    
    public static void main(String[] args) throws Exception
    {
        MisfireExample example = new MisfireExample();
        example.run();
    }
}