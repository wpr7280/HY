package com.wpr.test;

import com.wpr.domain.BaseDO;

public class Transation {
	public BaseDO first(BaseDO baseDO){
		System.out.println("执行第一步操作");
		baseDO.setResultCode("SUCCESS");
		return baseDO;
	}
	public BaseDO second(BaseDO baseDO){
		System.out.println("执行第二步操作");
		baseDO.setResultCode("SUCCESS");
		return baseDO;
	}
	public BaseDO third(BaseDO baseDO){
		System.out.println("执行最后一步操作");
		return baseDO;
	}
}
