package com.inlog.entities;

public class InlogException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InlogException() {
		super();
	}

	public InlogException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InlogException(String message, Throwable cause) {
		super(message, cause);
	}

	public InlogException(String message) {
		super(message);
	}

	public InlogException(Throwable cause) {
		super(cause);
	}

	
}
