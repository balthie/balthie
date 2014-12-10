package org.balthie.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月19日 上午10:49:05
 * @description 配置项加载类，会覆盖掉 servic-servlet.xml中的相关配置
 * @since version 初始于版本 TODO
 */

/*
 * @Configuration
 * 
 * @ComponentScan(basePackages="org.balthie.springmvc")
 * 
 * @EnableWebMvc
 */
public class MvcConfiguration extends WebMvcConfigurerAdapter
{
    
    @Bean
    public ViewResolver getViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
}
