package com.wpr.dispatching;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wpr.domain.BaseDO;
import com.wpr.domain.Process;
import com.wpr.exception.MethodInvokeException;
import com.wpr.exception.PoolBusyException;
import com.wpr.util.Result;
/**
 * 
 * @author peirong.wpr
 *
 */
public class ProcessPool {
	Logger logger = LoggerFactory.getLogger(ProcessPool.class);
	/*�������������*/
	private String type; 
	/*������̳߳�*/
	private ThreadPoolExecutor threadPoolExecutor;
	
//	private WorkFlowEngine workFlowEngine;
	//�о�������в����Ǻܱ�Ҫ����ʱ���Ȳ�Ū��
//	private BlockingQueue<Task> taskQueue;
	/*��֤һ��ʱ���ڶ�ĳ������ĵ��Ȳ��ᳬ��ĳ����ֵ*/
	private Semaphore semaphore;
	public ProcessPool(String type,int capacity) {
		this.type =type;
		semaphore = new Semaphore(capacity,true);
	}
	/**
	 * �ɵ�ǰpool������
	 * @param baseDO     ���ݵĲ���
	 * @param stateId    Ҫִ�е�State��ID
	 * @param process 
	 * @return
	 * @throws PoolBusyException 
	 */
	public Object schdule(BaseDO baseDO, String stateId, Process process) throws PoolBusyException {
		Object obj = null;
		try {
			semaphore.acquire();
			obj =process.invoke(baseDO,stateId);
			semaphore.release();
		} catch (InterruptedException e) {
			logger.info("��ǰ"+type+"ProcessPool��������");
			throw new PoolBusyException(e);
		} catch (MethodInvokeException e) {
			logger.error("���÷���ʧ��");
			e.printStackTrace();
		}
		return obj;
	}

}
