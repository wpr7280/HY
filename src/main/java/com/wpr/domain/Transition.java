package com.wpr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * �ڵ����һ��ת��
 * @author peirong.wpr
 *
 */
public class Transition {
	/*���������෵�صĽ����*/
	private String result;
	/*��һ�ε�״̬*/
	private String to;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
