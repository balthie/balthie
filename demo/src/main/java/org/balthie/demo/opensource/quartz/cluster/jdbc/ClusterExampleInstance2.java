package org.balthie.demo.opensource.quartz.cluster.jdbc;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClusterExampleInstance2
{
    private static Logger _log = LoggerFactory.getLogger(ClusterExampleInstance2.class);
    
    public void run(boolean inClearJobs, boolean inScheduleJobs) throws Exception
    {
        SchedulerFactory sf = new StdSchedulerFactory("quartz/cluster/jdbc/instance2.properties");
        Scheduler sched = sf.getScheduler();
        
        if(inClearJobs)
        {
            _log.warn("***** Deleting existing jobs/triggers *****");
            sched.clear();
        }
        
        _log.info("------- Initialization Complete ----------- ClusterExampleInstance2 Scheduler = " + sched);
        
        
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
    
    public static void main(String[] args) throws Exception
    {
        boolean clearJobs = false;
        boolean scheduleJobs = false;
        
        for(String arg : args)
        {
            if(arg.equalsIgnoreCase("clearJobs"))
                clearJobs = true;
            else if(arg.equalsIgnoreCase("dontScheduleJobs"))
            {
                scheduleJobs = false;
            }
        }
        
        ClusterExampleInstance2 example = new ClusterExampleInstance2();
        example.run(clearJobs, scheduleJobs);
    }
}