package com.vw.formhandler.webspring.model.leads;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Kunal Bhatia
 * 
 * <class name="com.vw.persistence.lead.Lead">
 * 	<map-to xml="lead" />
 * 		<field name="leadId">
 * 			<bind-xml name="leadId" node="element" />
 * 		</field>
 * </class>
 */
@SuppressWarnings("serial")
@XmlRootElement(name="lead")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "leadResponse")
public class LeadsServiceResponseVO implements Serializable
{
	@XmlElement
	private Integer leadId;

	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}
}
