package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tradeIn", propOrder = {
    "tradeInVehicleYear",
    "tradeInVehicleMake",
    "tradeInVehicleModel",
    "tradeInVehicleMileage",
    "tradeInVehicleRemainingBalance",
    "tradeInVehicleCondition",
    "tradeInValue"
})
public class TradeInVO implements Serializable
{
	@XmlElement(name="year")
	protected Integer tradeInVehicleYear;
	
	@XmlElement(name="make")
	protected String tradeInVehicleMake;
	
	@XmlElement(name="model")
	protected String tradeInVehicleModel;
	
	@XmlElement(name="mileage")
	protected Integer tradeInVehicleMileage;
	
	@XmlElement(name="remainingBalance")
	protected BigDecimal tradeInVehicleRemainingBalance;
	
	@XmlElement(name="condition")
	protected String tradeInVehicleCondition;
	
	@XmlElement(name="tradeInValue")
        protected BigDecimal tradeInValue;
        
	
	public Integer getTradeInVehicleYear() {
		return tradeInVehicleYear;
	}

	public void setTradeInVehicleYear(Integer tradeInVehicleYear) {
		this.tradeInVehicleYear = tradeInVehicleYear;
	}

	public String getTradeInVehicleMake() {
		return tradeInVehicleMake;
	}

	public void setTradeInVehicleMake(String tradeInVehicleMake) {
		this.tradeInVehicleMake = tradeInVehicleMake;
	}

	public String getTradeInVehicleModel() {
		return tradeInVehicleModel;
	}

	public void setTradeInVehicleModel(String tradeInVehicleModel) {
		this.tradeInVehicleModel = tradeInVehicleModel;
	}

	public Integer getTradeInVehicleMileage() {
		return tradeInVehicleMileage;
	}

	public void setTradeInVehicleMileage(Integer tradeInVehicleMileage) {
		this.tradeInVehicleMileage = tradeInVehicleMileage;
	}

	public BigDecimal getTradeInVehicleRemainingBalance() {
		return tradeInVehicleRemainingBalance;
	}

	public void setTradeInVehicleRemainingBalance(
	        BigDecimal tradeInVehicleRemainingBalance) {
		this.tradeInVehicleRemainingBalance = tradeInVehicleRemainingBalance;
	}

	public String getTradeInVehicleCondition() {
		return tradeInVehicleCondition;
	}

	public void setTradeInVehicleCondition(String tradeInVehicleCondition) {
		this.tradeInVehicleCondition = tradeInVehicleCondition;
	}

        public BigDecimal getTradeInValue() {
            return tradeInValue;
        }
    
        public void setTradeInValue(BigDecimal tradeInValue) {
            this.tradeInValue = tradeInValue;
        }
    	
	
	
}
