package com.vw.formhandler.webspring.mvc.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Kunal Bhatia
 */
@SuppressWarnings("serial")
@XmlRootElement(name="error")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceResponseException", propOrder={
    "errorCode", 
    "message", 
    "xmlSource"
})
public class ServiceResponseException implements Serializable
{
	@XmlElement
	private String message;

	@XmlElement
    private String xmlSource;

	@XmlElement
	private String errorCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getXmlSource() {
		return xmlSource;
	}

	public void setXmlSource(String xmlSource) {
		this.xmlSource = xmlSource;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
