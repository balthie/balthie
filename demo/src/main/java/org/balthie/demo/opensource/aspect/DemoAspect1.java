package org.balthie.demo.opensource.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class DemoAspect1
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoAspect1.class);
    
    /**
     * 目标类切点
     * 
     * @author：balthie + 10050
     * @createtime ： 2014-1-9 下午1:56:27
     */
    // java.lang.IllegalStateException: Cannot call sendError() after the response has been
    // committed
    // at org.apache.catalina.connector.ResponseFacade.sendError(ResponseFacade.java:451)
    // at org.apache.struts2.dispatcher.Dispatcher.sendError(Dispatcher.java:906)
    // at org.apache.struts2.dispatcher.Dispatcher.serviceAction(Dispatcher.java:586)
    
    @Before("within(com.mama100.mobile.business.*.action.*Action)||within(com.mama100.mobile.business.*.*.action.*Action)")
    public void beforeActionExcute()
    {
        System.out.println(" beforeActionExcute invoke ");
        LOGGER.info(" beforeActionExcute invoke ");
    }
    
    /**
     * 目标类切点
     * @author：balthie + 10050
     * @createtime ： 2014-1-9 下午1:56:27
     * @description 在所有service包下的类方法中加入切点， 使用这种方式无法获取 切点内部信息，改为分别定义切点和 通知方法的方式
     */
    @Before("within(com.mama100.mobile.business.*.service.*)||within(com.mama100.mobile.business.*.*.service.*)")
    public void beforeServiceExcute()
    {
        LOGGER.info(" beforeServiceExcute invoke ");
    }
    
    /*********  分离切点和通知   *********/
    @Pointcut("within(com.mama100.mobile.business.*.service.*)||within(com.mama100.mobile.business.*.*.service.*)"
            + "||within(com.mama100.mobile.business.*.*.*.service.*)||within(com.mama100.mobile.common.service.*)"
            + "||within(com.mama100.mobile.service.*.*)")
    public void commonServiceExcute()
    {
    }
    
    @Around("commonServiceExcute()")
    public Object dbSwitchAdvice(ProceedingJoinPoint pjp) throws Throwable
    {
        LOGGER.debug(" dbSwitchAdvice , excute method " + pjp.getSignature().getName() + "方法");
        long beginTime = System.currentTimeMillis();
        // 获取连接点的方法签名对象
       /* MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        // 连接点对象的方法
        Method method = joinPointObject.getMethod();
        
        RunAtBackupDB runAtBackupDB = method.getAnnotation(RunAtBackupDB.class);
        // add by mico 2014-3-20 增加日志数据库和报表数据库切换
        RunAtLogDB runAtLogDB = method.getAnnotation(RunAtLogDB.class);
        RunAtReportDB runAtReportDB = method.getAnnotation(RunAtReportDB.class);
        if(runAtBackupDB != null)
        {
            DbContextHolder.setDbType(DynamicRoutingDataSource.BACKUP_DATASOURCE);
        }
        else if(runAtLogDB != null)
        {
            DbContextHolder.setDbType(DynamicRoutingDataSource.LOG_DATASOURCE);
        }
        else if(runAtReportDB != null)
        {
            DbContextHolder.setDbType(DynamicRoutingDataSource.REPORT_DATASOURCE);
        }*/
        
        Object obj = null;
        try
        {
            LOGGER.debug(" dbSwitchAdvice invoke ");
            obj = pjp.proceed();
        }
        catch (Throwable e)
        {
            LOGGER.error(" dbSwitchAdvice error :" , e);
            throw e;
        }
        finally
        {
            // 恢复默认的数据库
           /* DbContextHolder.clearDbType();*/
        }
        LOGGER.debug(" dbSwitchAdvice , excute method " + pjp.getSignature().getName() + "方法 during "+(System.currentTimeMillis()-beginTime));
        return obj;
    }
}
