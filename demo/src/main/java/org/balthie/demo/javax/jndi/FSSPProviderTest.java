package org.balthie.demo.javax.jndi;

/**
 */
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

/**
 * @createtime ： 2015年3月6日 上午10:51:50
 * @description sun的FSSP(File System Service Provider)文件系统服务提供者.注意在这个例子中要使用到前面所说的File System Service
 *              Provider for the java Naming and Directory InterfaceTM (JNDI)相关类
 * @tip1 jdk没有自带文件系统jndi服务实现 com.sun.jndi.fscontext.RefFSContextFactory，需要加入依赖申明 <dependency>
 *       <groupId>com.sun.messaging.mq</groupId> <artifactId>fscontext</artifactId>
 *       <version>4.5-b21</version> </dependency>
 */
public class FSSPProviderTest
{
    public FSSPProviderTest()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args)
    {
        try
        {
            Properties env = new Properties();
            
            // fileSystemService.properties文件的内容为：java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory
            // env.load(new FileInputStream("fileSystemService.properties"));
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            env.put(Context.PROVIDER_URL, "file:///c:/");
            Context ctx = new InitialContext(env);
            
            // 创建子文件目录
            ctx.createSubcontext("sylilzy");
            
            // 遍历所有文件目录
            NamingEnumeration list = ctx.list("/");
            while (list.hasMore())
            {
                NameClassPair nc = (NameClassPair) list.next();
                System.out.println(nc);
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
