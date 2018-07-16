package balthie.demo.testNGDemo.base;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//https://www.cnblogs.com/liwu/p/5113936.html
public class TestNGLearn2
{
    
    @Test
    public void testNgLearn()
    {
        System.out.println("this is TestNGLearn2 case");
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
    @Parameters("test1")
    public void paramTest(@Optional("use defualt") String test1)
    {
        // 配置文件方式，同一个参数，只能设置一次，最后一次设置的值生效，不支持自动多次执行
        System.out.println("paramTest This is " + test1);
    }
    
    // The annotated method must return an Object[][]
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
}
