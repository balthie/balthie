/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月26日 上午10:43:00
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.schedule;

import java.util.concurrent.TimeUnit;
import org.balthie.springmvc.common.BaseSpringTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月26日 上午10:43:00
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class ScheduleDemoTest extends BaseSpringTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleDemoTest.class);
    
    @Test
    public void startTest() throws InterruptedException
    {
        LOGGER.debug(" ScheduleDemoTest startTest");
        LOGGER.info(" ScheduleDemoTest startTest");
        LOGGER.error(" ScheduleDemoTest startTest");
        
        TimeUnit.SECONDS.sleep(12000);
        System.out.println("startTest end ");
    }
}
