package balthie.demo.testNGDemo.base.dataProvider;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//动态判断，给不同的方法，提供不同的测试参数
public class DataProviderDemo3
{
    
    @Test(dataProvider = "dataProvider")
    public void test1(int number, int expected)
    {
        Assert.assertEquals(number, expected);
    }
    
    @Test(dataProvider = "dataProvider")
    public void test2(String email, String expected)
    {
        Assert.assertEquals(email, expected);
    }
    
    @DataProvider(name = "dataProvider")
    public Object[][] provideData(Method method)
    {
        // 给不同的方法，提供不同的测试参数
        Object[][] result = null;
        if(method.getName().equals("test1"))
        {
            result = new Object[][] {
                    { 1, 1 }, { 200, 200 }
            };
        }
        else if(method.getName().equals("test2"))
        {
            result = new Object[][] {
                    { "test@gmail.com", "test@gmail.com" },
                    { "test@yahoo.com", "test@yahoo.com" }
            };
        }
        
        return result;
    }
}
