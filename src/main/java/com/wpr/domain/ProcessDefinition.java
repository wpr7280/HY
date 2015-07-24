package com.wpr.domain;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * һ�δ����г���������߼�
 * @author peirong.wpr
 *
 */
public class ProcessDefinition {
	/*�����̳߳ص�����*/
	private String type;
	/*�������̵ļ�����*/
	private String desc;
	/*Ĭ�ϵ�״̬ */
	private String defaultState;
	/*һ�����������е�״̬�࣬key��State��id��value��State����*/
	private Map<String,State> states;
	
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
	public Map<String, State> getStates() {
		return states;
	}
	public void setStates(Map<String, State> states) {
		this.states = states;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
