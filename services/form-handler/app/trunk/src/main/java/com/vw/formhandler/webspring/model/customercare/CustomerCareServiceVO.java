package com.vw.formhandler.webspring.model.customercare;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.vw.formhandler.webspring.model.leads.VehicleVO;


@SuppressWarnings("serial")
@XmlRootElement(name="customer-care-web-case")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customer-care-web-case", namespace="customercare", propOrder = {
    "templateName",
    "brand",
    "vehicle",
    "owner",
    "contact",
    "experience",
    "recordStatusCode",
    "updId",
    "updatedByUser",
    "updatedDateTime",
    "vwActTxt",
    "addByUser",
    "addedDateTime",
    "caseNumber",
    "custId",
    "dealer",
    "contactPref"
})
public class CustomerCareServiceVO implements Serializable
{
	@XmlElement(name="template-name")
	protected String templateName;
	
	@XmlElement(name="brand")
        protected String brand;
        	
	@XmlElement(name="record-status-code")
        protected String recordStatusCode;
	
	@XmlElement(name="upd-id")
	protected String updId;
	
	@XmlElement(name="updated-by-user")
	protected String updatedByUser;
	
	@XmlElement(name="updated-date-time")
	protected String updatedDateTime;
	
	@XmlElement(name="vw-act-txt")
	protected String vwActTxt;
	
	@XmlElement(name="add-by-user")
	protected String addByUser;
	
	@XmlElement(name="added-date-time")
	protected String addedDateTime;
	
	@XmlElement(name="case-number")
	protected String caseNumber;
	
	@XmlElement(name="cust-id")
        protected String custId;

    @XmlElement(name="contact-pref")
    protected String contactPref;
	
	@XmlElement
        protected VehicleVO vehicle;
	
	@XmlElement
        protected OwnerVO owner;
	
	@XmlElement
        protected ContactVO contact;
	
	@XmlElement
        protected ExperienceVO experience;
	
	@XmlElement
        protected DealerVO dealer;
	
	

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getRecordStatusCode() {
        return recordStatusCode;
    }

    public void setRecordStatusCode(String recordStatusCode) {
        this.recordStatusCode = recordStatusCode;
    }

    public VehicleVO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleVO vehicle) {
        this.vehicle = vehicle;
    }

    public OwnerVO getOwner() {
        return owner;
    }

    public void setOwner(OwnerVO owner) {
        this.owner = owner;
    }

    public ContactVO getContact() {
        return contact;
    }

    public void setContact(ContactVO contact) {
        this.contact = contact;
    }

    public ExperienceVO getExperience() {
        return experience;
    }

    public void setExperience(ExperienceVO experience) {
        this.experience = experience;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public String getVwActTxt() {
        return vwActTxt;
    }

    public void setVwActTxt(String vwActTxt) {
        this.vwActTxt = vwActTxt;
    }

    public String getAddByUser() {
        return addByUser;
    }

    public void setAddByUser(String addByUser) {
        this.addByUser = addByUser;
    }

    public String getAddedDateTime() {
        return addedDateTime;
    }

    public void setAddedDateTime(String addedDateTime) {
        this.addedDateTime = addedDateTime;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public DealerVO getDealer() {
        return dealer;
    }

    public void setDealer(DealerVO dealer) {
        this.dealer = dealer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getContactPref() {
        return contactPref;
    }

    public void setContactPref(String contactPref) {
        this.contactPref = contactPref;
    }
    
}
