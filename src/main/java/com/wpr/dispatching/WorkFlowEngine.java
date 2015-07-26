package com.wpr.dispatching;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wpr.domain.Process;
import com.wpr.domain.BaseDO;
import com.wpr.domain.Transition;
import com.wpr.exception.MethodException;
import com.wpr.exception.PoolBusyException;
import com.wpr.exception.ProcessPoolNotSupportException;
import com.wpr.util.NewDocumentParser;
import com.wpr.util.Result;
/**
 * 
 * @author peirong.wpr
 *
 */
@SuppressWarnings("deprecation")
public class WorkFlowEngine implements InitializingBean,ApplicationContextAware {
	Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);
	/*ÿһ���̳߳صĲ���������*/
	//TODO ֮����Ըĳ�ÿһ��type��process�Ĵ����в�ͬ��ֵ���������Զ�ȡ�����ļ�����ȡ
	public static final int DEFAULT_CONCURRENT_TASK_COUNT = 50;
	
	@Autowired
	private String[] processXmlFiles;
	/*��ʾ��Ŀ����·��*/
	private String path;
	
	private ApplicationContext applicationContext;
	/*��ŷ����������Ŀ��key��xml�ļ��ж����process��type��value�Ƕ�Ӧ��process*/
	private Map<String,Process> processMap;
	/*ÿһ�ֲ�ͬtype��process��Ӧ��һ���̳߳أ���������Ŀ���Ǹ��õĿ��Ƹ������ݳأ��������ڿ����������ļ������������̳߳ص��������Ա�֤���õ�����*/
	private Map<String,ProcessPool> processPools;
	
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
		parser();
		processPools = new HashMap<String, ProcessPool>();
		for(String key:processMap.keySet()){
			ProcessPool pool = new ProcessPool(key,DEFAULT_CONCURRENT_TASK_COUNT);
			processPools.put(key,pool);
		}
		logger.info("workengine��ʼ�������");
	}
	/**
	 * ����xml�ļ�
	 */
	public void parser() {
		processMap = new HashMap<String, Process>();
		for (String procXmlFile : processXmlFiles) {
			try {
				//����xml
				Process process = NewDocumentParser.parser(path+File.separator+procXmlFile);
				//�������
				process.check();
				//����process�Ķ���
				process.register(applicationContext);
				processMap.put(process.getType(), process);
//				System.out.println(process);
			} catch (Exception e) {
				e.printStackTrace();
				new DocumentException("xml�ļ���������");
			}
		}
	}
	/**
	 * ��ʼ�����̽��е���
	 * @param baseDO  ����
	 * @return
	 * @throws PoolBusyException 
	 * @throws MethodException 
	 * @throws ProcessPoolNotSupportException 
	 */
	@SuppressWarnings("deprecation")
	public Result<Void> schedule(BaseDO baseDO) throws PoolBusyException  {
		Result<Void> result = new Result<Void>();
		ProcessPool pool = processPools.get(baseDO.getType());
		if(pool==null){
			logger.error("û�����ö�Ӧtype�Ĵ�������");
			//TODO ���ò�����
			result.setSuccess(false);
			return result;
		}
		String stateId;
		Process process = processMap.get(baseDO.getType());
		Assert.assertNotNull(process);
		
		if(StringUtils.isBlank(baseDO.getNext())){
			stateId = process.getDefaultState();
			baseDO.setNext(stateId);
		}else{
			stateId = baseDO.getNext();
		}
		Object obj = pool.schdule(baseDO,stateId,process);
		if(obj==null){
			//TODO ����ط�֮��������
			logger.info("���÷������صĲ�������");
			return null;
		}
		if(!(obj instanceof BaseDO)){
			//TODO ��ʱ�Ȳ�����Щ�����ˣ�֮������
			logger.info("���صĲ������Ͳ���ȷ");
			return null;
		}
		BaseDO nextDO = (BaseDO) obj; 
		Transition transition = process.getNextState(stateId,nextDO);
		if(transition==null){
			//˵��û������֮���transition�������߼��£��������Ӧ���ǽ�����
			//TODO ��Ȼû�д����ؽ��
			logger.info("�������̴��������");
			return null;
		}
		baseDO.setNext(transition.getTo());
		this.schedule(baseDO);
		return null;
	}
	
	public String[] getProcessXmlFiles() {
		return processXmlFiles;
	}

	public void setProcessXmlFiles(String[] processXmlFiles) {
		this.processXmlFiles = processXmlFiles;
	}


}
