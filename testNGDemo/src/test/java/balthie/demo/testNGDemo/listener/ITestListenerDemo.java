package balthie.demo.testNGDemo.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerDemo implements ITestListener
{
    
    @Override
    public void onTestStart(ITestResult testResult)
    {
        System.out.println(String.format("ITestListenerDemo onTestStart[%s]", testResult.getMethod().getMethodName()));
    }
    
    @Override
    public void onTestSuccess(ITestResult testResult)
    {
        System.out.println(String.format("ITestListenerDemo onTestSuccess[%s]", testResult.getMethod().getMethodName()));
    }
    
    @Override
    public void onTestFailure(ITestResult testResult)
    {
        System.out.println(String.format("ITestListenerDemo before runTestMethod[%s]", testResult.getMethod().getMethodName()));
    }
    
    @Override
    public void onTestSkipped(ITestResult testResult)
    {
        System.out.println(String.format("ITestListenerDemo before runTestMethod[%s]", testResult.getMethod().getMethodName()));
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onStart(ITestContext context)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onFinish(ITestContext context)
    {
        // TODO Auto-generated method stub
        
    }
    
}
