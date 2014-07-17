package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehicles", propOrder = {
    "vehicleList"
})
public class VehiclesVO implements Serializable
{
	@XmlElement(name="vehicle")
	public List<VehicleVO> vehicleList;

	public VehiclesVO() {}
	
	public VehiclesVO(List<VehicleVO> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public List<VehicleVO> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<VehicleVO> vehicleList) {
		this.vehicleList = vehicleList;
	}
}
