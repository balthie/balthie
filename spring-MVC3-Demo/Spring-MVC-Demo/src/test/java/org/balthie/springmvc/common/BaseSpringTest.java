package org.balthie.springmvc.common;

import java.io.FileNotFoundException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring/spring-test.xml",
         "/conf/spring/spring-schedule.xml" })
public class BaseSpringTest extends AbstractJUnit4SpringContextTests
{
    static {  
        try {  
            Log4jConfigurer.initLogging("classpath:log4j.properties");  
        } catch (FileNotFoundException ex) {  
            System.err.println("Cannot Initialize log4j");  
        }  
    } 
}
