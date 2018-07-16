package balthie.demo.testNGDemo.listener;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

// 这个功能和IHookable 类似，结构上更像监听器，并且先于IHookableDemo的切面执行
public class IInvokedMethodListenerDemo implements IInvokedMethodListener
{
    
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
    {
        System.out.println(
                String.format("IInvokedMethodListenerDemo beforeInvocation[%s]", method.getTestMethod().getMethodName()));
    }
    
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult)
    {
        System.out.println(String.format("IHookableDemo afterInvocation[%s]", method.getTestMethod().getMethodName()));
    }
    
}
