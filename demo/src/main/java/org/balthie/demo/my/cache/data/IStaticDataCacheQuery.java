/**
 * author：balthie   
 * createtime ： 2013-2013-12-18 下午6:13:52
 * 静态数据缓存查询功能接口：
 * 向包外暴露缓存数据 查询接口
 */
package org.balthie.demo.my.cache.data;

import java.util.Collection;

public interface IStaticDataCacheQuery<T>
{
    Collection<T> queryByGroupId(String groupId) throws Exception;
}
