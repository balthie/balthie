/**
 * @author：balthie + 10050
 * @createtime ： 2015年2月5日 下午3:11:40
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.balthie.springmvc.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年2月5日 下午3:11:40
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class ExceptionHandlerIntercepter extends HandlerInterceptorAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerIntercepter.class);
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        LOGGER.debug("afterCompletion invoke ");
        try
        {
            if(ex != null)
            {
                if(ex instanceof RuntimeException)
                {
                    try
                    {
                        LOGGER.error(" afterCompletion 业务异常 ", ex);
                        JsonUtils.writeValue(response.getOutputStream(), "业务异常发生");
                        ex = null;
                    }
                    catch (Exception e)
                    {
                        LOGGER.error(" resolver exception error ", e);
                    }
                }
            }
            super.afterCompletion(request, response, handler, ex);
        }
        catch (Exception e)
        {
            LOGGER.error("afterCompletion occur exception ", e);
        }
        LOGGER.debug("afterCompletion end ");
    }
}
