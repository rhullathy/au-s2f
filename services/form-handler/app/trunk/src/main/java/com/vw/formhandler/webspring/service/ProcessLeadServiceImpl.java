package com.vw.formhandler.webspring.service;

import java.text.ParseException;

import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.vw.formhandler.webspring.common.VOBuilderUtil;
import com.vw.formhandler.webspring.model.leads.LeadsServiceVO;
import com.vw.formhandler.webspring.mvc.ExtendedParams;

@Component
public class ProcessLeadServiceImpl implements ProcessLeadService {
	
	@Autowired
    private Jaxb2Marshaller marshaller;

	public String produceXmlToSend(ExtendedParams inputParams,
			StreamResult result) throws ParseException {
		LeadsServiceVO leadsServiceVO = VOBuilderUtil.buildLeadsServiceVOFromFormHandlerParamsObj(inputParams);
		marshaller.marshal(leadsServiceVO, result);
		return result.getWriter().toString();
		
	}

}
