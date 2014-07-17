package com.vw.formhandler.webspring.mvc;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class ExtendedParams extends FormHandlerParams
{
	private static final Logger log=Logger.getLogger(ExtendedParams.class);

//Using following parameters from parent class	
//	public String dealerId;
//	public String firstName;
//	public String lastName;
//	public String zip;
//	public String email;
//	public String phone;
//	public String city;
//	public String state;
//	public String street1;
//	public String comments;
//	public String street2;
//	public String country;
//      public String brand;	

//CONTACT US PARAMETERS
//BEGIN	
	//Request params
        public String addByUser;
        public String addedDateTime;
        public String caseNumber;
        public String custId;
        public String recordStatusCode;
        public String templateName;
        public String updatedByUser;
        public String updatedDateTime;
        public String updId;
        public String vwActTxt;
             
      //Contact details
        public String contactBestTimeToCallCode ;       
        public String contactEmailIndicator;
        public String contactFirstName;
        public String contactLastName;
        public String contactPhone;
        public String contactEmail;
        public String contactZipCode;
        public String contactAddress1;
        public String contactAddress2;
        public String contactCity;
        public String contactState;
        
        //Dealer
        public String dealerContactName;
        //Inherited public String dealerId;
               
        //Experience
        //Inherited public String comments;
        public String dealershipFlag;
        public String reasonCode;
        
        //Owner
      //Inherited public String street1;
      //Inherited public String street2;
        public String bestTimeToCallCode;
      //Inherited public String city;
      //Inherited public String country;
      //Inherited public String email;
        public String emailIndicator;
      //Inherited public String firstName;
        public String isOwner;
      //Inherited public String lastName;
      //Inherited public String phone;
      //Inherited public String state;
      //Inherited public String zip;
        
        //Vehicle
        public String vin;
        public String mileage;
//END CONTACT US PARAMETERS

    	public String kubaModelID;
    	
		public String getKubaModelID() {
			return kubaModelID;
		}
		public void setKubaModelID(String kubaModelID) {
			this.kubaModelID = kubaModelID;
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
        public String getRecordStatusCode() {
            return recordStatusCode;
        }
        public void setRecordStatusCode(String recordStatusCode) {
            this.recordStatusCode = recordStatusCode;
        }
        public String getTemplateName() {
            return templateName;
        }
        public void setTemplateName(String templateName) {
            this.templateName = templateName;
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
        public String getUpdId() {
            return updId;
        }
        public void setUpdId(String updId) {
            this.updId = updId;
        }
        public String getVwActTxt() {
            return vwActTxt;
        }
        public void setVwActTxt(String vwActTxt) {
            this.vwActTxt = vwActTxt;
        }
        public String getContactBestTimeToCallCode() {
            return contactBestTimeToCallCode;
        }
        public void setContactBestTimeToCallCode(String contactBestTimeToCallCode) {
            this.contactBestTimeToCallCode = contactBestTimeToCallCode;
        }
        public String getContactEmailIndicator() {
            return contactEmailIndicator;
        }
        public void setContactEmailIndicator(String contactEmailIndicator) {
            this.contactEmailIndicator = contactEmailIndicator;
        }
        public String getContactFirstName() {
            return contactFirstName;
        }
        public void setContactFirstName(String contactFirstName) {
            this.contactFirstName = contactFirstName;
        }
        public String getContactLastName() {
            return contactLastName;
        }
        public void setContactLastName(String contactLastName) {
            this.contactLastName = contactLastName;
        }
        public String getContactPhone() {
            return contactPhone;
        }
        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }
        public String getContactEmail() {
            return contactEmail;
        }
        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }
        public String getContactZipCode() {
            return contactZipCode;
        }
        public void setContactZipCode(String contactZipCode) {
            this.contactZipCode = contactZipCode;
        }
        public String getDealerContactName() {
            return dealerContactName;
        }
        public void setDealerContactName(String dealerContactName) {
            this.dealerContactName = dealerContactName;
        }
        public String getDealershipFlag() {
            return dealershipFlag;
        }
        public void setDealershipFlag(String dealershipFlag) {
            this.dealershipFlag = dealershipFlag;
        }
        public String getReasonCode() {
            return reasonCode;
        }
        public void setReasonCode(String reasonCode) {
            this.reasonCode = reasonCode;
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
        public String getIsOwner() {
            return isOwner;
        }
        public void setIsOwner(String isOwner) {
            this.isOwner = isOwner;
        }
        public String getVin() {
            return vin;
        }
        public void setVin(String vin) {
            this.vin = vin;
        }
        public String getMileage() {
            return mileage;
        }
        public void setMileage(String mileage) {
            this.mileage = mileage;
        }
        public String getContactAddress1() {
			return contactAddress1;
		}
		public void setContactAddress1(String contactAddress1) {
			this.contactAddress1 = contactAddress1;
		}
		public String getContactAddress2() {
			return contactAddress2;
		}
		public void setContactAddress2(String contactAddress2) {
			this.contactAddress2 = contactAddress2;
		}
		public String getContactCity() {
			return contactCity;
		}
		public void setContactCity(String contactCity) {
			this.contactCity = contactCity;
		}
		public String getContactState() {
			return contactState;
		}
		public void setContactState(String contactState) {
			this.contactState = contactState;
		}
		@Override
		public String toString() {
			return "ExtendedParams [addByUser=" + addByUser
					+ ", addedDateTime=" + addedDateTime + ", caseNumber="
					+ caseNumber + ", custId=" + custId + ", recordStatusCode="
					+ recordStatusCode + ", templateName=" + templateName
					+ ", updatedByUser=" + updatedByUser + ", updatedDateTime="
					+ updatedDateTime + ", updId=" + updId + ", vwActTxt="
					+ vwActTxt + ", contactBestTimeToCallCode="
					+ contactBestTimeToCallCode + ", contactEmailIndicator="
					+ contactEmailIndicator + ", contactFirstName="
					+ contactFirstName + ", contactLastName=" + contactLastName
					+ ", contactPhone=" + contactPhone + ", contactEmail="
					+ contactEmail + ", contactZipCode=" + contactZipCode
					+ ", contactAddress1=" + contactAddress1
					+ ", contactAddress2=" + contactAddress2 + ", contactCity="
					+ contactCity + ", contactState=" + contactState
					+ ", dealerContactName=" + dealerContactName
					+ ", dealershipFlag=" + dealershipFlag + ", reasonCode="
					+ reasonCode + ", bestTimeToCallCode=" + bestTimeToCallCode
					+ ", emailIndicator=" + emailIndicator + ", isOwner="
					+ isOwner + ", vin=" + vin + ", mileage=" + mileage + "]";
		}
		
		
//		@Override
//        public String toString() {
//            return "CustomerCareParams [addByUser=" + addByUser + ", addedDateTime=" + addedDateTime + ", caseNumber="
//                    + caseNumber + ", custId=" + custId + ", recordStatusCode=" + recordStatusCode + ", templateName="
//                    + templateName + ", updatedByUser=" + updatedByUser + ", updatedDateTime=" + updatedDateTime
//                    + ", updId=" + updId + ", vwActTxt=" + vwActTxt + ", contactBestTimeToCallCode="
//                    + contactBestTimeToCallCode + ", contactEmailIndicator=" + contactEmailIndicator
//                    + ", contactFirstName=" + contactFirstName + ", contactLastName=" + contactLastName
//                    + ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", contactZipCode="
//                    + contactZipCode + ", dealerContactName=" + dealerContactName + ", dealershipFlag="
//                    + dealershipFlag + ", reasonCode=" + reasonCode + ", bestTimeToCallCode=" + bestTimeToCallCode
//                    + ", emailIndicator=" + emailIndicator + ", isOwner=" + isOwner + ", vin=" + vin + ", mileage="
//                    + mileage + "]";
//        }
        
}
