package com.wpr.dispatching;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpr.domain.ProcessDefinition;
import com.wpr.domain.State;
import com.wpr.util.DocumentParser;
import com.wpr.util.NewDocumentParser;

public class WorkFlowEngine implements InitializingBean {
	@Autowired
	private String[] processXmlFiles;
	/*表示项目的类路径，java工程这么搞没问题，如果是web工程，最好换成ApplicationContext*/
	private String path;

	public void afterPropertiesSet() throws Exception {
		// 完成读取配置，并初始化线程池
		//TODO 如果是web项目，这个地方应该注入的是ApplicationContext的对象，感觉那样的话最好的方式是实现ApplicationContextAware接口
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		parser();
	}
	/**
	 * 解析xml文件
	 */
	public void parser() {
		for (String procXmlFile : processXmlFiles) {
//			System.out.println(path+procXmlFile);
			try {
				ProcessDefinition processDefinition = NewDocumentParser.parser(path+File.separator+procXmlFile);
				System.out.println(processDefinition);
			} catch (Exception e) {
				new DocumentException("xml文件解析错误");
			}
		}
	}

	public String[] getProcessXmlFiles() {
		return processXmlFiles;
	}

	public void setProcessXmlFiles(String[] processXmlFiles) {
		this.processXmlFiles = processXmlFiles;
	}


	
}
