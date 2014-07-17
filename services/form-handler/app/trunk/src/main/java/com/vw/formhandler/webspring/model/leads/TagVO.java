package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag", propOrder = {
    "key",
    "value"
    
})
/*
 * Represents the <tag>
 * 1) to support FormHandler all interfaces that support Leads Submission:
 */
public class TagVO implements Serializable
{
	@XmlElement
	protected String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlElement
	protected String value;
	
}
