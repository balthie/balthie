package org.balthie.demo.opensource.sourceforge.cglib.proxy;

import org.apache.commons.lang.time.StopWatch;
import org.balthie.demo.opensource.sourceforge.cglib.BookFacade;

public class BookFacadeProxyDemo implements BookFacade
{
    public void addBook()
    {
//        System.out.println("新增图书...");
    }
    
    public static void main(String[] args)
    {
        BookFacadeProxyDemo bookFacadeImpl = new BookFacadeProxyDemo();
        JDKProxyDemo proxy = new JDKProxyDemo();
        BookFacade bookfacade = (BookFacade) proxy.bind(bookFacadeImpl);
        bookfacade.addBook();
        
//        original();
        
//        cglibTest();
        
//        jdkProxyTest();
    }
    
    public static void jdkProxyTest()
    {
        StopWatch sw = new StopWatch();
        sw.start();
        for(int i = 0; i < 1000000; i++)
        {
            BookFacadeProxyDemo bookFacadeImpl = new BookFacadeProxyDemo();
            JDKProxyDemo proxy = new JDKProxyDemo();
            BookFacade bookfacade = (BookFacade) proxy.bind(bookFacadeImpl);
            bookfacade.addBook();
        }
        sw.stop();
        System.out.println(" JDKProxy call 100w times " + sw.toString());
    }
    
    public static void original()
    {
        StopWatch sw = new StopWatch();
        sw.start();
        for(int i = 0; i < 1000000; i++)
        {
            BookFacadeProxyDemo bookFacade = new BookFacadeProxyDemo();
            bookFacade.addBook();
        }
        sw.stop();
        
        System.out.println(" original call 100w times " + sw.toString());
    }
    
    public static void cglibTest()
    {
        StopWatch sw = new StopWatch();
        sw.start();
        for(int i = 0; i < 1000000; i++)
        {
            BookFacadeProxyDemo bookFacade = new BookFacadeProxyDemo();
            CGLibProxyDemo cglib = new CGLibProxyDemo();
            BookFacadeProxyDemo bookCglib = (BookFacadeProxyDemo) cglib.getInstance(bookFacade);
            bookCglib.addBook();
        }
        sw.stop();
        
        System.out.println(" cglib call 100w times " + sw.toString());
    }
}
