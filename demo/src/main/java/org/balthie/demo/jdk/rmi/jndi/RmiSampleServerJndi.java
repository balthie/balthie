/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月21日 下午2:23:03
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.rmi.jndi;

import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.balthie.demo.jdk.rmi.RmiSampleImpl;

public class RmiSampleServerJndi
{
    
    public static void main(String[] args) throws Exception
    {
        
        LocateRegistry.createRegistry(8808);
        
        RmiSampleImpl server = new RmiSampleImpl();
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:8808");
        
        InitialContext ctx = new InitialContext();
        
        ctx.bind("java:comp/env/SampleDemo", server);
        
        ctx.close();
        
    }
    
}
