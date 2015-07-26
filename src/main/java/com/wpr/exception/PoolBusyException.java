package com.wpr.exception;
/**
 * 当每个Processpool处理的事件达到最大时，报这个错误
 * @author peirong.wpr
 *
 */
public class PoolBusyException extends Exception{

	public PoolBusyException(InterruptedException e) {
		super(e);
	}

}
