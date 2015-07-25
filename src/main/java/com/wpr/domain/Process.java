package com.wpr.domain;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;


/**
 * 一次处理中抽象的流程逻辑
 * @author peirong.wpr
 *
 */
public class Process {
	/*表征线程池的类型*/
	private String type;
	/*处理流程的简单描述*/
	private String desc;
	/*默认的状态 */
	private String defaultState;
	/*一个流程中所有的状态类，key是State的id，value是State对象*/
	private Map<String,State> states;
	/*注册的方法*/
	private Map<String,Method> stateProxy;
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
	/**
	 * 检查流程的正确性
	 * @throws DocumentException 
	 */
	public void check() throws DocumentException {
		//检查是否存在引用了不正确的State
		for(String key:states.keySet()){
			State temp = states.get(key);
			if(!states.containsKey(temp.getId())){
				throw new DocumentException("文档定义了不存在的State");
			}
			//检查transition的定义是否正确
			for(Transition t:temp.getTransitions()){
				if(!states.containsKey(t.getTo())){
					throw new DocumentException("文档定义了不存在的State");
				}
			}
		}
	}
	public void register(ApplicationContext applicationContext) throws NoSuchMethodException, SecurityException {
		stateProxy = new HashMap<String, Method>();
		for(String key:states.keySet()){
			State state = states.get(key);
			Object bean = applicationContext.getBean(state.getTarget());
			//TODO 调用方法的参数怎么设计？
			Method method = bean.getClass().getMethod(state.getMethod(), null);
			stateProxy.put(state.getId(),method);
		}
	}
}
