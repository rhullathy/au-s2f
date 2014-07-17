package com.vw.formhandler.webspring.mvc.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Kunal Bhatia
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={
	    "xmlSource", 
	    "responseCode"
})
public abstract class AbstractServiceResponseErrorVO extends AbstractError
{
	@XmlElement
    private String xmlSource;

	@XmlElement(name="HttpResponseStatusCode")
	private Integer responseCode;

	public String getXmlSource() {
		return xmlSource;
	}

	public void setXmlSource(String xmlSource) {
		this.xmlSource = xmlSource;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
}
