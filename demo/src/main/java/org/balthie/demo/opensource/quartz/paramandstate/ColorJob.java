package org.balthie.demo.opensource.quartz.paramandstate;

import java.util.Date;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//An annotation that marks a Job class as one that makes updates to its JobDataMap during execution, and wishes the scheduler to re-store the JobDataMap when execution completes.
@PersistJobDataAfterExecution

@DisallowConcurrentExecution
public class ColorJob implements Job
{
    private static Logger _log = LoggerFactory.getLogger(ColorJob.class);
    
    public static final String FAVORITE_COLOR = "favorite color";
    
    public static final String EXECUTION_COUNT = "count";
    
    // 内置计数器，job执行一次自增1
    private int _counter = 1;
    
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        JobKey jobKey = context.getJobDetail().getKey();
        
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String favoriteColor = data.getString("favorite color");
        int count = data.getInt("count");
        _log.info("ColorJob: "
                + jobKey + " executing at " + new Date() + "\n" + "  favorite color is " + favoriteColor + "\n"
                + "  execution count (from job map) is " + count + "\n" + "  execution count (from job member variable) is "
                + this._counter);
        
        // JobExecutionContext 数据来源于 JobDetail.JobDataMap 每次执行时 JobDetail是同一个实例，所以计数器生效
        ++count;
        data.put("count", count);
        
        // its count will always be displayed as “1” because Quartz will always instantiate a new instance of the class
        // during each execution. This prevents member variables from being used to maintain state.
        this._counter += 1;
    }
}