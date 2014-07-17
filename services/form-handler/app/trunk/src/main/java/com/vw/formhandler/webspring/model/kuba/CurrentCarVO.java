package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "currentCar", propOrder = {
    "currentCarBrand",
    "currentCarModel",
    "currentCarModelYear",
    "currentCarMileage",
    "currentCarPurchaseDate",
    "currentCarRegistrationDate",
    "currentCarUsageID",
    "currentCarFinancingTypeID",
    "currentCarEndOfFinancingDate"
})
public class CurrentCarVO implements Serializable
{
	@XmlElement(name="brand")
	protected String currentCarBrand;

	@XmlElement(name="model")
	protected String currentCarModel;

	@XmlElement(name="modelYear")
	protected Integer currentCarModelYear;

	@XmlElement(name="mileage")
	protected Integer currentCarMileage;

	@XmlElement(name="purchaseDate")
	protected Date currentCarPurchaseDate;

	@XmlElement(name="registrationDate")
	protected Date currentCarRegistrationDate;

	@XmlElement(name="usage")
	protected String currentCarUsageID;

	@XmlElement(name="financingType")
	protected String currentCarFinancingTypeID;

	@XmlElement(name="endOfFinancingDate")
	protected Date currentCarEndOfFinancingDate;

	
	public String getCurrentCarBrand() {
		return currentCarBrand;
	}

	public void setCurrentCarBrand(String currentCarBrand) {
		this.currentCarBrand = currentCarBrand;
	}

	public String getCurrentCarModel() {
		return currentCarModel;
	}

	public void setCurrentCarModel(String currentCarModel) {
		this.currentCarModel = currentCarModel;
	}

	public Integer getCurrentCarModelYear() {
		return currentCarModelYear;
	}

	public void setCurrentCarModelYear(Integer currentCarModelYear) {
		this.currentCarModelYear = currentCarModelYear;
	}

	public Integer getCurrentCarMileage() {
		return currentCarMileage;
	}

	public void setCurrentCarMileage(Integer currentCarMileage) {
		this.currentCarMileage = currentCarMileage;
	}

	public Date getCurrentCarPurchaseDate() {
		return currentCarPurchaseDate;
	}

	public void setCurrentCarPurchaseDate(Date currentCarPurchaseDate) {
		this.currentCarPurchaseDate = currentCarPurchaseDate;
	}

	public Date getCurrentCarRegistrationDate() {
		return currentCarRegistrationDate;
	}

	public void setCurrentCarRegistrationDate(Date currentCarRegistrationDate) {
		this.currentCarRegistrationDate = currentCarRegistrationDate;
	}

	public String getCurrentCarUsageID() {
		return currentCarUsageID;
	}

	public void setCurrentCarUsageID(String currentCarUsageID) {
		this.currentCarUsageID = currentCarUsageID;
	}

	public String getCurrentCarFinancingTypeID() {
		return currentCarFinancingTypeID;
	}

	public void setCurrentCarFinancingTypeID(String currentCarFinancingTypeID) {
		this.currentCarFinancingTypeID = currentCarFinancingTypeID;
	}

	public Date getCurrentCarEndOfFinancingDate() {
		return currentCarEndOfFinancingDate;
	}

	public void setCurrentCarEndOfFinancingDate(Date currentCarEndOfFinancingDate) {
		this.currentCarEndOfFinancingDate = currentCarEndOfFinancingDate;
	}
}
