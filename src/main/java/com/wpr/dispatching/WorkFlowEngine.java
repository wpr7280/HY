package com.wpr.dispatching;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wpr.domain.Process;
import com.wpr.util.NewDocumentParser;

public class WorkFlowEngine implements InitializingBean,ApplicationContextAware {
	Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);
	@Autowired
	private String[] processXmlFiles;
	/*��ʾ��Ŀ����·��*/
	private String path;
	
	private ApplicationContext applicationContext;
	private Map<String,Process> processMap;
	
	public void afterPropertiesSet() throws Exception {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	/**
	 * �������process�Ľ�������ɳ�ʼ��
	 * @throws Exception
	 */
	public void init() throws Exception {
		// ��ɶ�ȡ���ã�����ʼ���̳߳�
		processMap = new HashMap<String, Process>();
		parser();
		logger.info("workengine��ʼ�������");
	}
	/**
	 * ����xml�ļ�
	 */
	public void parser() {
		for (String procXmlFile : processXmlFiles) {
			try {
				//����xml
				Process process = NewDocumentParser.parser(path+File.separator+procXmlFile);
				//�������
				process.check();
				//����process�Ķ���
				process.register(applicationContext);
//				processMap.put(process.getType(), process);
				System.out.println(process);
			} catch (Exception e) {
				e.printStackTrace();
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
