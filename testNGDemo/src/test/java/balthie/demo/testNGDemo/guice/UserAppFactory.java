package balthie.demo.testNGDemo.guice;

public class UserAppFactory extends GuiceInjectFactory
{
    @Override
    protected void configure()
    {
        // 绑定实例对象
        bind(HelloClass.class).toInstance(new HelloClass());// 使用后面的类
        // 绑定类
        // bind(HelloClass.class).to(HelloClass.class);
        // bind绑定需要注入
    }
}
