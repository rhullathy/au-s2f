package com.vw.formhandler.webspring.model.customercare;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "experience", namespace="customercare",  propOrder = {
    "reasonCode",
    "dealershipFlag",
    "comments"
})

public class ExperienceVO {

    
    @XmlElement(name="reason-code")
    protected String reasonCode;
    
    @XmlElement(name="dealership-flag")
    protected String  dealershipFlag;
    
    @XmlElement(name="comments")
    protected String comments;

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getDealershipFlag() {
        return dealershipFlag;
    }

    public void setDealershipFlag(String dealershipFlag) {
        this.dealershipFlag = dealershipFlag;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
   
}
