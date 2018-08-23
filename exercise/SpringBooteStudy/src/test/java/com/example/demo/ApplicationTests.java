package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private BlogProperties blogProperties;
	
	@Test
	public void contextLoads() throws Exception{
		Assert.assertEquals(blogProperties.getName(), "程序猿DD");
		Assert.assertEquals(blogProperties.getTitle(), "Spring Boot教程");
	}

}
