package com.vw.formhandler.webspring.model.customercare;

import java.io.Serializable;

public class CustomerCase implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private int casenum;
    private String vin;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String emailIndicator;
    private String email;
    private String comments;
    private String contactFlag;

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContactFlag() {
        return contactFlag;
    }

    public void setContactFlag(String contactFlag) {
        this.contactFlag = contactFlag;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getCasenum() {
        return casenum;
    }

    public void setCasenum(int casenum) {
        this.casenum = casenum;
    }
}
