/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月9日 上午10:05:40
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.io.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月9日 上午10:05:40
 * @description 从应用相对路径读取配置文件
 */
public class PropertiesUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);
    
    public Properties loadProperties(String filePath) throws IOException
    {
        LOGGER.info(" loadProperties from  " + filePath);
        InputStream is = this.getClass().getResourceAsStream(filePath);
        Properties p = new Properties();
        p.load(is);
        return p;
    }
    
    public void method1(String filePath) throws IOException
    {
        /*
         * Class.getResourceAsStream() 会指定要加载的资源路径与当前类所在包的路径一致。 例如你写了一个MyTest类在包com.test.mycode
         * 下，那么MyTest.class.getResourceAsStream("name") 会在com.test.mycode包下查找相应的资源。 如果这个name是以 '/'
         * 开头的，那么就会从classpath的根路径下开始查找。
         */
        
        // 返回读取指定资源的输入流
        InputStream is = this.getClass().getResourceAsStream(filePath);
        Properties p = new Properties();
        p.load(is);
        System.out.println(p.getProperty("log4j.category.org.balthie", "none"));
    }
    
    public void method2(String name)
    {
        ResourceBundle bundle = ResourceBundle.getBundle(name, Locale.getDefault());
        String s = bundle.getString("log4j.category.org.balthie");
        System.out.println(s);
    }
    
    public void method3(String filePath) throws IOException
    {
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        ResourceBundle bundle = new PropertyResourceBundle(in);
        String s = bundle.getString("log4j.category.org.balthie");
        System.out.println(s);
    }
    
    public void method4(String filePath) throws IOException
    {
        /*
         * ClassLoader.getResourceAsStream() 无论要查找的资源前面是否带'/' 都会从classpath的根路径下查找。 所以:
         * MyTest.getClassLoader().getResourceAsStream("name") 和
         * MyTest.getClassLoader().getResourceAsStream("name") 的效果是一样的。
         */
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        Properties p = new Properties();
        p.load(in);
        System.out.println(p.getProperty("log4j.category.org.balthie", "none"));
    }
    
    public void method5(String filePath) throws IOException
    {
        InputStream in = ClassLoader.getSystemResourceAsStream(filePath);
        Properties p = new Properties();
        p.load(in);
        System.out.println(p.getProperty("log4j.category.org.balthie", "none"));
    }
    
    public void method6()
    {
        // Servlet中可以使用javax.servlet.ServletContext的getResourceAsStream()方法
        /*
         * InputStream in = context.getResourceAsStream(path);
         * Properties p = new Properties();
         * p.load(in);
         */
    }
    
    public void method7(String filePath) throws IOException
    {
        InputStream in = ClassLoader.getSystemResourceAsStream(filePath);
        Properties p = new Properties();
        p.load(in);
        System.out.println(p.getProperty("log4j.category.org.balthie", "none"));
    }
    
    public static void main(String[] args) throws IOException
    {
        PropertiesUtil util = new PropertiesUtil();
        
        util.method1("/log4j.properties");
        
        util.method2("log4j");
        
        util.method3("E://balthie-git//github//balthie//demo//src//main//resources//log4j.properties");
        
        util.method4("log4j.properties");
        
        util.method5("log4j.properties");
        
        util.method7("log4j.properties");
    }
}
