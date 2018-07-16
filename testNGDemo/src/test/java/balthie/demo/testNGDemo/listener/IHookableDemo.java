package balthie.demo.testNGDemo.listener;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

// 这里对应了 测试类中，所有被执行的方法的切面 , 包括 @beforeSuite @beforeClass 修饰的方法
public class IHookableDemo implements IHookable
{
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult)
    {
        System.out.println(String.format("IHookableDemo before runTestMethod[%s]", testResult.getMethod().getMethodName()));
        callBack.runTestMethod(testResult);
        System.out.println(String.format("IHookableDemo after runTestMethod[%s]", testResult.getMethod().getMethodName()));
    }
    
}
