/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月29日 上午10:14:57
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.cache.data.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.balthie.demo.my.cache.data.Foo;
import org.balthie.demo.my.cache.data.StaticDataCacheConfig;
import org.junit.Test;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月29日 上午10:14:57
 */
@StaticDataCacheConfig(dataLiveTimeMills=5000L)
public class RAMStaticDataCacheTest extends RAMStaticDataCache<Foo>
{
    private Map<String, Collection<Foo>> datas = new HashMap<String, Collection<Foo>>(); 
    
    private void init()
    {
        System.out.println(" init begin ");
        Collection<Foo> entryList = null;
        for(int i=1;i<100000;i++)
        {
            entryList = new ArrayList<Foo>();
            entryList.add(new Foo("user"+i, ""+i));
            datas.put(""+i, entryList);
        }
        System.out.println(" init end ");
    }
    
    @Test
    public void queryByGroupIdTest() throws RuntimeException, InterruptedException
    {
        init();
        
        Long time1 = System.currentTimeMillis();
        Collection<Foo> accountList = this.queryByGroupId("10008");
        Long time2 = System.currentTimeMillis();
        System.out.println(" accountList size = " + accountList.size() + "  ; during  " + (time2 - time1));
        
        this.forceRefreshByGroupId("10008");
        Long time3 = System.currentTimeMillis();
        System.out.println(" accountList size = " + accountList.size() + "  ; during  " + (time3 - time2));
        
        
        super.queryByGroupId("10008");
        Long time4 = System.currentTimeMillis();
        System.out.println(" accountList size = " + accountList.size() + "  ; during  " + (time4 - time3));
        
        TimeUnit.SECONDS.sleep(6);
        
        Long time5 = System.currentTimeMillis();
        super.queryByGroupId("10008");
        Long time6 = System.currentTimeMillis();
        System.out.println(" accountList size = " + accountList.size() + "  ; during  " + (time6 - time5));
    }
    
    @Override
    protected Map<String, Collection<Foo>> queryAllDB() throws Exception
    {
        return datas;
    }

    @Override
    protected Map<String, Collection<Foo>> queryByGroupIdDB(String groupId) throws Exception
    {
        Map<String, Collection<Foo>> map = new HashMap<String, Collection<Foo>>();
        map.put(groupId, datas.get(groupId));
        TimeUnit.SECONDS.sleep(2);
        return map;
    }
    
}
