/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月26日 下午2:54:45
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月26日 下午2:54:45
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 * 
 * 启动后debug日志错误：No qualifying bean of type [org.springframework.scheduling.TaskScheduler
 * 原因是需要在启动spring容器时，指定spring schedule 默认使用的执行器
 * http://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/scheduling.html
 */
@Configuration
@EnableScheduling
public class AppConfig
{
    @Bean
    public MyTask task()
    {
        System.out.println(" doTask ");
        return new MyTask();
    }
}