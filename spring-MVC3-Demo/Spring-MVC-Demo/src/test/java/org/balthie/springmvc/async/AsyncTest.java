package org.balthie.springmvc.async;

import java.util.concurrent.TimeUnit;
import org.balthie.springmvc.common.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AsyncTest extends BaseSpringTest
{
    @Autowired
    private AsyncTaskMockService service;

    @Test
    public void mainTest()
    {
        //excuteSync(5);
        //excuteAsync(20);
        excuteAsyncWithReturnValue(20);
        try
        {
            TimeUnit.SECONDS.sleep(60);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    private void excuteAsyncWithReturnValue(int times)
    {
        for(int i=0;i<times;i++)
        {
            System.out.println(service.asyncMethodWithReturnType());
        }
    }
    
    private void excuteAsync(int times)
    {
        for(int i=0;i<times;i++)
        {
            service.executeAsync();
        }
    }

    private void excuteSync(int times)
    {
        for(int i=0;i<times;i++)
        {
            service.execute();
        }
    }
}
