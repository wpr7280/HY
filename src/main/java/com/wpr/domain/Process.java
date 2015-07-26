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
 * 一次处理中抽象的流程逻辑
 * @author peirong.wpr
 *
 */
public class Process {
	Logger logger = LoggerFactory.getLogger(Process.class);
	/*表征线程池的类型*/
	private String type;
	/*处理流程的简单描述*/
	private String desc;
	/*默认的状态 */
	private String defaultState;
	/*一个流程中所有的状态类，key是State的id，value是State对象*/
	private Map<String,State> states;
	/*注册的方法，key是state的id，value是state定义的方法*/
	private Map<String,Method> stateProxy;
	/*上下文*/
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
	/**
	 * 把xml文件中定义的方法注册到stateProxy中，由这个去统一处理
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
	 * 调用方法的过程
	 * @param baseDO     传入的参数
	 * @param stateId    要调用状态的标志
	 * @throws MethodInvokeException 
	 */
	public Object invoke(BaseDO baseDO, String stateId) throws MethodInvokeException {
		logger.info("开始调用具体的方法"+states.get(stateId).toString());
		try {
			//根据定义的状态，得到需要调用的方法
			Method method = stateProxy.get(stateId);
			//得到需要调用的类对象
			Object object = applicationContext.getBean(states.get(stateId).getTarget());
			Object invokeResult = method.invoke(object, baseDO);
			logger.info("调用方法结束");
			return invokeResult;
		} catch (IllegalAccessException e) {
			logger.error("参数不正确");
			throw new MethodInvokeException(e);
		} catch (IllegalArgumentException e) {
			logger.error("参数不正确");
			throw new MethodInvokeException(e);
		} catch (InvocationTargetException e) {
			logger.error("调用方法失败");
			throw new MethodInvokeException(e);
		}
	}
}
