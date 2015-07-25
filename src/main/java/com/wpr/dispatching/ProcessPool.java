package com.wpr.dispatching;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

public class ProcessPool {
	/*具体的线程池*/
	private ThreadPoolExecutor threadPoolExecutor;
	//感觉任务队列并不是很必要，暂时就先不弄了
//	private BlockingQueue<Task> taskQueue;
	/*保证一段时间内对某个任务的调度不会超过某个阈值*/
	private Semaphore semaphore;
	public ProcessPool(int capacity) {
		semaphore = new Semaphore(capacity,true);
	}
}
