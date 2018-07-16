package balthie.demo.testNGDemo.listener;

import java.util.List;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

// 返回测试类中所有 被 @Test 注解注释的方法实例列表
// 在beforeSuite注解的方法后执行，suit完成初始化后执行
public class IMethodInterceptorDemo implements IMethodInterceptor
{
    // step2 : 发现实际被调用了2次，第一次执行时包含所有的 @Test方法， 第二次执行的时候，排除了已经指定了执行顺序（dependency）的方法
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context)
    {
        System.out.println(String.format("IMethodInterceptorDemo methods size [%s]", methods.size()));
        return methods;
    }
}
