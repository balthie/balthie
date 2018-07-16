package balthie.demo.testNGDemo.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

//用来修改 @Test 注释的默认行为
// 运行时机：初始化测试用例 RunInfo对象时（测试用例加载时）
public class IAnnotationTransformerDemo implements IAnnotationTransformer
{
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
    {
        System.out.println(String.format(
                "IAnnotationTransformerDemo transform begin annotation[%s] testClass[%s] testConstructor[%s] testMethod[%s]",
                new Object[] { annotation, testClass, testConstructor, testMethod }));
    }
    
}
