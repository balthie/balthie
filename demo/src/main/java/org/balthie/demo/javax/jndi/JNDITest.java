package org.balthie.demo.javax.jndi;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDITest
{
    public static void main(String[] args) throws NamingException
    {
        fileSystemJndi();
    }

    /**
     * 测试文件系统jndi
     */
    public static void fileSystemJndi() throws NamingException
    {
        Hashtable env = new Hashtable();
        String name = "F:\\fscontext-1_2-beta3.zip";
        // 文件系统服务的提供者
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        Context ctx = new InitialContext(env);
        // 通过上下文查找名称对应的对象
        Object obj = ctx.lookup(name);
        System.out.println("名称：[" + name + "]绑定的对象是:" + obj);
    }
}
