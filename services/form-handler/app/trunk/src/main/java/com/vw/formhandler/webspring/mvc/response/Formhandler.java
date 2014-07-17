package com.vw.formhandler.webspring.mvc.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.vw.formhandler.webspring.mvc.response.kuba.KubaServiceResponseErrorVO;
import com.vw.formhandler.webspring.mvc.response.leads.LeadsServiceResponseErrorVO;

@SuppressWarnings("serial")
@XmlRootElement(name="formhandler")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formhandler", propOrder={
	    "leadId", 
	    "kubaId",
            "vin",
            "caseNumber",
	    "errors"
})
/**
 * Formhandler response class
 */
public class Formhandler implements FormHandlerResponseInterface
{
	@XmlElement
	private String leadId;

	@XmlElement
	private String kubaId;
	
	@XmlElement
        private String vin;
	
	@XmlElement
        private String caseNumber;
	
	@XmlElementWrapper(name="errors")
	@XmlElementRefs({ 
        @XmlElementRef(name="validationError", type=ValidationError.class),
        @XmlElementRef(name="formHandlerError", type=FormHandlerError.class),
        @XmlElementRef(name="kubaServiceResponseError", type=KubaServiceResponseErrorVO.class),
        @XmlElementRef(name="leadsServiceResponseError", type=LeadsServiceResponseErrorVO.class)
	}) 
    private List<AbstractError> errors;
	
	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getKubaId() {
		return kubaId;
	}

	public void setKubaId(String kubaId) {
		this.kubaId = kubaId;
	}

    public List<AbstractError> getErrors() {
        if (errors == null) {
            errors = new ArrayList<AbstractError>();
        }
        return this.errors;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }
        
}
