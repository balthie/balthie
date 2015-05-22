/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月21日 下午2:23:49
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.rmi.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import org.balthie.demo.jdk.rmi.RmiSample;

public class RmiSampleClientJndi
{
    
    public static void main(String[] args) throws Exception
    
    {
        
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:8808");
        
        InitialContext ctx = new InitialContext();
        
        String url = "java:comp/env/SampleDemo";
        
        RmiSample RmiObject = (RmiSample) ctx.lookup(url);
        
        System.out.println("  1 + 2 = " + RmiObject.sum(1, 2));
        
    }
    
}
