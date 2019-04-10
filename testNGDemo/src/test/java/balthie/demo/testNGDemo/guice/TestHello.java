package balthie.demo.testNGDemo.guice;

import javax.inject.Inject;
import org.testng.annotations.Test;

public class TestHello
{
    
    @Inject
    HelloClass helloClass;
    
    @Test
    public void testHello()
    {
        helloClass.sayHello();
    }
    
}