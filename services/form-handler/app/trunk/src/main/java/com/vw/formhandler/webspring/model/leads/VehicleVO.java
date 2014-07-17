package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehicle", propOrder = {
    "modelCode",
    "modelName",
    "transmission",
    "trim",
    "interiorColor",
    "exteriorColor",
    "year", 
    "brand",
    "engine",
    "msrp",
    "msrpBase",
    "msrpTotal",
    "saleType",
    "bodyStyle",
    "comments",
    "vin",
    "mileage",
    "options"
})
public class VehicleVO implements Serializable
{
	@XmlElement
	protected String modelCode;

	@XmlElement
	protected String modelName;

	@XmlElement
	protected String transmission;
	
	@XmlElement
	protected String trim;

	@XmlElement
	protected String interiorColor;
	
	@XmlElement
	protected String exteriorColor;
	
	protected Integer year;

	@XmlElement(name="make")
	protected String brand;

	@XmlElement
	protected String engine;
	
	@XmlElement
	protected String msrp;
	
	@XmlElement
	protected String msrpBase;
	
	@XmlElement
	protected String msrpTotal;
	
	@XmlElement
	public String saleType;

	@XmlElement
	public String bodyStyle;
	
	@XmlElement
	protected String comments;

	@XmlElement(name="vin")
        protected String vin;

	@XmlElement(name="vehicle-mileage")
        protected String mileage;

	@XmlElement
        protected String options;
        
	
	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public String getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}

	public String getExteriorColor() {
		return exteriorColor;
	}

	public void setExteriorColor(String exteriorColor) {
		this.exteriorColor = exteriorColor;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getMsrp() {
		return msrp;
	}

	public void setMsrp(String msrp) {
		this.msrp = msrp;
	}

	public String getMsrpBase() {
		return msrpBase;
	}

	public void setMsrpBase(String msrpBase) {
		this.msrpBase = msrpBase;
	}

	public String getMsrpTotal() {
		return msrpTotal;
	}

	public void setMsrpTotal(String msrpTotal) {
		this.msrpTotal = msrpTotal;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
	
	
	
}
