package com.wpr.service;

import com.wpr.domain.BaseDO;
import com.wpr.exception.PoolBusyException;
import com.wpr.util.Result;
/**
 * �����ṩ�Ľӿ�
 * @author peirong.wpr
 *
 */
public interface DispatchingService {
	/**
	 * ִ��һ���µ�����
	 * @param task  
	 * @return
	 * @throws PoolBusyException   ��ProcessPool����æ��ʱ����׳�����쳣����Ҫҵ��ȥ����
	 */
	public Result<Void> addTask(BaseDO task) throws PoolBusyException;
}
