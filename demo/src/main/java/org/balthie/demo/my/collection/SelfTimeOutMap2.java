/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 下午2:03:36
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.collection;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 下午2:03:36
 * @description 使用守护线程，清理过期map元素，使用delayQueue保存元素过期时间 效率比使用concurrentMap高
 * @since version 初始于版本 V1.0
 */
public class SelfTimeOutMap2<K, V> extends HashMap<K, V>
{
    private static final long serialVersionUID = 1L;
    
    // 清理频率(生产环境 , 10分钟)
    private static final long intervalTimeSecond = 5L;
    
    private TimeOutCleaner cleaner;
    
    private DelayQueue<DelayedKey> keyQueue = new DelayQueue<DelayedKey>();
    
    private Thread deamon;
    
    public SelfTimeOutMap2()
    {
        super();
        this.cleaner = new TimeOutCleaner();
        startDeamon();
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2014年12月22日 下午2:38:39
     * @description 保存并设定元素的超时时间，超时元素的清理时间不精确，所以不能以此超时时间作为业务逻辑使用
     * @param key
     * @param value
     * @param timeMills 超时毫秒数(不能以此超时时间作为业务逻辑使用)
     * @return
     */
    public V put(K key, V value, Long timeMills)
    {
        super.put(key, value);
        
        if(timeMills != null && timeMills > 0)
        {
            Long timeOutMills = System.currentTimeMillis() + timeMills;
            keyQueue.put(new DelayedKey(key, timeOutMills));
        }
        
        return value;
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2014年12月22日 下午2:26:22
     * @description 启动清理线程
     */
    private void startDeamon()
    {
        Thread deamon = new Thread(this.cleaner);
        deamon.setDaemon(true);
        this.deamon = deamon;
        deamon.start();
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2014年12月22日 下午2:24:49
     * @description 实现守护线程逻辑：定时遍历map，清除超时元素
     */
    private class TimeOutCleaner implements Runnable
    {
        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    TimeUnit.SECONDS.sleep(intervalTimeSecond);
                    DelayQueue<DelayedKey> keyQueue = SelfTimeOutMap2.this.keyQueue;
                    long beginTimeMills = System.currentTimeMillis();
                    System.out.println(MessageFormat.format(
                            "Thread【{0}】 invoke at 【{1,time, HH:mm:ss:ms}】 , map size = 【{2,number,###}】", Thread.currentThread()
                                    .getName(), new Date(), keyQueue.size()));
                    int counter = 0;
                    if(keyQueue != null && !keyQueue.isEmpty())
                    {
                        SelfTimeOutMap2<K, V>.DelayedKey delayedKey = null;
                        
                        do
                        {
                            //等待过期元素
                            delayedKey = keyQueue.poll();
                            
                            if(delayedKey != null)
                            {
                                SelfTimeOutMap2.this.remove(delayedKey.getKey());
                                counter++;
                            }
                        }
                        while (delayedKey != null);
                    }
                    System.out
                            .println(MessageFormat
                                    .format("Thread【{0}】 end at 【{1,time, HH:mm:ss:ms}】 , map size = 【{2,number,###}】, delete key count【{3}】, during【{4,number,###}】ms",
                                            Thread.currentThread().getName(), new Date(), keyQueue.size(), counter,
                                            (System.currentTimeMillis() - beginTimeMills)));
                }
                catch (InterruptedException e)
                {
                }
            }
        }
    }
    
    public class DelayedKey implements Delayed
    {
        // map元素key
        private K key;
        
        // 超时时间
        private long timeOutTimeMills;
        
        public DelayedKey()
        {
        }
        
        public DelayedKey(K key2, Long timeMills)
        {
            this.key = key2;
            this.timeOutTimeMills = timeMills;
        }
        
        @Override
        public int compareTo(Delayed o)
        {
            long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
            
            if(result > 0)
            {
                return 1;
            }
            else if(result < 0)
            {
                return -1;
            }
            return 0;
        }
        
        @Override
        public long getDelay(TimeUnit unit)
        {
            long diff = timeOutTimeMills - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }
        
        public void setTimeOutTimeMills(long timeOutTimeMills)
        {
            this.timeOutTimeMills = timeOutTimeMills;
        }
        
        public void setKey(K key)
        {
            this.key = key;
        }
        
        public K getKey()
        {
            return this.key;
        }
    }
    
    public static void main(String[] args)
    {
        SelfTimeOutMap2<String, String> map = new SelfTimeOutMap2<String, String>();
        
        String temp = null;
        System.out.println(" init map begin");
        for(int i = 0; i < 100000; i++)
        {
            temp = i + "";
            map.put(temp, temp, i * 100L);
        }
        System.out.println(" init map end");
        
        while (true)
        {
            try
            {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(System.currentTimeMillis() + " ,  map size = " + map.size());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
