package com.wpr.domain;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;



/**
 * �����״̬��
 * @author peirong.wpr
 *
 */
public class State {
	
	private String id;
	/*Ҫ���õĶ���*/
	private String target;
	/*�����ö���ķ��� */
	private String method;
//	/*��ǰ״̬�ļ�����*/
//	private String desc;
	/*�ڵ��ת������*/
	private List<Transition> transitions;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public List<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
