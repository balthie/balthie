package org.balthie.demo.opensource.quartz.misfire;

import java.util.Date;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulDumbJob implements Job
{
    public static final String NUM_EXECUTIONS = "NumExecutions";
    
    public static final String EXECUTION_DELAY = "ExecutionDelay";
    
    Logger log = LoggerFactory.getLogger(StatefulDumbJob.class);
    
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        
        //System.err.println("---" + context.getJobDetail().getKey() + " executing.[" + new Date() + "]");
        log.warn( Thread.currentThread().getName() + "  ---" + context.getJobDetail().getKey() + " executing.[" + new Date() + "]");
        
        JobDataMap map = context.getJobDetail().getJobDataMap();
        
        int executeCount = 0;
        if(map.containsKey("NumExecutions"))
        {
            executeCount = map.getInt("NumExecutions");
        }
        
        /*
         * Because the class has the PersistJobDataAfterExecution annotation, the execution count is
         * preserved between each execution.
         */
        ++executeCount;
        map.put("NumExecutions", executeCount);
        
        long delay = 5000L;
        if(map.containsKey("ExecutionDelay"))
        {
            delay = map.getLong("ExecutionDelay");
        }
        try
        {
            Thread.sleep(delay);
        }
        catch (Exception ignore)
        {
        }
        log.warn(Thread.currentThread().getName() +"  -" + context.getJobDetail().getKey() + " complete (" + executeCount + ").");
        //System.err.println("  -" + context.getJobDetail().getKey() + " complete (" + executeCount + ").");
    }
}