package com.vw.formhandler.webspring.model.customercare;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "owner", namespace="customercare",  propOrder = {
    "firstName",
    "lastName",
    "addressLine1",
    "addressLine2",
    "city",
    "state",
    "zipCode", 
    "phone",
    "bestTimeToCallCode",
    "emailIndicator",
    "email",
    "isOwner",
    "countryCode"
})

public class OwnerVO {

    @XmlElement(name="first-name")                               
    protected String firstName;

    @XmlElement(name="last-name")                           
    protected String lastName;

    @XmlElement(name="address-line1")                       
    protected String addressLine1;

    @XmlElement(name="address-line2")                       
    protected String addressLine2;

    @XmlElement(name="city")                                
    protected String city;

    @XmlElement(name="state")                               
    protected String state;

    @XmlElement(name="zip-code")                            
    protected String zipCode;
     
    @XmlElement(name="phone")                               
    protected String phone;

    @XmlElement(name="best-time-to-call-code")              
    protected String bestTimeToCallCode;

    @XmlElement(name="email-indicator")                     
    protected String emailIndicator;

    @XmlElement(name="email")                               
    protected String email;

    @XmlElement(name="is-owner")                            
    protected String isOwner;

    @XmlElement(name="country-code")                        
    protected String countryCode;

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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getEmailIndicator() {
        return emailIndicator;
    }

    public void setEmailIndicator(String emailIndicator) {
        this.emailIndicator = emailIndicator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    
}
