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
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class Per10SecondJob extends SimpleRecoveryStatefulJob
{
    private static Logger LOGGER = LoggerFactory.getLogger(Per10SecondJob.class);

    @Override
    public void doJobBusiness()
    {
        try
        {
            LOGGER.info("Per10SecondJob some business task invoke");
            // do task
            TimeUnit.SECONDS.sleep(2);
            LOGGER.info("Per10SecondJob some business task end");
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
        }
    }
    
}
