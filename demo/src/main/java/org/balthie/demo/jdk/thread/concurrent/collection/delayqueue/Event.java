/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午4:17:02
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.delayqueue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月2日 下午4:17:02
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class Event implements Delayed
{
    private Date startDate;

    public Event(Date startDate)
    {
        super();
        this.startDate = startDate;
    }

    @Override
    public int compareTo(Delayed o)
    {
        // 排序逻辑，时间晚的放列表后面
        long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        if(result<0)
        {
            return -1;
        }
        else if(result>0)
        {
            return 1;
        }
        return 0;
    }

    // DelayQueue 类本身是按纳秒工作的，此处计算激活日期和当前日期间的纳秒数
    @Override
    public long getDelay(TimeUnit unit)
    {
        Date now = new Date();
        long diff = startDate.getTime() - now.getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
}
