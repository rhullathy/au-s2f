package com.vw.formhandler.webspring.service;

import java.text.ParseException;

import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;

import com.vw.formhandler.webspring.mvc.ExtendedParams;
@Service
public interface ProcessLeadService {
	
	public String produceXmlToSend(ExtendedParams inputParams,
			StreamResult result) throws ParseException;

}
