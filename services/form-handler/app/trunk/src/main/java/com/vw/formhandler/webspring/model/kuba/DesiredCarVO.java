package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "desiredCar", propOrder = {
    "modelDerivatID",
    "modelNameID",
    "engineVersionID",
    "enginePower",
    "transmissionTypeID",
    "plannedPurchaseDate",
    "typeOfPurchaseID",
    "plannedUsageID",
    "financeMethodID"
})
public class DesiredCarVO implements Serializable {
        @XmlElement(name="derivat", required=true)
        protected String modelDerivatID;

	@XmlElement(name="model", required=true)
	protected String modelNameID;

	@XmlElement(name="engineVersion")
	protected String engineVersionID;
	
	protected String enginePower;
	
	@XmlElement(name="transmissionType")
	protected String transmissionTypeID;

	@XmlElement
	protected Date plannedPurchaseDate;

	@XmlElement(name="typeOfPurchase")
	protected String typeOfPurchaseID;

	@XmlElement(name="plannedUsage")
	protected String plannedUsageID;
	
	@XmlElement(name="plannedFinancingType")
	protected String financeMethodID;


	public String getModelNameID() {
		return modelNameID;
	}

	public void setModelNameID(String modelNameID) {
		this.modelNameID = modelNameID;
	}

	public String getEngineVersionID() {
		return engineVersionID;
	}

	public void setEngineVersionID(String engineVersionID) {
		this.engineVersionID = engineVersionID;
	}

	public String getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(String enginePower) {
		this.enginePower = enginePower;
	}

	public String getTransmissionTypeID() {
		return transmissionTypeID;
	}

	public void setTransmissionTypeID(String transmissionTypeID) {
		this.transmissionTypeID = transmissionTypeID;
	}

	public Date getPlannedPurchaseDate() {
		return plannedPurchaseDate;
	}

	public void setPlannedPurchaseDate(Date plannedPurchaseDate) {
		this.plannedPurchaseDate = plannedPurchaseDate;
	}

	public String getTypeOfPurchaseID() {
		return typeOfPurchaseID;
	}

	public void setTypeOfPurchaseID(String typeOfPurchaseID) {
		this.typeOfPurchaseID = typeOfPurchaseID;
	}

	public String getPlannedUsageID() {
		return plannedUsageID;
	}

	public void setPlannedUsageID(String plannedUsageID) {
		this.plannedUsageID = plannedUsageID;
	}

	public String getFinanceMethodID() {
		return financeMethodID;
	}

	public void setFinanceMethodID(String financeMethodID) {
		this.financeMethodID = financeMethodID;
	}

    public String getModelDerivatID() {
        return modelDerivatID;
    }

    public void setModelDerivatID(String modelDerivatID) {
        this.modelDerivatID = modelDerivatID;
    }
}
