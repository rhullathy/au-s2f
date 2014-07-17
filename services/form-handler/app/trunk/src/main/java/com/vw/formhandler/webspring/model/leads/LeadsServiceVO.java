package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name="lead")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lead", propOrder = {
    "requestType",
    "subType",
    "sourceType",
    "vehicles",
    "tradeIn",
    "contact",
    "dealer",
    "tags"
})
public class LeadsServiceVO implements Serializable
{
	@XmlElement(required = true)
	protected String requestType;
	
	@XmlElement
	protected String subType;

	@XmlElement
	protected String sourceType;
	
	@XmlElement
	protected VehiclesVO vehicles;
	
	@XmlElement
	protected TradeInVO tradeIn;
	
	@XmlElement
	protected LeadsContactVO contact;
	
	@XmlElement
	protected DealerVO dealer;

	// 1) to support FormHandler all interfaces that support Leads Submission:
	@XmlElement(required=false)
	protected TagsVO tags;

	public TagsVO getTags() {
		return tags;
	}

	public void setTags(TagsVO tags) {
		this.tags = tags;
	}


	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public VehiclesVO getVehicles() {
		return vehicles;
	}

	public void setVehicles(VehiclesVO vehicles) {
		this.vehicles = vehicles;
	}

	public TradeInVO getTradeIn() {
		return tradeIn;
	}

	public void setTradeIn(TradeInVO tradeIn) {
		this.tradeIn = tradeIn;
	}

	public LeadsContactVO getContact() {
		return contact;
	}

	public void setContact(LeadsContactVO contact) {
		this.contact = contact;
	}

	public DealerVO getDealer() {
		return dealer;
	}

	public void setDealer(DealerVO dealer) {
		this.dealer = dealer;
	}
}
