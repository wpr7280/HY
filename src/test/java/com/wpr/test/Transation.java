package com.wpr.test;

import com.wpr.domain.BaseDO;

public class Transation {
	public void first(BaseDO task){
		System.out.println("ִ�е�һ������");
	}
	public void second(BaseDO task){
		System.out.println("ִ�еڶ�������");
	}
}
