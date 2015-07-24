package com.wpr.domain;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;



/**
 * 定义的状态类
 * @author peirong.wpr
 *
 */
public class State {
	
	private String id;
	/*要调用的对象*/
	private String target;
	/*将调用对象的方法 */
	private String method;
//	/*当前状态的简单描述*/
//	private String desc;
	/*节点的转换描述*/
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
