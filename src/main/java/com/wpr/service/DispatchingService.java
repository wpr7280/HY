package com.wpr.service;

import com.wpr.domain.BaseDO;
import com.wpr.exception.PoolBusyException;
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
	 * @throws PoolBusyException   当ProcessPool调度忙的时候会抛出这个异常，需要业务方去处理
	 */
	public Result<Void> addTask(BaseDO task) throws PoolBusyException;
}
