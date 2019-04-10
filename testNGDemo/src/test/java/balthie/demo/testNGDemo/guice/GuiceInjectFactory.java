package balthie.demo.testNGDemo.guice;

import java.lang.reflect.Constructor;
import org.testng.IObjectFactory;
import org.testng.internal.ObjectFactoryImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

// https://blog.csdn.net/langsand/article/details/53817145
// 测试套件配置文件中需要 增加 <suite name="testproj" parallel="false" object-factory="balthie.demo.testNGDemo.guice.UserAppFactory">
public abstract class GuiceInjectFactory extends AbstractModule implements IObjectFactory
{
    
    public ObjectFactoryImpl creator = new ObjectFactoryImpl();
    
    private Injector injector;
    
    public GuiceInjectFactory()
    {
        injector = Guice.createInjector(this);
    }
    
    @Override
    public Object newInstance(Constructor constructor, Object... params)
    {
        Object o = creator.newInstance(constructor, params);
        System.out.println("create test :" + o);
        injector.injectMembers(o);
        return o;
    }
}