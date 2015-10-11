/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月27日 下午5:04:25
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月27日 下午5:04:25
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class TimeUnitDemo
{
    public static void main(String[] args)
    {
        Calendar c = Calendar.getInstance();
        System.out.println(new Date());
        c.setTime(new Date());
        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        c.set(Calendar.DAY_OF_MONTH,25);
        System.out.println(c.get(Calendar.WEEK_OF_YEAR));
        
        
        long timeMills = System.currentTimeMillis();
        System.out.println(" timeMills = " + timeMills);
        TimeUnit unit = TimeUnit.NANOSECONDS;
        long nanoMills = unit.convert(timeMills, TimeUnit.MILLISECONDS);
        System.out.println(" timeNanoMills = " + nanoMills);
    }
}
