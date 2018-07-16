package balthie.demo.testNGDemo.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer2;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;

//目前只有 @Configuration,@DataProvider 以及 @Factory 注释能够通过该监听器修改。而事实上，@Configuration 在最新版本中已不被推荐使用，需用 @BeforeSuite，@AfterSuite 等注释替代。
public class IAnnotationTransformer2Demo implements IAnnotationTransformer2
{
    
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
    {
        // 对 @Test注解生效
        System.out.println(String.format(
                "IAnnotationTransformer2Demo transform ITestAnnotation begin annotation[%s] testClass[%s] testConstructor[%s] testMethod[%s]",
                new Object[] { annotation, testClass, testConstructor, testMethod }));
    }
    
    @Override
    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
    {
        // afterMethod afterClass beforeSuite beforeClass beforeGroups afterSuite beforeMethod
        // afterGroups 各种配置类注解
        System.out.println(String.format(
                "IAnnotationTransformer2Demo transform IConfigurationAnnotation begin annotation[%s] testClass[%s] testConstructor[%s] testMethod[%s]",
                new Object[] { annotation, testClass, testConstructor, testMethod }));
    }
    
    @Override
    public void transform(IDataProviderAnnotation annotation, Method method)
    {
        // 对 @DataProvider 注解生效
        System.out.println(String.format(
                "IAnnotationTransformer2Demo transform IDataProviderAnnotation begin annotation[%s] testMethod[%s]",
                new Object[] { annotation, method }));
    }
    
    @Override
    public void transform(IFactoryAnnotation annotation, Method method)
    {
        // 对 @Factory 注解生效
        System.out.println(String.format(
                "IAnnotationTransformer2Demo transform IFactoryAnnotation begin annotation[%s] testMethod[%s]",
                new Object[] { annotation, method }));
    }
    
}
