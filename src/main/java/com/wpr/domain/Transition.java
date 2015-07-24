package com.wpr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 节点的下一次转变
 * @author peirong.wpr
 *
 */
public class Transition {
	/*调用其它类返回的结果集*/
	private String result;
	/*下一次的状态*/
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
