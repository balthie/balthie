package balthie.demo.testNGDemo.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import balthie.demo.testNGDemo.listener.IAnnotationTransformerDemo;

//在 @Listeners 中添加监听器跟在 testng.xml 添加监听器的不同之处在于，它不能添加 IAnnotationTransformer 和 IAnnotationTransformer2 监听器。
//原因是因为这两种监听器必须在更早的阶段添加到 TestNG 中才能实施修改注释的操作，所以它们只能在 testng.xml 添加。
@Listeners({ IAnnotationTransformerDemo.class })
public class TestNGLearn1
{
    @BeforeGroups(groups = { "someGroup" })
    public void beforeGroups()
    {
        System.out.println("this is beforeGroups");
    }
    
    @AfterGroups(groups = { "someGroup" })
    public void asfterGroups()
    {
        System.out.println("this is AfterGroups");
    }
    
    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("this is TestNGLearn1 beforeSuite");
    }
    
    @AfterSuite
    public void afterSuite()
    {
        System.out.println("this is TestNGLearn1 afterSuite");
    }
    
    @BeforeClass
    public void beforeClass()
    {
        System.out.println("this is TestNGLearn1 before class");
    }
    
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("this is beforeMethod");
    }
    
    @AfterMethod
    public void afterMethod()
    {
        System.out.println("this is afterMethod");
    }
    
    @Test
    public void TestNgLearn()
    {
        System.out.println("this is TestNG test case");
    }
    
    @Test(groups = { "someGroup" })
    public void TestNgInGroup()
    {
        System.out.println("this is TestNgInGroup case");
    }
    
    @Test(dependsOnMethods = { "TestNgLearn" })
    public void TestNgDependency()
    {
        System.out.println("this is TestNgDependency case");
    }
    
    @DataProvider(name = "user")
    public Object[][] Users()
    {
        System.out.println(" @DataProvider Users begin ");
        return new Object[][] {
                { "tank", "xiao" }
        };
    }
    
    @Test(dataProvider = "user", dependsOnMethods = { "TestNgLearn" })
    public void verifyUser(String userName, String password)
    {
        System.out.println(" verifyUser Username begin ");
    }
    
    @AfterClass
    public void afterClass()
    {
        System.out.println("this is after class");
    }
}
