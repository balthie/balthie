package org.balthie.testng.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = { "classpath:spring/spring-test-config.xml" })
public class SomeServiceTest extends AbstractTestNGSpringContextTests
{
    
    @Autowired
    SomeService someService;
    
    @Test
    void testEmailGenerator()
    {
        String email = someService.doService();
        System.out.println(email);
    }
    
    @BeforeSuite
    public void beforeSuite()
    {
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
