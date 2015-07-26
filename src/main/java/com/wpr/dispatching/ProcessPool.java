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
	/*表明处理的类型*/
	private String type; 
	/*具体的线程池*/
	private ThreadPoolExecutor threadPoolExecutor;
	
//	private WorkFlowEngine workFlowEngine;
	//感觉任务队列并不是很必要，暂时就先不弄了
//	private BlockingQueue<Task> taskQueue;
	/*保证一段时间内对某个任务的调度不会超过某个阈值*/
	private Semaphore semaphore;
	public ProcessPool(String type,int capacity) {
		this.type =type;
		semaphore = new Semaphore(capacity,true);
	}
	/**
	 * 由当前pool对象处理
	 * @param baseDO     传递的参数
	 * @param stateId    要执行的State的ID
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
			logger.info("当前"+type+"ProcessPool出了问题");
			throw new PoolBusyException(e);
		} catch (MethodInvokeException e) {
			logger.error("调用方法失败");
			e.printStackTrace();
		}
		return obj;
	}

}
