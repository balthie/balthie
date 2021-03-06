package org.balthie.testng.spring;

import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Log4jConfigurer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import common.spring.test.BaseSpringTestNG;

public class SomeServiceTest extends BaseSpringTestNG
{
    protected static Logger LOGGER = LoggerFactory.getLogger(SomeServiceTest.class);
    
    @Autowired
    SomeService someService;
    
    static
    {
        try
        {
            Log4jConfigurer.initLogging("classpath:log4j_4_test.properties");
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Cannot Initialize log4j");
        }
    }
    
    @Test(groups = "springGroup")
    void doServiceTest()
    {
//        RemoteTestNG
//        TestRunner
//        RunInfo
//        DefaultCacheAwareContextLoaderDelegate
//        SuiteRunner.newTestRunner
//        TestMethodWorker
//        Invoker
        String email = someService.doService();
        System.out.println(email);
    }
    
    @BeforeSuite
    public void beforeSuite()
    {
        // 在容器初始化之前执行，下面代码空指针
        // someService.doService();
        System.out.println("this is beforeSuite");
    }
    
    @AfterSuite
    public void afterSuite()
    {
        System.out.println("this is afterSuite");
    }
    
    @BeforeClass
    public void beforeClass()
    {
        System.out.println("this is before class");
    }
    
    @AfterClass
    public void afterClass()
    {
        System.out.println("this is after class");
    }
    
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("this is beforeMethod");
    }
    
    @AfterMethod
    public void afterMethod()
    {
        System.out.println("this is afterMethod");
    }
}
