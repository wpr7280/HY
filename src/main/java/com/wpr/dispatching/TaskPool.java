package com.wpr.dispatching;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import com.wpr.domain.Task;

public class TaskPool {
	/*具体的线程池*/
	private ThreadPoolExecutor threadPoolExecutor;
	/*任务队列*/
	private BlockingQueue<Task> taskQueue;
}
