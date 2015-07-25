package com.wpr.dispatching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wpr.domain.Task;
import com.wpr.util.Result;



public class TaskExecutor {
	private Logger logger = LoggerFactory.getLogger(TaskExecutor.class);
	/**
	 * 完成对一个流程的调度
	 * @param task  整个流程中需要的参数对象的封装Model
	 * @return
	 */
	public Result<Void> schedule(Task task) {
		
		return null;
	}
}
