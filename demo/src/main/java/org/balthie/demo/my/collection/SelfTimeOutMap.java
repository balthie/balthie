/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 下午2:03:36
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.collection;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 下午2:03:36
 * @description  自动删除过去元素Map
 * @since version 初始于版本 V0.0.6
 */
public class SelfTimeOutMap<K, V> extends HashMap<K, V>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SelfTimeOutMap.class);
    
    private static final long serialVersionUID = 1L;
    
    // 清理频率(生产环境 , 10分钟)
    private static final long intervalTimeSecond = 60 * 10L;
    
    private TimeOutCleaner cleaner;
    
    private HashMap<K, Long> entryTimeOutMap = new HashMap<K, Long>();
    
    private Thread deamon;
    
    public SelfTimeOutMap()
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
            entryTimeOutMap.put(key, timeOutMills);
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
                    TimeUnit.SECONDS.sleep(SelfTimeOutMap.intervalTimeSecond);
                    
                    HashMap<K, Long> entryTimeOutMap = SelfTimeOutMap.this.entryTimeOutMap;
                    
                    long beginTimeMills = System.currentTimeMillis();
                    LOGGER.debug(MessageFormat.format(
                            "Thread【{0}】 invoke at 【{1,time, HH:mm:ss:ms}】 , map size = 【{2,number,###}】", Thread.currentThread()
                            .getName(), new Date(), entryTimeOutMap.size()));
                    if(entryTimeOutMap != null && !entryTimeOutMap.isEmpty())
                    {
                        K key = null;
                        for(Iterator<K> iter = entryTimeOutMap.keySet().iterator(); iter.hasNext();)
                        {
                            key = iter.next();
                            if(entryTimeOutMap.get(key) <= System.currentTimeMillis())
                            {
                                SelfTimeOutMap.this.remove(key);
                                iter.remove();
                            }
                        }
                    }
                    LOGGER.debug(MessageFormat.format(
                            "Thread【{0}】 end at 【{1,time, HH:mm:ss:ms}】 , map size = 【{2,number,###}】, during【{3,number,###}】ms",
                            Thread.currentThread().getName(), new Date(), entryTimeOutMap.size(),
                            (System.currentTimeMillis() - beginTimeMills)));
                }
                catch (InterruptedException e)
                {
                }
            }
        }
    }
    
    /**
     * 测试方法
     * @author：balthie + 10050
     * @createtime ： 2014年12月22日 下午3:47:13
     * @description TODO 一句话描述
     * @since version 初始于版本 TODO 
     * @param args
     */
    public static void main(String[] args)
    {
        SelfTimeOutMap<String, String> map = new SelfTimeOutMap<String, String>();
        
        String temp = null;
        for(int i = 0; i < 1000; i++)
        {
            temp = i + "";
            map.put(temp, temp, i * 100L);
        }
        
        while (true)
        {
            try
            {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(System.currentTimeMillis() + " ,  map size = " + map.size());
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
