/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月21日 下午2:14:08
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

/* 4、 编写使用远程服务的客户机类， 客户机类的主要功能有两个
 * @1 是通过Naming.lookup方法来构造注册服务程序 stub 程序实例，
 * @2 是调用服务器远程对象上的远程方法。*/
public class RmiSampleClient
{
    
    public static void main(String[] args)
    {
        try
        {
            
            String url = "//localhost:8808/SAMPLE-SERVER";
            
            RmiSample RmiObject = (RmiSample) Naming.lookup(url);
            
            System.out.println(" 1 + 2 =  " + RmiObject.sum(1, 2));
            
        }
        catch (RemoteException exc)
        {
            
            System.out.println("Error  in lookup: " + exc.toString());
            
        }
        catch (java.net.MalformedURLException exc)
        {
            
            System.out.println("Malformed URL: " + exc.toString());
            
        }
        catch (java.rmi.NotBoundException exc)
        {
            
            System.out.println("NotBound:  " + exc.toString());
            
        }
        
    }
    
}
