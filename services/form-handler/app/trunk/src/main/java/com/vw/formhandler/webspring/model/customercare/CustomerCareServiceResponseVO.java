package com.vw.formhandler.webspring.model.customercare;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@SuppressWarnings("serial")
@XmlRootElement(name="customer-care-case")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customer-care-case", namespace="customercare", propOrder = {
        "vin",
        "caseNumber"
    })
    
public class CustomerCareServiceResponseVO implements Serializable
{
    @XmlElement(name="vin")
    protected String vin;
    
    @XmlElement(name="case-number")
    protected String caseNumber;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }
    
}
