package com.wpr.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpr.dispatching.TaskExecutor;
import com.wpr.domain.Task;
import com.wpr.util.Result;
/**
 * 
 * @author peirong.wpr
 *
 */
public class DispatchingServiceImpl implements DispatchingService {
	private Logger logger = LoggerFactory.getLogger(DispatchingService.class);
	@Autowired
	private TaskExecutor taskExecutor;
	
	public Result<Void> addTask(Task task) {
		Long startTime = System.currentTimeMillis();
		Result<Void> result = taskExecutor.schedule(task);
		Long endTime =System.currentTimeMillis();
		logger.info("本次任务调度的时间是："+(endTime-startTime));
		return result;
	}

}
