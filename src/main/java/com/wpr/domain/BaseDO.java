package com.wpr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 传入参数Model的标示，名字起得很差，主要是之前没打算这样做。。。
 * @author peirong.wpr
 *
 */
public class BaseDO {
	/*改类要的process类型*/
	protected String type;
	/*下一次要执行的state的id*/
	protected String next;
	/*返回的结果状态*/
	protected String resultCode;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
