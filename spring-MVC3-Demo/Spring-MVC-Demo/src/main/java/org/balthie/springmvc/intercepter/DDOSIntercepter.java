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
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 上午11:17:20
 * @description 长耗时请求拦截器
 * @since version 初始于版本 0.0.6
 */
public class DDOSIntercepter extends HandlerInterceptorAdapter
{
    private static final String DDOS_MODULE_PREFIX = "DDOS";
    private static final Logger LOGGER = LoggerFactory.getLogger(DDOSIntercepter.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        LOGGER.debug("DDOSIntercepter preHandle invoke");
        String clientIP = request.getRemoteAddr();
        String reqUrl = request.getRequestURL().toString();
        
        // 每次请求action接口生成唯一token
        StringBuilder requestToken = new StringBuilder().append(reqUrl).append("_").append(clientIP).append("_");
        
        // 根据请求查找缓存中的token
        
        /*if(MobileCacheEngine.saveNX(requestToken.toString(), System.currentTimeMillis() + "", DDOS_MODULE_PREFIX, 2L))
        {
            boolean result = super.preHandle(request, response, handler);
            *//**
             * 清除缓存中的token 不管是否是重复提交，都需要删除
             *//*
            MobileCacheEngine.delete(requestToken.toString(), DDOS_MODULE_PREFIX);
            return result;
        }
        else
        {
            // 对同一用户不允许重复提交请求
            response.getWriter().print("请求太频繁，稍后再试...");;
            return false;
        }*/
        return true;
    }
}
