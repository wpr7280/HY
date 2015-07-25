package com.wpr.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wpr.dispatching.WorkFlowEngine;

public class DocumentParserTest {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void before(){
		String springResourcePath = "spring-bean.xml";
		ctx = new ClassPathXmlApplicationContext(springResourcePath);
	}
	@Test
	public void testDocumentParserTest(){
//		WorkFlowEngine workFlowEngine = (WorkFlowEngine) ctx.getBean("workFlowEngine");
//		workFlowEngine.parser();
	}
	@Test
	public void testPath(){
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
	}
}
