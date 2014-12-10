/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月13日 上午11:06:15
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.quartz.execute;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.time.DateUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月13日 上午11:06:15
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class SchedulerFactoryBean
{
    private static final String MASTER_SCHEDULE_SERVER = "1";
    
    private static Logger LOGGER = LoggerFactory.getLogger(SchedulerFactoryBean.class);
    
    private Scheduler sched;
    
    private boolean isMaster;
    
    private Map<String , String> cronJob = new HashMap<String, String>();
    
    public void startExecute() throws SchedulerException
    {
        LOGGER.info(" SchedulerFactoryBean begin" );
        init();
        
        if(isMaster)
        {
            refreshJob();
        }
        
        sched.start();
        LOGGER.info(" SchedulerFactoryBean started" );
    }
    
    public void init() throws SchedulerException
    {
        StdSchedulerFactory sf = new StdSchedulerFactory("conf/quartz/cluster/instance.properties");
        sched = sf.getScheduler();
        
        String masterServer = System.getProperty("ti.shedule.master", "");
        
        isMaster = MASTER_SCHEDULE_SERVER.equalsIgnoreCase(masterServer);
    }
    
    @SuppressWarnings("unchecked")
    public void refreshJob() throws SchedulerException
    {
        LOGGER.warn("***** Deleting existing jobs/triggers *****");
        sched.clear();
        LOGGER.info("------- clear Complete ----------- ClusterMasterInstance Scheduler = " + sched);
        
        Class<? extends Job> clazz = null;
        Date triggerStartAt = null;
        Date now = new Date();
        if(cronJob!=null && cronJob.size()>0)
        {
            int i = 0;
            for(Map.Entry<String , String> entry : cronJob.entrySet())
            {
                try
                {
                    triggerStartAt = DateUtils.addMinutes(now, i++);
                    clazz = ((Class<? extends Job>) SchedulerFactoryBean.class.getClassLoader().loadClass(entry.getKey()));
                    scheduleJob(clazz, entry.getValue(), triggerStartAt);
                }
                catch (Exception e)
                {
                    LOGGER.error(" refreshJob job fail, cause :", e);
                }
            }
        }
    }
    
    private void scheduleJob(Class<? extends Job> jobClazz, String cronExp, Date triggerStartAt) throws SchedulerException
    {
        JobDetail job;
        CronTrigger trigger;
        
        String jobName = jobClazz.getName();
        jobName = jobName.substring(jobName.lastIndexOf(".")+1);
        String groupName = jobName+"_group";
        String triggerName = jobName +"_trigger";
        
        job = JobBuilder.newJob(jobClazz).withIdentity(jobName, groupName).build();
        
        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(triggerName, groupName).startAt(triggerStartAt)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExp)).build();
        
        // TODO 返回首次任务首次触发时间 computeFirstFireTime
        Date ft = sched.scheduleJob(job, trigger);
        LOGGER.info(job.getKey()
                + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
    }

    public void scheduleJob( Class<? extends Job> jobClazz, String cronExp)
            throws SchedulerException
    {
        scheduleJob(jobClazz, cronExp, new Date());
    }
    
    public void stopExecute() throws SchedulerException
    {
        this.sched.shutdown(true);
    }

    public Map<String, String> getCronJob()
    {
        return cronJob;
    }

    public void setCronJob(Map<String, String> cronJob)
    {
        this.cronJob = cronJob;
    }
}
