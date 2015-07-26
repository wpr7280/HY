package com.wpr.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.wpr.exception.MethodInvokeException;


/**
 * һ�δ����г���������߼�
 * @author peirong.wpr
 *
 */
public class Process {
	Logger logger = LoggerFactory.getLogger(Process.class);
	/*�����̳߳ص�����*/
	private String type;
	/*�������̵ļ�����*/
	private String desc;
	/*Ĭ�ϵ�״̬ */
	private String defaultState;
	/*һ�����������е�״̬�࣬key��State��id��value��State����*/
	private Map<String,State> states;
	/*ע��ķ�����key��state��id��value��state����ķ���*/
	private Map<String,Method> stateProxy;
	/*������*/
	private ApplicationContext applicationContext;
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
	/**
	 * ��xml�ļ��ж���ķ���ע�ᵽstateProxy�У������ȥͳһ����
	 * @param applicationContext
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public void register(ApplicationContext applicationContext) throws NoSuchMethodException, SecurityException {
		stateProxy = new HashMap<String, Method>();
		for(String key:states.keySet()){
			State state = states.get(key);
			Object bean = applicationContext.getBean(state.getTarget());
			Method method = bean.getClass().getMethod(state.getMethod(), BaseDO.class);
			stateProxy.put(state.getId(),method);
		}
		this.applicationContext= applicationContext;
	}
	/**
	 * ���÷����Ĺ���
	 * @param baseDO     ����Ĳ���
	 * @param stateId    Ҫ����״̬�ı�־
	 * @throws MethodInvokeException 
	 */
	public Object invoke(BaseDO baseDO, String stateId) throws MethodInvokeException {
		logger.info("��ʼ���þ���ķ���"+states.get(stateId).toString());
		try {
			//���ݶ����״̬���õ���Ҫ���õķ���
			Method method = stateProxy.get(stateId);
			//�õ���Ҫ���õ������
			Object object = applicationContext.getBean(states.get(stateId).getTarget());
			Object invokeResult = method.invoke(object, baseDO);
			logger.info("���÷�������");
			return invokeResult;
		} catch (IllegalAccessException e) {
			logger.error("��������ȷ");
			throw new MethodInvokeException(e);
		} catch (IllegalArgumentException e) {
			logger.error("��������ȷ");
			throw new MethodInvokeException(e);
		} catch (InvocationTargetException e) {
			logger.error("���÷���ʧ��");
			throw new MethodInvokeException(e);
		}
	}
}
