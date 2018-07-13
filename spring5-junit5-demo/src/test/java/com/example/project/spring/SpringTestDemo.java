package com.example.project.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(locations= {"classpath:applicationContext-global-test.xml"})
public class SpringTestDemo {
	
	@Autowired
	SomeService someService;
	
	@Test
	void onlyOnMacOs() {
		// ...
		someService.print();
	}
	
}
