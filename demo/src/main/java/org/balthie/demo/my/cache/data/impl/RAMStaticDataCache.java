/**
 * author：balthie   
 * createtime ： 2013-2013-12-18 下午6:13:52
 * 静态缓存默认实现：使用内存实现数据缓存
 * 继承此类可提供Map结构的数据缓存
 * 
 * 1，子类实现以下两个方法，提供查询原始数据及分组逻辑
 * Map<String, Collection<T>> queryAllDB();
 * Map<String, Collection<T>> queryByGroupIdDB(String groupId);
 * 
 * 2，此类实现 IStaticDataCacheQuery 向包外提供 queryByGroupId(String groupId) 方法，提供查询缓存数据 api
 * 
 * 3，默认数据刷新时间间隔  DefaultHytStaticDataCacheTimer.MAX_PERIOD_TIME_MILLS_DEFAULT
 * 子类可通过覆盖 get、SetUpDataLiveTimeMills() 方法提供自定义的数据刷新频率
 */
package org.balthie.demo.my.cache.data.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.balthie.demo.my.cache.data.IStaticDataCacheQuery;
import org.balthie.demo.my.cache.data.StaticDataCacheConfig;
import org.balthie.demo.my.collection.SelfTimeOutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月29日 上午10:06:46
 * @description RAM静态数据缓存
 * @since version 初始于版本 V0.0.6
 */
abstract public class RAMStaticDataCache<T> implements IStaticDataCacheQuery<T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RAMStaticDataCache.class);
    
    private static final Long MAX_PERIOD_TIME_MILLS_DEFAULT = 1000 * 60 * 60L;
    
    /**
     * 数据从进入缓存到超时的存活时间
     */
    private Long dataLiveTimeMills;
    
    // 业务数据内存缓存
    protected Map<String, Collection<T>> allData = new SelfTimeOutMap<String, Collection<T>>();
    
    // 缓存数据管理对象
    private DefaultHytDataStaticCacheManager cacheManager;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public RAMStaticDataCache()
    {
        this.cacheManager = new DefaultHytDataStaticCacheManager(new RAMStaticDataCache.DefaultHytStaticDataCacheTimer());
    }
    
    /**
     * 全量加载原始数据
     * 
     * @return 根据条件分组后的原始数据
     * @throws Exception
     */
    abstract protected Map<String, Collection<T>> queryAllDB() throws Exception;
    
    /**
     * 根据分组条件增量加载原始数据
     * 
     * @param groupId 分组条件
     * @return 根据条件分组后的原始数据
     * @throws Exception
     */
    abstract protected Map<String, Collection<T>> queryByGroupIdDB(String groupId) throws Exception;
    
    /**
     * 向业务层暴露数据查询接口：根据分组条件，查询数据
     * 
     * @param terminalCode
     * @return
     * @throws Exception
     */
    @Override
    public Collection<T> queryByGroupId(String groupId) throws RuntimeException
    {
        LOGGER.debug(" queryByGroupId begin");
        if(StringUtils.isBlank(groupId))
        {
            throw new RuntimeException(" queryByGroupId groupId mustn't be null ");
        }
        
        // 缓存数据超过最大存活时间，尝试从数据库加载
        // 缓存没有全量初始化的时候，如果缓存中暂无此组数据，尝试从数据库加载
        if(this.cacheManager.dataTimmer.isDataTimeOut(groupId)
                || (!this.cacheManager.isAllRefreshed() && !allData.containsKey(groupId)))
        {
            this.cacheManager.forceRefreshByGroupId(groupId);
        }
        
        LOGGER.debug(" queryByGroupId end ");
        return allData.get(groupId);
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2015年1月29日 上午11:21:01
     * @description 获取数据超时时间
     */
    private long getDataLiveTimeMills()
    {
        if(this.dataLiveTimeMills == null)
        {
            /**
             * 从子类类注解获取数据有效时间 : 设置默认超时时间为 1小时
             */
            StaticDataCacheConfig config = this.getClass().getAnnotation(StaticDataCacheConfig.class);
            
            if(config != null)
            {
                this.dataLiveTimeMills = config.dataLiveTimeMills();
            }
            else
            {
                this.dataLiveTimeMills = MAX_PERIOD_TIME_MILLS_DEFAULT;
            }
        }
        return this.dataLiveTimeMills;
    }
    
    boolean forceRefreshByGroupId(String groupId)
    {
        return this.cacheManager.forceRefreshByGroupId(groupId);
    }
    
    class DefaultHytDataStaticCacheManager
    {
        // 数据是否全量初始化
        private Boolean bInited = false;
        
        // 缓存数据计时器
        private DefaultHytStaticDataCacheTimer dataTimmer;
        
        private Map<String, Collection<T>> tempDataMap;
        
        public DefaultHytDataStaticCacheManager(DefaultHytStaticDataCacheTimer timer)
        {
            this.dataTimmer = timer;
        }
        
        /**
         * @author：balthie + 10050
         * @createtime ： 2013-12-30 下午5:33:54
         * @description 强制缓存数据全量刷新
         */
        public boolean forceRefreshAll()
        {
            Long begin = System.currentTimeMillis();
            LOGGER.debug(" forceRefreshAll  begin ");
            try
            {
                // 重新查询数据
                tempDataMap = RAMStaticDataCache.this.queryAllDB();
                // 锁定并刷新
                RAMStaticDataCache.this.allData.clear();
                RAMStaticDataCache.this.allData.putAll(tempDataMap);
                
                // 记录刷新时间
                Map<String, Long> tempTimmerMap = new HashMap<String, Long>();
                Long currentTimeMills = System.currentTimeMillis();
                for(String key : tempDataMap.keySet())
                {
                    tempTimmerMap.put(key, currentTimeMills);
                }
                this.dataTimmer.dataInitTimeMap.clear();
                this.dataTimmer.dataInitTimeMap.putAll(tempTimmerMap);
                
                this.bInited = true;
            }
            catch (Exception e)
            {
                LOGGER.error(" forceRefreshAll failed , cause : ", e);
                return false;
            }
            LOGGER.debug(" forceRefreshAll  success during 【" + (begin - System.currentTimeMillis()) + "】ms ");
            return true;
        }
        
        /**
         * @author：balthie + 10050
         * @createtime ： 2013-12-30 下午5:33:54
         * @description 根据分组编号强制刷新缓存数据
         */
        public boolean forceRefreshByGroupId(String groupId)
        {
            Long begin = System.currentTimeMillis();
            LOGGER.debug(" forceRefreshByGroupId  begin  groupId = " + groupId);
            try
            {
                tempDataMap = RAMStaticDataCache.this.queryByGroupIdDB(groupId);
                
                // 更新缓存中的数据
                if(tempDataMap != null && tempDataMap.size() > 0)
                {
                    RAMStaticDataCache.this.allData.putAll(tempDataMap);
                }
                else
                {
                    RAMStaticDataCache.this.allData.put(groupId, new ArrayList<T>());
                }
                
                // 记录数据加载时间
                dataTimmer.updateTimeMills(groupId, System.currentTimeMillis());
            }
            catch (Exception e)
            {
                LOGGER.error(" forceRefreshByGroupId failed , cause : ", e);
                return false;
            }
            
            LOGGER.debug(" forceRefreshByGroupId  success during 【" + (System.currentTimeMillis() - begin) + "】ms ");
            return true;
        }
        
        /**
         * @author：balthie + 10050
         * @createtime ： 2013-12-31 下午3:45:28
         * @description 是否已经全量加载数据
         * @return
         */
        public boolean isAllRefreshed()
        {
            return bInited;
        }
    }
    
    class DefaultHytStaticDataCacheTimer
    {
        // 业务数据内存缓存刷新时间
        protected Map<String, Long> dataInitTimeMap = new SelfTimeOutMap<String, Long>();
        
        /**
         * 查询缓存数据是否已经超时
         * 
         * @param groupId 分组条件
         * @result 数据是否已超时
         */
        public boolean isDataTimeOut(String groupId)
        {
            Long lastInitTimeMills = dataInitTimeMap.get(groupId);
            
            if(lastInitTimeMills == null)
            {
                return true;
            }
            else
            {
                return System.currentTimeMillis() - lastInitTimeMills > RAMStaticDataCache.this.getDataLiveTimeMills();
            }
        }
        
        /**
         * @author：balthie + 10050
         * @createtime ： 2013-12-30 下午4:24:51
         * @description 记录数据刷新时间
         * @param gourpId 分组id
         * @param timeMills Date.time()
         */
        public void updateTimeMills(String gourpId, Long timeMills)
        {
            dataInitTimeMap.put(gourpId, timeMills);
        }
        
        /**
         * @author：balthie + 10050
         * @createtime ： 2013-12-30 下午4:49:36
         * @description 获取数据上次刷新时间
         * @param gourpId
         * @return
         */
        public Long getTimeMills(String gourpId)
        {
            return dataInitTimeMap.get(gourpId);
        }
    }
}
