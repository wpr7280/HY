package com.wpr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * �������Model�ı�ʾ��������úܲ��Ҫ��֮ǰû����������������
 * @author peirong.wpr
 *
 */
public class BaseDO {
	/*����Ҫ��process����*/
	protected String type;
	/*��һ��Ҫִ�е�state��id*/
	protected String next;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
