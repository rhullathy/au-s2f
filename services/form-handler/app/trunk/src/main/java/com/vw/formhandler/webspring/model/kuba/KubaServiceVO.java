package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name="handraiser")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "handraiser", propOrder = {
	"campaignID",
	"brand",
    "contact",
    "desiredCar",
    "currentCar",
    "survey",
    "links",
    "BPID"
})
public class KubaServiceVO implements Serializable
{
	@XmlElement
	protected String campaignID;
	
	@XmlElement
        protected String brand;
	
	@XmlElement
	protected KubaContactVO contact;
	
	@XmlElement
	protected List<DesiredCarVO> desiredCar;
	
	@XmlElement
	protected CurrentCarVO currentCar;
	
	@XmlElement
	protected SurveyVO survey;
	
	@XmlElement
	protected LinksVO links;
	
	@XmlElement
	protected String BPID;
	
	
	public String getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}

	public KubaContactVO getContact() {
		return contact;
	}

	public void setContact(KubaContactVO contact) {
		this.contact = contact;
	}

	public CurrentCarVO getCurrentCar() {
		return currentCar;
	}

	public void setCurrentCar(CurrentCarVO currentCar) {
		this.currentCar = currentCar;
	}

	public List<DesiredCarVO> getDesiredCar() {
		return desiredCar;
	}

	public void setDesiredCar(List<DesiredCarVO> desiredCar) {
		this.desiredCar = desiredCar;
	}

	public SurveyVO getSurvey() {
		return survey;
	}

	public void setSurvey(SurveyVO survey) {
		this.survey = survey;
	}

	public LinksVO getLinks() {
		return links;
	}

	public void setLinks(LinksVO links) {
		this.links = links;
	}

	public String getBPID() {
		return BPID;
	}

	public void setBPID(String bPID) {
		BPID = bPID;
	}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
