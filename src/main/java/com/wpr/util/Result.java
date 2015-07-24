package com.wpr.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * ͨ�õĽ����������
 * @author peirong.wpr
 *
 * @param <T>
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = -1848071965814004979L;
	/** �����Ƿ�ɹ� */
	private boolean success;
	/** ����� */
	private String code;
	/** ������� */
	private String desc;
	/** ��� */
	private T model;

	/**
	 * Ĭ�Ϲ��캯��
	 */
	public Result() {
		super();
	}

	/**
	 * Getter method for property <tt>success</tt>.
	 * 
	 * @return property value of success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Setter method for property <tt>success</tt>.
	 * 
	 * @param success
	 *            value to be assigned to property success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code
	 *            value to be assigned to property code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter method for property <tt>desc</tt>.
	 * 
	 * @return property value of desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Setter method for property <tt>desc</tt>.
	 * 
	 * @param desc
	 *            value to be assigned to property desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Getter method for property <tt>model</tt>.
	 * 
	 * @return property value of model
	 */
	public T getModel() {
		return model;
	}

	/**
	 * Setter method for property <tt>model</tt>.
	 * 
	 * @param model
	 *            value to be assigned to property model
	 */
	public void setModel(T model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
