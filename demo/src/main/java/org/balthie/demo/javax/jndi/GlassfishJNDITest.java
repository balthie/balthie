/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月26日 下午2:39:09
 * @description TODO 一句话描述
 */
package org.balthie.demo.javax.jndi;

import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月26日 下午2:39:09
 * @description 测试使用glassfish jndi resource 保存系统参数 fish上的配置菜单: Resources/JNDI/Custom Resources/ new
 * 
 */
public class GlassfishJNDITest
{
    public static void main(String[] args) throws NamingException
    {
        test1();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void test2() throws NamingException
    {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.glassfish.resources.custom.factory.PropertiesFactory");
        /*
         * glassfish domain.xml: <system-property name="IIOP_LISTENER_PORT"
         * value="23700"></system-property>
         */
        env.put(Context.PROVIDER_URL, "localhost:23700");
        DirContext ctx = new InitialDirContext(env);
        
        // 分别获取包含所有属性和只包含Mx属性的Attributes对象
        System.out.println(ctx);
    }
    
    public static void test1() throws NamingException
    {
        Properties props = new Properties();
        // JNDI初始化工厂
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        // 下面两个可以不设置
        /*
         * props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
         * props.setProperty("java.naming.factory.state",
         * "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
         */
        // 以下关于地址的设置，可以任意选择一种方式
        // 方式1：使用标准的ORB设置，需要设置ORB的主机和端口，端口实际上就是Glassfish的IIOP监听的端口
        /*
         * props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
         * props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
         */
        // 方式2：使用带iiop前缀的地址格式
        props.setProperty("java.naming.provider.url", "iiop://localhost:23700");
        // 方式3：可以指定多个JNDI服务的地址，多个地址用逗号分隔
        /* props.setProperty("com.sun.appserv.iiop.endpoints", "localhost:3700,localhost:13700"); */
        InitialContext ic = new InitialContext(props);
        
        System.out.println(ic);
        
        Properties p = (Properties) ((Context) ic.lookup("proxool/config")).lookup("cmpn");
        
        System.out.println(p);
        
        System.out.println(p);
    }
    
    // 注意，PropertyConfigurator 源码中，只解析以jdbc开头的配置，所以 key的名称必须以jdbc开头
    /*
     * Properties prop = new Properties(); prop.setProperty("jdbc-cmpn.proxool.alias",
     * "cmpnMySqlDBPool"); prop.setProperty("jdbc-cmpn.proxool.driver-url",
     * "jdbc:mysql://127.0.0.1:3306/cmpn?autoReconnect=true&amp;failOverReadOnly=false&amp;useUnicode=true&amp;characterEncoding=gb2312"
     * ); prop.setProperty("jdbc-cmpn.proxool.driver-class", "com.mysql.jdbc.Driver");
     * prop.setProperty("jdbc-cmpn.user", "root"); prop.setProperty("jdbc-cmpn.password", "root");
     * prop.setProperty("jdbc-cmpn.proxool.house-keeping-test-sql", "select 1 from dual");
     * prop.setProperty("jdbc-cmpn.proxool.house-keeping-sleep-time", "90000");
     * prop.setProperty("jdbc-cmpn.proxool.maximum-connection-count", "10");
     * prop.setProperty("jdbc-cmpn.proxool.minimum-connection-count", "1");
     * prop.setProperty("jdbc-cmpn.proxool.prototype-count", "2");
     * prop.setProperty("jdbc-cmpn.proxool.test-before-use", "true");
     * prop.setProperty("jdbc-cmpn.proxool.simultaneous-build-throttle", "30");
     * prop.setProperty("jdbc-cmpn.proxool.statistics", "15s,10m,1d");
     * prop.setProperty("jdbc-cmpn.proxool.statistics-log-level", "ERROR");
     */
}
