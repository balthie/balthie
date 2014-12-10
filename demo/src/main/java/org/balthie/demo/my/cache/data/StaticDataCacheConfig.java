/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月29日 上午11:33:11
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.cache.data;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月29日 上午11:33:11
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
// 类注解
@Target(ElementType.TYPE)
// 允许反射获取
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StaticDataCacheConfig
{
    /**
     * 数据有效时间 : 设置默认超时时间为 1小时
     */
    long MAX_PERIOD_TIME_MILLS_DEFAULT = 1000 * 60 * 60L;
    
    public long dataLiveTimeMills() default MAX_PERIOD_TIME_MILLS_DEFAULT;
}
