package com.gophergroceries.results;

public class ErrorResult {

	private boolean error = true;
	private String errorMsg = "An Uncaught Error has occured";

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
