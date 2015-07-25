package com.wpr.domain;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;


/**
 * һ�δ����г���������߼�
 * @author peirong.wpr
 *
 */
public class Process {
	/*�����̳߳ص�����*/
	private String type;
	/*�������̵ļ�����*/
	private String desc;
	/*Ĭ�ϵ�״̬ */
	private String defaultState;
	/*һ�����������е�״̬�࣬key��State��id��value��State����*/
	private Map<String,State> states;
	/*ע��ķ���*/
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
	 * ������̵���ȷ��
	 * @throws DocumentException 
	 */
	public void check() throws DocumentException {
		//����Ƿ���������˲���ȷ��State
		for(String key:states.keySet()){
			State temp = states.get(key);
			if(!states.containsKey(temp.getId())){
				throw new DocumentException("�ĵ������˲����ڵ�State");
			}
			//���transition�Ķ����Ƿ���ȷ
			for(Transition t:temp.getTransitions()){
				if(!states.containsKey(t.getTo())){
					throw new DocumentException("�ĵ������˲����ڵ�State");
				}
			}
		}
	}
	public void register(ApplicationContext applicationContext) throws NoSuchMethodException, SecurityException {
		stateProxy = new HashMap<String, Method>();
		for(String key:states.keySet()){
			State state = states.get(key);
			Object bean = applicationContext.getBean(state.getTarget());
			//TODO ���÷����Ĳ�����ô��ƣ�
			Method method = bean.getClass().getMethod(state.getMethod(), null);
			stateProxy.put(state.getId(),method);
		}
	}
}
