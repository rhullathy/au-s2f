package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurveyItem", propOrder = {
    "question",
    "answer"
})
public class SurveyItemVO implements Serializable
{
	@XmlElement(name="Question")
	protected String question;

	@XmlElement(name="Answer")
	protected String answer;

	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
