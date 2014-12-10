/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 上午11:17:20
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 上午11:17:20
 * @description  长耗时请求拦截器
 * @since version 初始于版本 0.0.6
 */
public class LongTimeLoggerIntercepter extends HandlerInterceptorAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LongTimeLoggerIntercepter.class);
    
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        System.out.println("afterCompletion invoke");
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;
        if(consumeTime > 500)
        {
            // 此处认为处理时间超过500毫秒的请求为慢请求, 记录到日志文件
            System.out.println(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
            LOGGER.info(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
            super.afterCompletion(request, response, handler, ex);
        }
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        long beginTime = System.currentTimeMillis();// 1、开始时间
        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
        return super.preHandle(request, response, handler);
    }
    
}
