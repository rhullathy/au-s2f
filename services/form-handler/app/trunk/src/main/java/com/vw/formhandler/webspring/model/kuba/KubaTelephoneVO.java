package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "telephone")
public class KubaTelephoneVO implements Serializable
{
	@XmlAttribute(name="preferred", required=true)
	protected Boolean preferredFlag;

	@XmlValue
	protected String telephoneStr;

	public Boolean getPreferredFlag() {
		return preferredFlag;
	}

	public void setPreferredFlag(Boolean preferredFlag) {
		this.preferredFlag = preferredFlag;
	}

	public String getTelephoneStr() {
		return telephoneStr;
	}

	public void setTelephoneStr(String telephoneStr) {
		this.telephoneStr = telephoneStr;
	}
}
