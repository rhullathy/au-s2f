package com.vw.formhandler.webspring.model.customercare;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contact", namespace="customercare", propOrder = {
    "emailIndicator",
    "bestTimeToCallCode",
    "ownerRelationship",
    "firstName",
    "lastName",
    "phone",
    "zipCode", 
    "email"   
})
public class ContactVO {
  
    @XmlElement(name="email-indicator")                     
    protected String emailIndicator;

    @XmlElement(name="first-name")                               
    protected String firstName;

    @XmlElement(name="last-name")                           
    protected String lastName;

    @XmlElement(name="zip-code")                            
    protected String zipCode;
     
    @XmlElement(name="phone")                               
    protected String phone;

    @XmlElement(name="best-time-to-call-code")              
    protected String bestTimeToCallCode;
    
    @XmlElement(name="email")                               
    protected String email;
    
    @XmlElement(name="owner-relationship")                               
    protected String ownerRelationship;
    
    public String getEmailIndicator() {
        return emailIndicator;
    }

    public void setEmailIndicator(String emailIndicator) {
        this.emailIndicator = emailIndicator;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBestTimeToCallCode() {
        return bestTimeToCallCode;
    }

    public void setBestTimeToCallCode(String bestTimeToCallCode) {
        this.bestTimeToCallCode = bestTimeToCallCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwnerRelationship() {
        return ownerRelationship;
    }

    public void setOwnerRelationship(String ownerRelationship) {
        this.ownerRelationship = ownerRelationship;
    }

}
