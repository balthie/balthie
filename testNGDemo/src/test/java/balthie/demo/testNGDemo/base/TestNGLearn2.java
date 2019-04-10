package balthie.demo.testNGDemo.base;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//https://www.cnblogs.com/liwu/p/5113936.html
public class TestNGLearn2
{
    
    @Test
    public void testNgLearn(ITestContext itc)
    {
        itc.getAttribute("");
        System.out.println("this is TestNGLearn2 case");
        System.out.println("this is TestNGLearn2 inject ITestContext " + itc);
    }
    
    @Test(groups = { "someGroup" })
    public void testNgInGroup()
    {
        System.out.println("this is TestNGLearn2.TestNgInGroup case");
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "NullPoint")
    public void testException()
    {
        System.out.println("this is TestNGLearn2 testException");
        throw new IllegalArgumentException("NullPoint");
    }
    
    /**
     * @author：balthie@126.com
     * @createtime ： 2018年7月16日 上午11:22:07
     * @description 第一种: testng.xml 方式使代码和测试数据分离，方便维护
     *              第二种：@DataProvider能够提供比较复杂的参数。 (也叫data-driven testing)
     */
    @Test
    // 通过@Parameters可以声明多个参数，只要与测试方法的参数索引和数量对应即可。如果参数不对应则在执行测试的时候会抛出异常。
    @Parameters("test1")
    public void paramTest(@Optional("use defualt") String anyName)
    {
        // 配置文件方式，同一个参数，只能设置一次，最后一次设置的值生效，不支持自动多次执行
        System.out.println("paramTest This is " + anyName);
    }
    
    // The annotated method must return an Object[][]
    // requires a @DataProvider named : user, @DataProvider 必须和 @Test(dataProvider = 的测试方法，在同一个测试类当中
    // 否则就需要指定 dataProviderClass属性，并且 provider 方法必须为静态
    @DataProvider(name = "user")
    public Object[][] Users()
    {
        return new Object[][] {
                { "root", "passowrd" },
                { "cnblogs.com", "tankxiao" },
                { "tank", "xiao" }
        };
    }
    
    // TestNG 通过 @Test 注释中的 dependsOnGroups 和 dependsOnMethods
    // 使用户能够定义测试方法之间的依赖关系。这种依赖关系也就决定这些测试方法必须按着怎样的顺序运行
    @Test(dataProvider = "user", dependsOnMethods = { "paramTest" })
    public void verifyUser(String userName, String password)
    {
        System.out.println("paramTest verifyUser Username: " + userName + " Password: " + password);
    }
    
    @Test(enabled = false)
    public void testIgnore()
    {
        System.out.println("This test case will ignore");
    }
    
    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("this is TestNGLearn2 beforeSuite");
    }
    
    @BeforeClass
    public void beforeClass()
    {
        System.out.println("this is TestNGLearn2 before class");
    }
}
