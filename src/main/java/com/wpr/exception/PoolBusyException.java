package com.wpr.exception;
/**
 * ��ÿ��Processpool������¼��ﵽ���ʱ�����������
 * @author peirong.wpr
 *
 */
public class PoolBusyException extends Exception{

	public PoolBusyException(InterruptedException e) {
		super(e);
	}

}
