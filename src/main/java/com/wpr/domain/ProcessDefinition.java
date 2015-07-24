package com.wpr.domain;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 一次处理中抽象的流程逻辑
 * @author peirong.wpr
 *
 */
public class ProcessDefinition {
	/*表征线程池的类型*/
	private String type;
	/*处理流程的简单描述*/
	private String desc;
	/*默认的状态 */
	private String defaultState;
	/*一个流程中所有的状态类，key是State的id，value是State对象*/
	private Map<String,String> states;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDefaultState() {
		return defaultState;
	}
	public void setDefaultState(String defaultState) {
		this.defaultState = defaultState;
	}
	public Map<String, String> getStates() {
		return states;
	}
	public void setStates(Map<String, String> states) {
		this.states = states;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
