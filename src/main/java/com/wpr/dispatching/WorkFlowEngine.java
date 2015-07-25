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
	/*��ʾ��Ŀ����·����java������ô��û���⣬�����web���̣���û���ApplicationContext*/
	private String path;

	public void afterPropertiesSet() throws Exception {
		// ��ɶ�ȡ���ã�����ʼ���̳߳�
		//TODO �����web��Ŀ������ط�Ӧ��ע�����ApplicationContext�Ķ��󣬸о������Ļ���õķ�ʽ��ʵ��ApplicationContextAware�ӿ�
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		parser();
	}
	/**
	 * ����xml�ļ�
	 */
	public void parser() {
		for (String procXmlFile : processXmlFiles) {
//			System.out.println(path+procXmlFile);
			try {
				ProcessDefinition processDefinition = NewDocumentParser.parser(path+File.separator+procXmlFile);
				System.out.println(processDefinition);
			} catch (Exception e) {
				new DocumentException("xml�ļ���������");
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
