package com.vw.formhandler.webspring.model.kuba;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Kunal Bhatia
 * 
 * <class name="com.vw.persistence.handraiser.HandraiserRequest">
 * 	<map-to xml="handraiser" />
 * 		<field name="requestId">
 * 			<bind-xml name="request-id" node="element" />
 * 		</field>
 * </class>
 */
@SuppressWarnings("serial")
@XmlRootElement(name="handraiser")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "handraiserResponse")
public class KubaServiceResponseVO implements Serializable
{
	@XmlElement(name="request-id")
	private Long requestId;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
}
