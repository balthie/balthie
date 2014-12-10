/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月6日 上午9:44:35
 * @description TODO 一句话描述
 */
package org.balthie.demo.opensource.quartz.cron;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月6日 上午9:44:35
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class SimpleJob implements Job
{
    private static Logger _log = LoggerFactory.getLogger(SimpleJob.class);
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        JobKey jobKey = context.getJobDetail().getKey();
        _log.info("SimpleJob says: " + jobKey + " executing at " + new Date());
    }
}
