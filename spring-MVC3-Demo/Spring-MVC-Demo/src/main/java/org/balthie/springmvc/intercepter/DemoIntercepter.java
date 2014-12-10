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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月22日 上午11:17:20
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class DemoIntercepter extends HandlerInterceptorAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoIntercepter.class);
    
    /*
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        // TODO Auto-generated method stub
        LOGGER.debug("afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }
    
    /*
     * AsyncHandlerInterceptor
     * 继承HandlerInterceptor的接口，额外提供了afterConcurrentHandlingStarted方法，该方法是用来处理异步请求
     * 。当Controller中有异步请求方法的时候会触发该方法。
     * 楼主做过测试，异步请求先支持preHandle、然后执行afterConcurrentHandlingStarted。异步线程完成之后执行preHandle
     * 、postHandle、afterCompletion。
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
        // TODO Auto-generated method stub
        LOGGER.debug("afterConcurrentHandlingStarted");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
    
    /*
     * 后处理回调方法，实现处理器的后处理（但在视图解析渲染之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理
     * 返回值为void，表示不可阻断后续流程
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception
    {
        // TODO Auto-generated method stub
        LOGGER.debug("postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }
    
    /*
     * 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器；返回值：true表示继续流程（如调用下一个拦截器或处理器）；
     * false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // TODO Auto-generated method stub
        LOGGER.debug("preHandle");
        return super.preHandle(request, response, handler);
    }
    
}
