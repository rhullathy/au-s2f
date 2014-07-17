package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contact", propOrder = {
	"titleID",
    "firstName",
    "lastName",
    "nickName",
    "language",
    "email",
    "phone",
    "mobile",
    "address"
})
public class KubaContactVO implements Serializable
{
	@XmlElement(name="title")
	protected String titleID;

	@XmlElement(required=true)
	protected String firstName;

	@XmlElement(required=true)
	protected String lastName;

	@XmlElement
        protected String nickName;
	
	@XmlElement
	protected String language;

	@XmlElement
	protected String email;
	
	@XmlElement(name="phone")
	protected KubaTelephoneVO phone;
	
	@XmlElement(name="mobile")
	protected KubaTelephoneVO mobile;
	
	@XmlElement
	protected AddressVO address;

	
	public String getTitleID() {
		return titleID;
	}

	public void setTitleID(String titleID) {
		this.titleID = titleID;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public KubaTelephoneVO getPhone() {
		return phone;
	}

	public void setPhone(KubaTelephoneVO phone) {
		this.phone = phone;
	}

	public KubaTelephoneVO getMobile() {
		return mobile;
	}

	public void setMobile(KubaTelephoneVO mobile) {
		this.mobile = mobile;
	}

	public AddressVO getAddress() {
		return address;
	}

	public void setAddress(AddressVO address) {
		this.address = address;
	}

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
	
	
}
