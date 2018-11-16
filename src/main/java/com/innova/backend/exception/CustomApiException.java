package com.innova.backend.exception;

public class CustomApiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomApiException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	private String code;
	private String message;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "CustomApiException [code=" + code + ", message=" + message + "]";
	}

	

}