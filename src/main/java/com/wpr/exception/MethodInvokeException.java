package com.wpr.exception;

public class MethodInvokeException extends Exception {
	public MethodInvokeException() {
		super();
	}

	public MethodInvokeException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	public MethodInvokeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MethodInvokeException(String arg0) {
		super(arg0);
	}

	public MethodInvokeException(Throwable arg0) {
		super(arg0);
	}
	
}
