package com.wpr.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wpr.dispatching.WorkFlowEngine;
import com.wpr.exception.PoolBusyException;
import com.wpr.service.DispatchingService;
import com.wpr.testdomain.TestDO;

public class DocumentParserTest {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void before(){
		String springResourcePath = "spring-bean.xml";
		ctx = new ClassPathXmlApplicationContext(springResourcePath);
	}
	@Test
	public void testDocumentParserTest(){
		WorkFlowEngine workFlowEngine = (WorkFlowEngine) ctx.getBean("workFlowEngine");
	}
	@Test
	public void testPath(){
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
	}
	/**
	 * 测试一下能够执行方法
	 * @throws PoolBusyException 
	 */
	@Test
	public void testProcess() throws PoolBusyException{
		DispatchingService dispatchingService =(DispatchingService) ctx.getBean("dispatchingService");
		TestDO testDO = new TestDO("service");
		dispatchingService.addTask(testDO);
	}
}
