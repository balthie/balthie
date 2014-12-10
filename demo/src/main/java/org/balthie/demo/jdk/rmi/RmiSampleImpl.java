/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月21日 下午1:58:59
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程接口实现类，继承了UnicastRemoteObject并实现了RmiSample远程接口
 * 
 * 远程对象实现类必须扩展远程对象java.rmi.UnicastRemoteObject类，并实现所定义的远程接口。远程对象的实现类中包含实现每个远程接口所指定的远程方法的代码。
 * 这个类也可以含有附加的方法，但客户只能使用远程接口中的方法。因为客户是指向接口的一个句柄，而不是它的哪个类。必须为远程对象定义构造函数，即使只准备定义一个默认构造函数，用它调用基础类构造函数。
 * 因为基础类构造函数可能会抛出 java.rmi.RemoteException，所以即使别无它用必须抛出java.rmi.RemoteException例外。
 */

public class RmiSampleImpl extends UnicastRemoteObject implements RmiSample
{
    private static final long serialVersionUID = -3895511512985340151L;

    // 覆盖默认构造函数并抛出RemoteException
    public RmiSampleImpl() throws RemoteException
    {
        
        super();
        
    }
    
    // 所有远程实现方法必须抛出RemoteException
    
    public int sum(int a, int b) throws RemoteException
    {
        
        return a + b;
        
    }
    
}
