package balthie.demo.testNGDemo.base;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGLearn3
{
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("this is TestNGLearn3 beforeMethod");
    }
    
    @AfterMethod
    public void afterMethod()
    {
        System.out.println("this is TestNGLearn3 afterMethod");
    }
    
    @Test(invocationCount = 2, threadPoolSize = 2)
    public void testParallel() throws InterruptedException
    {
        System.out.println("this is testParallel");
    }
    
    // timeOut 本单元每次执行的最大时长
    @Test(invocationCount = 2, threadPoolSize = 1, timeOut = 199)
    public void testTimeout() throws InterruptedException
    {
        System.out.println("this is testTimeout");
        TimeUnit.MILLISECONDS.sleep(100L);
    }
    
    // timeOut 本单元执行的总时长，没有invocationCount则被忽略
    @Test(invocationCount = 2, threadPoolSize = 2, invocationTimeOut = 199)
    public void testinvocationTimeOut() throws InterruptedException
    {
        System.out.println("this is testinvocationTimeOut");
        TimeUnit.MILLISECONDS.sleep(100L);
    }
}
