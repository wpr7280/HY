package com.wpr.dispatching;

import java.io.InputStream;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpr.exception.DocumentParserException;
import com.wpr.util.DocumentParser;
import com.wpr.util.Result;



public class TaskExecutor implements InitializingBean{
	@Autowired
	private String[] processXmlFiles;
//	private Map<String,TaskPool> threadPools;
	public void afterPropertiesSet() throws Exception {
		//��ɶ�ȡ���ã�����ʼ���̳߳�
		for(String procXmlFile:processXmlFiles){
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream(procXmlFile);
			try {
				Result<Void> result = DocumentParser.parse(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}

}
