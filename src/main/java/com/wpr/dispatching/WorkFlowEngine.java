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
/**
 * 
 * @author peirong.wpr
 *
 */
public class WorkFlowEngine implements InitializingBean,ApplicationContextAware {
	Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);
	@Autowired
	private String[] processXmlFiles;
	/*表示项目的类路径*/
	private String path;
	
	private ApplicationContext applicationContext;
	/*存放服务的类型数目，key是xml文件中定义的process的type，value是对应的process*/
	private Map<String,Process> processMap;
	/*每一种不同type的process对应着一个线程池，这样做的目的是更好的控制各个数据池，甚至后期可以由配置文件来决定各个线程池的容量，以保证良好的性能*/
	private Map<String,ProcessPool> processPools;
	
	public void afterPropertiesSet() throws Exception {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	/**
	 * 完成流程process的解析，完成初始化
	 * @throws Exception
	 */
	public void init() throws Exception {
		// 完成读取配置，并初始化线程池
		processMap = new HashMap<String, Process>();
		parser();
		logger.info("workengine初始化完成了");
	}
	/**
	 * 解析xml文件
	 */
	public void parser() {
		for (String procXmlFile : processXmlFiles) {
			try {
				//解析xml
				Process process = NewDocumentParser.parser(path+File.separator+procXmlFile);
				//参数检查
				process.check();
				//完善process的定义
				process.register(applicationContext);
				processMap.put(process.getType(), process);
				System.out.println(process);
			} catch (Exception e) {
				e.printStackTrace();
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
