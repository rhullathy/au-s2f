package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "links", propOrder = {
    "linkId1",
    "linkId2",
    "linkId3"
})
public class LinksVO implements Serializable
{
	@XmlElement
	protected String linkId1;

	@XmlElement
	protected String linkId2;
	
	@XmlElement
	protected String linkId3;

	
	public String getLinkId1() {
		return linkId1;
	}

	public void setLinkId1(String linkId1) {
		this.linkId1 = linkId1;
	}

	public String getLinkId2() {
		return linkId2;
	}

	public void setLinkId2(String linkId2) {
		this.linkId2 = linkId2;
	}

	public String getLinkId3() {
		return linkId3;
	}

	public void setLinkId3(String linkId3) {
		this.linkId3 = linkId3;
	}
}
