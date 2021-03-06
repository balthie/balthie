package org.balthie.demo.javax.jndi.spi;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

/**
 * SPI接口实现类，作用：根据配置实例化ConfigContext, 客户端程序就可以使用Context中绑定的对象.
 */
public class ConfigInitialContextFactory implements InitialContextFactory
{
    
    protected static final String PREFIX = "config.";
    
    protected static final String NAME_SUFFIX = ".name";
    
    protected static final String SOURCES_SUFFIX = ".sources";
    
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException
    {
        
        // environment中包括了当前application中所有的JNDI配置信息
        // 在实例化context时需要有选择性的操作.
        // 比如,当前应用中有JMS的JNDI配置,那么此environment也包括这些信息.
        if(environment == null)
        {
            return new ConfigContext();
        }
        Map<String, Map<String, String>> configs = new HashMap<String, Map<String, String>>();
        Properties innerEnv = new Properties();
        for(Map.Entry entry : environment.entrySet())
        {
            String key = (String) entry.getKey();
            if(!key.startsWith(PREFIX))
            {
                continue;
            }
            int begin = key.indexOf(".");
            int end = key.lastIndexOf(".");
            if(begin == end)
            {
                continue;
            }
            String property = key.substring(end + 1);
            if(!Config.contains(property))
            {
                continue;
            }
            // 将naming表示为类似于目录的路径,其实它可以为任意字符串.
            String name = key.substring(begin + 1, end).replaceAll("\\.", "/");
            Map<String, String> properties = configs.get(name);
            if(properties == null)
            {
                properties = new HashMap<String, String>();
                configs.put(name, properties);
            }
            String content = "";
            if(entry.getValue() != null)
            {
                content = entry.getValue().toString();
            }
            properties.put(property, content);
            innerEnv.put(name + "/" + property, content);
        }
        
        Context context = new ConfigContext();
        for(Map.Entry<String, Map<String, String>> entry : configs.entrySet())
        {
            String name = entry.getKey();
            Config config = createConfig(name, entry.getValue());
            context.bind(name, config);
        }
        
        return context;
    }
    
    private Config createConfig(String name, Map<String, String> properties)
    {
        if(name == null)
        {
            throw new RuntimeException("config name cant be empty..");
        }
        Config config = new Config(name);
        String sources = properties.get("sources");
        if(sources != null)
        {
            config.setSources(sources);
        }
        // more properties setting..
        return config;
    }
    
    public static void main(String[] args) throws Exception
    {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.balthie.demo.javax.jndi.spi.ConfigInitialContextFactory");
        env.put("config.database.mysql.name", "mysql-jdbc");
        env.put("config.database.mysql.sources", "192.168.0.122:3306");
        
        // 实际创建的就是 自定义的ConfigContext对象
        Context context = new InitialContext(env);
        // 查找方式1
        Config config = (Config) context.lookup("database/mysql");
        if(config != null)
        {
            System.out.println(config.getName() + "  -->   " + config.getSources());
        }
        
        // 查找方式2
        Name name = new CompositeName("database/mysql");
        config = (Config) context.lookup(name);
        if(config != null)
        {
            System.out.println(config.getName() + "," + config.getSources());
        }
        
        // 查找方式3
        Context subContext = (Context) context.lookup("database");
        config = (Config) subContext.lookup("mysql");
        if(config != null)
        {
            System.out.println(config.getName() + "," + config.getSources());
        }
        
        // 自定义的ConfigContext对象没有实现此方法，抛出空指针异常
        NamingEnumeration<NameClassPair> list = context.list("/");
        while (list.hasMore())
        {
            NameClassPair nc = (NameClassPair) list.next();
            System.out.println(nc);
        }
    }
}
