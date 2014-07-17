package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurveyItems", propOrder = {
    "surveyItemsList"
})
public class SurveyItemsVO implements Serializable
{
	@XmlElement(name="SurveyItem")
	public List<SurveyItemVO> surveyItemsList;

	public SurveyItemsVO() {}
	
	public SurveyItemsVO(List<SurveyItemVO> surveyItemsList) {
		this.surveyItemsList = surveyItemsList;
	}

	public List<SurveyItemVO> getSurveyItemsList() {
		return surveyItemsList;
	}

	public void setSurveyItemsList(List<SurveyItemVO> surveyItemsList) {
		this.surveyItemsList = surveyItemsList;
	}
}
