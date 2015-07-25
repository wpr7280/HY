package com.wpr.service;

import com.wpr.domain.Task;
import com.wpr.util.Result;
/**
 * 对外提供的接口
 * @author peirong.wpr
 *
 */
public interface DispatchingService {
	/**
	 * 执行一个新的流程
	 * @param task  
	 * @return
	 */
	public Result<Void> addTask(Task task);
}
