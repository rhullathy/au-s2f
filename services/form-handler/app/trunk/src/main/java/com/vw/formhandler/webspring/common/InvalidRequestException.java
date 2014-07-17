package com.vw.formhandler.webspring.common;

import com.vw.formhandler.webspring.mvc.response.Errors;

public class InvalidRequestException extends Exception {

	private static final long serialVersionUID = -2667038806035504353L;
	private Errors errors;
	String errorAbsoluteURL;
	String emailStr;

	public InvalidRequestException(Errors errors2, String errorAbsoluteURL, String emailStr) {
		errors=errors2;
		this.errorAbsoluteURL = errorAbsoluteURL;
		this.emailStr = emailStr;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

	public String getErrorAbsoluteURL() {
		return errorAbsoluteURL;
	}

	public String getEmailStr() {
		return emailStr;
	}
}
