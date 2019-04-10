/**
 * @author : balthie@126.com
 * @createtime ： 2015年5月27日 上午9:12:53
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : balthie@126.com
 * @createtime ： 2015年5月17日 下午1:23:00
 * @description SpringContext 静态工具类
 * @since V0.0.7
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware
{
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException
    {
        context = paramApplicationContext;
    }
    
    public static ApplicationContext getApplicationContext()
    {
        return context;
    }
    
    public static Object getBean(String name) throws BeansException
    {
        return context.getBean(name);
    }
    
    public static boolean containsBean(String name)
    {
        return context.containsBean(name);
    }
    
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException
    {
        return context.isSingleton(name);
    }
    
    public static Class getType(String name) throws NoSuchBeanDefinitionException
    {
        return context.getType(name);
    }
    
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException
    {
        return context.getAliases(name);
    }
}