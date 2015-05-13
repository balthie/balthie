package org.balthie.springmvc.async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskMockService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskMockService.class);
    
    public void execute()
    {
        long begin = System.currentTimeMillis();
        LOGGER.info(String.format("execute invoke Thread [%s]", Thread.currentThread().getName()));
        
        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        LOGGER.info(String.format("execute end during[%d] Thread [%s]", System.currentTimeMillis() - begin, Thread
                .currentThread().getName()));
    }
    
    @Async
    public void executeAsync()
    {
        long begin = System.currentTimeMillis();
        LOGGER.info(String.format("executeAsync invoke Thread [%s]", Thread.currentThread().getName()));
        
        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        LOGGER.info(String.format("executeAsync end during[%d] Thread [%s]", System.currentTimeMillis() - begin, Thread
                .currentThread().getName()));
    }
    
    @Async
    public Future<String> asyncMethodWithReturnType()
    {
        long begin = System.currentTimeMillis();
        AsyncResult<String> result = null;
        LOGGER.info(String.format("asyncMethodWithReturnType invoke Thread [%s]", Thread.currentThread().getName()));
        try
        {
            Thread.sleep(5000);
            result = new AsyncResult<String>("hello world !!!!");
        }
        catch (InterruptedException e)
        {
            //
        }
        LOGGER.info(String.format("asyncMethodWithReturnType end during[%d] Thread [%s]", System.currentTimeMillis() - begin, Thread
                .currentThread().getName()));
        return result;
    }
}
