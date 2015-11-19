package com.gophergroceries.results;

public class ErrorResult {

	private boolean error = false;
	private String errorMsg = "No Error";
	
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
