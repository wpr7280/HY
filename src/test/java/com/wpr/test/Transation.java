package com.wpr.test;

import com.wpr.domain.BaseDO;

public class Transation {
	public BaseDO first(BaseDO baseDO){
		System.out.println("ִ�е�һ������");
		baseDO.setResultCode("SUCCESS");
		return baseDO;
	}
	public BaseDO second(BaseDO baseDO){
		System.out.println("ִ�еڶ�������");
		baseDO.setResultCode("SUCCESS");
		return baseDO;
	}
	public BaseDO third(BaseDO baseDO){
		System.out.println("ִ�����һ������");
		return baseDO;
	}
}
