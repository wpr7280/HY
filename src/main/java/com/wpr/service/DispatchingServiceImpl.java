package com.wpr.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wpr.dispatching.WorkFlowEngine;
import com.wpr.domain.BaseDO;
import com.wpr.exception.MethodException;
import com.wpr.exception.PoolBusyException;
import com.wpr.util.Result;
/**
 * 
 * @author peirong.wpr
 *
 */
public class DispatchingServiceImpl implements DispatchingService {
	private Logger logger = LoggerFactory.getLogger(DispatchingService.class);
	private WorkFlowEngine workFlowEngine;
	
	public Result<Void> addTask(BaseDO task) throws PoolBusyException  {
		Long startTime = System.currentTimeMillis();
		Result<Void> result;
		result = workFlowEngine.schedule(task);
		Long endTime =System.currentTimeMillis();
		logger.info("本次任务调度的时间是："+(endTime-startTime));
		return result;
	}

	public WorkFlowEngine getWorkFlowEngine() {
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine) {
		this.workFlowEngine = workFlowEngine;
	}

}
