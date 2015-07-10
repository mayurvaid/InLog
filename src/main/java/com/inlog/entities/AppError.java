package com.inlog.entities;

public class AppError extends BaseDataObject{
	private String errorMessage;
	private String erroCode;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErroCode() {
		return erroCode;
	}
	public void setErroCode(String erroCode) {
		this.erroCode = erroCode;
	}
	
	@Override
	public String toString() {
		return "AccessError [errorMessage=" + errorMessage + ", erroCode="
				+ erroCode + "]";
	}
	
	
}
