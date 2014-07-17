package com.vw.formhandler.webspring.model.customercare;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dealer", namespace="customercare",  propOrder = {
    "contactName",
    "dealerId"
})

public class DealerVO {
    @XmlElement(name="contact-name")
     protected String  contactName;
     
    @XmlElement(name="dealer-id")
    protected String  dealerId;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }
    
}
