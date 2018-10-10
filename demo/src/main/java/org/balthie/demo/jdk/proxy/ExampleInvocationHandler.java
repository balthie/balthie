package org.balthie.demo.jdk.proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ExampleInvocationHandler implements InvocationHandler
{
    private double balance;
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        // simplified method checks, would need to check the parameter count and types too
        if("deposit".equals(method.getName()))
        {
            Double value = (Double) args[0];
            System.out.println("deposit: " + value);
            balance += value;
            return proxy; // here we use the proxy to return 'this'
        }
        if("getBalance".equals(method.getName()))
        {
            return balance;
        }
        return null;
    }
    
    public static void main(String[] args)
    {
        Account account = (Account) Proxy.newProxyInstance(ExampleInvocationHandler.class.getClassLoader(),
                new Class[] { Account.class, Serializable.class },
                new ExampleInvocationHandler());
                
        // method chaining for the win!
        account.deposit(5000).deposit(4000).deposit(-2500);
        System.out.println("Balance: " + account.getBalance());
    }
}
