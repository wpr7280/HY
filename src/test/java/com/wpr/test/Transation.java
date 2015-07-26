package com.wpr.test;

import com.wpr.domain.BaseDO;

public class Transation {
	public void first(BaseDO task){
		System.out.println("执行第一步操作");
	}
	public void second(BaseDO task){
		System.out.println("执行第二步操作");
	}
}
