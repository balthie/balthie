/**
 * @author：balthie + 10050
 * @createtime ： 2015年2月9日 下午4:26:28
 * @description TODO 一句话描述
 */
package org.balthie.springmvc.excptionresolver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年2月9日 下午4:26:28
 * @description 业务异常处理
 * @since version 初始于版本 TODO 
 */
public class BusinessExceptionResolver extends AbstractHandlerExceptionResolver
{

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver#doResolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object paramObject, Exception ex)
    {
        /*try
        {
            response.sendError(404);
            request.setAttribute("javax.servlet.error.exception", ex);
            response.sendError(500);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
        String errorMsg = "";
        if(ex instanceof ArithmeticException)
        {
            System.out.println("捕获业务异常");
            errorMsg = "捕获业务异常:"+ex.getMessage();
        }
        else
        {
            System.out.println("未知异常");
        }
        
        List<Object> info = new ArrayList<Object>();
        info.add(errorMsg);
        info.add(new Date());
        
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("businessException", info);
        return mv;
    }
    
}
