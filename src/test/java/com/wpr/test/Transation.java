package com.wpr.test;

import com.wpr.domain.Task;

public class Transation {
	public void first(Task task){
		System.out.println("执行第一步操作");
	}
	public void second(Task task){
		System.out.println("执行第二步操作");
	}
}
