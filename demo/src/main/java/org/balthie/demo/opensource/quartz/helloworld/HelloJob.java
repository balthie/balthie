/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月5日 下午5:32:05
 * @description TODO 一句话描述
 */
package org.balthie.demo.opensource.quartz.helloworld;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job
{
    
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        // Say Hello to the World and display the date/time
        System.out.println("Hello World! - " + new Date());
    }
}
