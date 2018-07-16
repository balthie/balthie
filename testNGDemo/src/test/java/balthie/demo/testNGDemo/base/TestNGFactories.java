package balthie.demo.testNGDemo.base;

import org.testng.annotations.Factory;

public class TestNGFactories
{
//  @Factory    标记方法作为一个返回对象的工厂，这些对象将被TestNG用于作为测试类。这个方法必须返回Object[]
    @Factory
    public Object[] createInstances()
    {
        Object[] result = new Object[1];
        for(int i = 0; i < 1; i++)
        {
            result[i] = new TestNGLearn1();
        }
        return result;
    }
}
