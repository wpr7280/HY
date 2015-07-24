package com.wpr.dispatching;

import java.io.InputStream;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpr.domain.ProcessDefinition;
import com.wpr.domain.State;
import com.wpr.util.DocumentParser;

public class WorkFlowEngine implements InitializingBean {
	@Autowired
	private String[] processXmlFiles;

	public void afterPropertiesSet() throws Exception {
		// 完成读取配置，并初始化线程池

	}

	public void parse() {
		for (String procXmlFile : processXmlFiles) {
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream(procXmlFile);
			try {
				ProcessDefinition processDefinition = DocumentParser.parse(in);
				System.out.println(processDefinition);
//				for(Map.Entry<String, State> s:processDefinition.getStates().entrySet()){
//					System.out.println(s);
//				}
			} catch (Exception e) {
				e.printStackTrace();
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
