package com.wpr.dispatching;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import com.wpr.domain.Task;

public class TaskPool {
	/*������̳߳�*/
	private ThreadPoolExecutor threadPoolExecutor;
	/*�������*/
	private BlockingQueue<Task> taskQueue;
}
