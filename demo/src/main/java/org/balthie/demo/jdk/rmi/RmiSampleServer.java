/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月21日 下午2:10:22
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/*3、 编写服务器类
 包含 main 方法的类可以是实现类自身，也可以完全是另一个类。下面通过RmiSampleServer来创建一个远程对象的实例，并通过java.rmi.registry.LocateRegistry类的createRegistry 方法从指定端口号启动注册服务程序，也可以通过执行 rmiregistry 命令启动注册服务程序，注册服务程序的缺省运行端口为 1099。*/
public class RmiSampleServer
{
    
    public static void main(String[] args)
    {
        
        /*
         * 创建和安装一个安全管理器，令其支持RMI.作为Java开发包的一部分
         * 
         * 适用于RMI唯一一个是RMISecurityManager. if(System.getSecurityManager() == null) {
         * System.setSecurityManager(new RMISecurityManager()); }
         */
        
        try
        {
            
            LocateRegistry.createRegistry(8808);
            
            RmiSampleImpl server = new RmiSampleImpl();
            
            Naming.rebind("//localhost:8808/SAMPLE-SERVER", server);
            
            System.out.println("远程对象注册成功，RMI服务已经启动，等待客户端调用....");
            
        }
        catch (java.net.MalformedURLException me)
        {
            
            System.out.println("Malformed URL:" + me.toString());
            
        }
        catch (RemoteException re)
        {
            
            System.out.println("Remote exception:" + re.toString());
            
        }
    }
}
