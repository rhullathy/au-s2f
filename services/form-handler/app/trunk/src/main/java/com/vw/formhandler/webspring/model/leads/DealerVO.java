package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dealer", propOrder = {
    "dealerId"
})
public class DealerVO implements Serializable 
{
	@XmlElement(required=true)
	protected String dealerId; //dealerCode of dealer

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
}
