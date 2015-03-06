package org.balthie.demo.javax.jndi.spi;

/*本实例简单的展示了JNDI SPI的实现,模拟一个"配置管理中心",通过web server或者spring容器的方式,向"配置管理中心"提交配置信息,应用程序可以通过JNDI的方式来查找相应的配置等.本实例中包括了:
 1) ConfigInitialContextFactory.java : 它实现了 javax.naming.spi.InitialContextFactory接口,通过调用者传递的"环境参数"来创建Context查找点.应用程序(通常为客户端)使用.
 2) ConfigContext.java : 实现了javax.naming.Context接口,它主要负责托管绑定在Context上的所有object,并提供了基于路径的查找方式.
 3) ConfigObjectFactory.java : 实现了javax.naming.spi.ObjectFactory接口,用于容器(Container)来创建或者获取对象.*/
public class DemoTest
{
    public static void main(String[] args)
    {
        /*ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Config config = (Config) context.getBean("zookeeperConfig");
        System.out.println(config.getName() + "<>" + config.getSources());*/
    }
}
