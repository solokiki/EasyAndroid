package com.lonn.core.utils;

public class MyException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String message;
	private int statusCode = -1;

	public MyException() {
	}

	public MyException(String message) {
		this.message = message;
	}

	public MyException(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
