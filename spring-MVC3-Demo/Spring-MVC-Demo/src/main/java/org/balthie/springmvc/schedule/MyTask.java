/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月26日 下午2:57:09
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.schedule;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月26日 下午2:57:09
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class MyTask
{
    public static void main(String[] args)
    {
        System.out.println(System.currentTimeMillis());
    }
    
    // 每 1000 ms
    @Scheduled(fixedRate = 1000)
    public void work()
    {
        // System.out.println(" @Scheduled task invoke ");
    }
    
    // 每10秒
    //@Scheduled(cron = "0/10 * * * * ?")
    public void workPer10Second()
    {
        System.out.println(" @Scheduled workPer10Second task invoke ");
    }
    
    // 每分钟
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void workPerMinute()
    {
        System.out.println(" @Scheduled workPerMinute task invoke ");
    }
    
    // 每天指定时间
    @Scheduled(cron = "0 25 10 * * ?")
    public void workAtFixDate()
    {
        System.out.println(" @Scheduled workAtFixDate task invoke ");
    }
    
    // 9-17时内，每2分钟执行
    @Scheduled(cron = "0 0/2 9-17 * * ?")
    public void intervalAtFixPeriod()
    {
        System.out.println(" @Scheduled intervalAtFixPeriod task invoke ");
    }
    
    // 周一到周五10:15分执行
    @Scheduled(cron = "0 15 10 ? * MON-FRI")
    public void intervalBetweenMondayAndFriday()
    {
        System.out.println(" @Scheduled intervalBetweenMondayAndFriday task invoke ");
    }
    
    // 每月最后一天10:15分执行， spring版本问题，低版本可能报错   java.lang.IllegalStateException: For input string: "L"
    /*@Scheduled(cron = "0 15 10 L * ?")
    public void intervalAtMonthLastDay()
    {
        System.out.println(" @Scheduled intervalAtMonthLastDay task invoke ");
    }*/
}
