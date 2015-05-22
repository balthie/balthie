/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月21日 下午1:58:23
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*1、 定义远程接口
 * http://blog.csdn.net/liushuai_ly/article/details/8634957
 在 Java 中，远程对象是实现远程接口的类的实例, 远程接口声明每个要远程调用的方法。在需要创建一个远程对象的时候，我们通过传递一个接口来隐藏基层的实施细节，客户通过接口句柄发送消息即可。远程接口具有如下特点：
 Ø        远程接口必须为public属性。如果不这样，除非客户端与远程接口在同一个包内，否则当试图装入实现该远程接口的远程对象时，调用会得到错误结果。
 Ø        远程接口必须扩展接口java.rmi.Remote。
 Ø        除与应用程序本身特定的例外之外，远程接口中的每个方法都必须在自己的throws从句中声明java.rmi.RemoteException。（或 RemoteException 的父类）。*/
public interface RmiSample extends Remote
{
    public int sum(int a, int b) throws RemoteException;
}
