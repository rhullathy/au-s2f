package com.vw.formhandler.common;

import java.util.Stack;

public class HttpClientUtilResponse
{
	int status;
	boolean urlPostSuccessful;
	byte[] responseByteArray;
	Stack<HttpClientUtilResponse> previousTryResponsesStack;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isUrlPostSuccessful() {
		return urlPostSuccessful;
	}
	public void setUrlPostSuccessful(boolean urlPostSuccessful) {
		this.urlPostSuccessful = urlPostSuccessful;
	}

	public byte[] getResponseByteArray() {
		return responseByteArray;
	}
	public void setResponseByteArray(byte[] responseByteArray) {
		this.responseByteArray = responseByteArray;
	}
	
	public Stack<HttpClientUtilResponse> getPreviousTryResponsesStack() {
		return previousTryResponsesStack;
	}
	public void setPreviousTryResponsesStack(
			Stack<HttpClientUtilResponse> previousTryResponsesStack) {
		this.previousTryResponsesStack = previousTryResponsesStack;
	}
}
