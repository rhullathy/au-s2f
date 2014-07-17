package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "survey", propOrder = {
    "surveyID",
    "surveyItems"
})
public class SurveyVO implements Serializable 
{
	@XmlElement(name="SurveyID")
	protected String surveyID;
	
	@XmlElement(name="SurveyItems")
	protected SurveyItemsVO surveyItems;

	public String getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(String surveyID) {
		this.surveyID = surveyID;
	}

	public SurveyItemsVO getSurveyItems() {
		return surveyItems;
	}

	public void setSurveyItems(SurveyItemsVO surveyItems) {
		this.surveyItems = surveyItems;
	}

	public boolean isSurveyVOEmpty() {
		return surveyID==null 
				&& surveyItems==null;
	}
}
