package com.wpr.dispatching;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

public class ProcessPool {
	/*������̳߳�*/
	private ThreadPoolExecutor threadPoolExecutor;
	//�о�������в����Ǻܱ�Ҫ����ʱ���Ȳ�Ū��
//	private BlockingQueue<Task> taskQueue;
	/*��֤һ��ʱ���ڶ�ĳ������ĵ��Ȳ��ᳬ��ĳ����ֵ*/
	private Semaphore semaphore;
	public ProcessPool(int capacity) {
		semaphore = new Semaphore(capacity,true);
	}
}
