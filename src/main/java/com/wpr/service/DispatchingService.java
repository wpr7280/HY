package com.wpr.service;

import com.wpr.domain.Task;
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
	 */
	public Result<Void> addTask(Task task);
}
