/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月13日 上午11:39:58
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.quartz.job;

import java.util.concurrent.TimeUnit;
import org.balthie.demo.my.quartz.execute.SimpleRecoveryStatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月13日 上午11:39:58
 * @description 每分钟定时任务
 * @since version 初始于版本 V0.0.6
 */
public class PerMinuteJob extends SimpleRecoveryStatefulJob
{
    private static Logger LOGGER = LoggerFactory.getLogger(PerMinuteJob.class);
    
    @Override
    public void doJobBusiness()
    {
        try
        {
            LOGGER.info("PerMinuteJob some business task invoke");
            // do task
            TimeUnit.SECONDS.sleep(20);
            LOGGER.info("PerMinuteJob some business task end");
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
        }
    }
}
