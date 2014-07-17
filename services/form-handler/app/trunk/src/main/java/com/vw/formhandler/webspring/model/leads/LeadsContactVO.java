package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "leadsContact", propOrder = {
    "firstName",
    "lastName",
    "phone",
    "street1",
    "city",
    "zip",
    "state",
    "email",
    "preferredContactMethodStr",
    "countryId",
    "languageId",
    "contactByDealerYNCharStr",
    "contactDate1",
    "contactTime1",
    "contactDate2",
    "contactTime2",
    "requestTestDrive",
    "timeToPurchase",
    "financeMethod",
    "referrer", 
    "visits", 
    "sessionZip", 
    "loggedInBefore", 
    "accountId", 
    "sessionId", 
    "device", 
    "userAgent", 
    "searchZip", 
    "searchRadius", 
    "listingsViewed", 
    "financeTerm", 
    "downPayment", 
    "expectedApr", 
    "financeAmount",
    "campaignId", 
    "filterSelections"
})
public class LeadsContactVO implements Serializable
{
	@XmlElement(required=true)
	protected String firstName;

	@XmlElement(required=true)
	protected String lastName;

	@XmlElement
	protected String phone;

	@XmlElement(name="address1")
	protected String street1;
	
	@XmlElement
	protected String city;

	@XmlElement(name="postalCode", required=true)
	protected String zip;

	@XmlElement(name="stateProvince")
	protected String state;

	@XmlElement(name="email1")
	protected String email;

	@XmlElement(name="preferredContactMethod", required=true)
	protected String preferredContactMethodStr; // comma separated string with last item having 'or'

	@XmlElement
	protected String countryId;

	@XmlElement
	protected String languageId;

	@XmlElement(name="contactbydealer")
	protected String contactByDealerYNCharStr;

	@XmlElement
	protected Date contactDate1;

	@XmlElement
	protected String contactTime1;

	@XmlElement
	protected Date contactDate2;

	@XmlElement
	protected String contactTime2;

	@XmlElement
	protected Boolean requestTestDrive;

	@XmlElement
	protected String timeToPurchase;
	
	@XmlElement
	protected String financeMethod;
	
	@XmlElement 
	protected String referrer;

	@XmlElement 
	protected String visits;

	@XmlElement 
	protected String sessionZip;

	@XmlElement 
	protected String loggedInBefore;

	@XmlElement 
	protected String accountId;

	@XmlElement 
	protected String sessionId;

	@XmlElement 
	protected String device;

	@XmlElement 
	protected String userAgent;

	@XmlElement 
	protected String searchZip;

	@XmlElement 
	protected String searchRadius;

	@XmlElement 
	protected String listingsViewed;

	@XmlElement 
	protected String financeTerm;

	@XmlElement 
	protected String downPayment;

	@XmlElement 
        protected String financeAmount;

	@XmlElement 
	protected String expectedApr;

	@XmlElement 
	protected String campaignId;

	@XmlElement 
	protected String filterSelections;
	

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPreferredContactMethodStr() {
		return preferredContactMethodStr;
	}

	public void setPreferredContactMethodStr(String preferredContactMethodStr) {
		this.preferredContactMethodStr = preferredContactMethodStr;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getContactByDealerYNCharStr() {
		return contactByDealerYNCharStr;
	}

	public void setContactByDealerYNCharStr(String contactByDealerYNCharStr) {
		this.contactByDealerYNCharStr = contactByDealerYNCharStr;
	}

	public Date getContactDate1() {
		return contactDate1;
	}

	public void setContactDate1(Date contactDate1) {
		this.contactDate1 = contactDate1;
	}

	public String getContactTime1() {
		return contactTime1;
	}

	public void setContactTime1(String contactTime1) {
		this.contactTime1 = contactTime1;
	}

	public Date getContactDate2() {
		return contactDate2;
	}

	public void setContactDate2(Date contactDate2) {
		this.contactDate2 = contactDate2;
	}

	public String getContactTime2() {
		return contactTime2;
	}

	public void setContactTime2(String contactTime2) {
		this.contactTime2 = contactTime2;
	}

	public Boolean getRequestTestDrive() {
		return requestTestDrive;
	}

	public void setRequestTestDrive(Boolean requestTestDrive) {
		this.requestTestDrive = requestTestDrive;
	}

	public String getTimeToPurchase() {
		return timeToPurchase;
	}

	public void setTimeToPurchase(String timeToPurchase) {
		this.timeToPurchase = timeToPurchase;
	}

	public String getFinanceMethod() {
		return financeMethod;
	}

	public void setFinanceMethod(String financeMethod) {
		this.financeMethod = financeMethod;
	}

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getSessionZip() {
        return sessionZip;
    }

    public void setSessionZip(String sessionZip) {
        this.sessionZip = sessionZip;
    }

    public String getLoggedInBefore() {
        return loggedInBefore;
    }

    public void setLoggedInBefore(String loggedInBefore) {
        this.loggedInBefore = loggedInBefore;
    }

   

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSearchZip() {
        return searchZip;
    }

    public void setSearchZip(String searchZip) {
        this.searchZip = searchZip;
    }

    

    public String getListingsViewed() {
        return listingsViewed;
    }

    public void setListingsViewed(String listingsViewed) {
        this.listingsViewed = listingsViewed;
    }

    public String getFinanceTerm() {
        return financeTerm;
    }

    public void setFinanceTerm(String financeTerm) {
        this.financeTerm = financeTerm;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getExpectedApr() {
        return expectedApr;
    }

    public void setExpectedApr(String expectedApr) {
        this.expectedApr = expectedApr;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getFilterSelections() {
        return filterSelections;
    }

    public void setFilterSelections(String filterSelections) {
        this.filterSelections = filterSelections;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(String financeAmount) {
        this.financeAmount = financeAmount;
    }

    public String getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(String searchRadius) {
        this.searchRadius = searchRadius;
    }	
	
}
