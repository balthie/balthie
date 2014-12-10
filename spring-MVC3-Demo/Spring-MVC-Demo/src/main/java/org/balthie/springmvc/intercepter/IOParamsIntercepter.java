/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 上午11:17:20
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.intercepter;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.balthie.springmvc.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 上午11:17:20
 * @description 业务请求参数公共拦截器
 * @since version 初始于版本 V1.0
 */
public class IOParamsIntercepter extends HandlerInterceptorAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IOParamsIntercepter.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // Auto-generated method stub
        System.out.println("RequestParamIntercepter invoke");
        LOGGER.debug("RequestParamIntercepter invoke");
        
        // 打印请求url
        String reqUrl = request.getRequestURL().toString();
        
        String queryString = request.getQueryString();
        
        String clientIP = request.getRemoteAddr();
        
        LOGGER.info(String.format(" request url=【%s】 \r\n queryString=【%s】\r\n clientIP【%s】", reqUrl, queryString, clientIP));
        
        return super.preHandle(request, response, handler);
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception
    {
        LOGGER.debug("RequestParamIntercepter postHandle");
        if(modelAndView != null)
        {
            Map<String, Object> model = modelAndView.getModel();
            
            LOGGER.info(String.format(" RequestParamIntercepter model %s", JsonUtils.toJson(model)));
            
            View view = modelAndView.getView();
            LOGGER.info(" RequestParamIntercepter view = "+view);
        }
        else
        {
            LOGGER.info(" RequestParamIntercepter modelAndView is null");
        }
        
        super.postHandle(request, response, handler, modelAndView);
    }
}
