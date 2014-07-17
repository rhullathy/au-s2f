package com.vw.formhandler.webspring.common;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.View;
import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.webspring.mvc.response.AbstractError;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.ValidationError;

public class XmlBasedMarshalledErrorView implements View
{
	private static final Logger log=Logger.getLogger(ExceptionHandlerResolver.class);
	private Errors errors;

	public XmlBasedMarshalledErrorView(Errors errors)
	{
		this.errors=errors;
	}

	public String getContentType() {
		return FormHandlerConstants.defaultContentType;
	}
	
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String errorAbsoluteURL = request.getParameter(FormHandlerConstants.errorPageURL);
		List<AbstractError> listOfErrorsForParam_errorPageURLError = ControllerUtils.validateUrlString(FormHandlerConstants.errorPageURL, errorAbsoluteURL).getErrors();
		
		if (!CommonUtils.isNullOrBlank(errorAbsoluteURL) && CommonUtils.isNullOrEmpty(listOfErrorsForParam_errorPageURLError))
		{
			String emailStr = request.getParameter(FormHandlerConstants.email);
			String finalUrlStr = ControllerUtils.getErrorURLWithParamsQuery(errors, errorAbsoluteURL, emailStr);
			log.debug("errorPageURL given; redirect URL:"+finalUrlStr);

			response.sendRedirect(finalUrlStr);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			errors.getErrors().addAll(listOfErrorsForParam_errorPageURLError);
	        ByteArrayOutputStream bos = new ByteArrayOutputStream(FormHandlerConstants.byteBufferSize);
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
			marshaller.setClassesToBeBound(Errors.class, ValidationError.class);
	        marshaller.marshal(errors, new StreamResult(bos));
	        response.setContentType(getContentType());
	        response.setContentLength(bos.size());
	        FileCopyUtils.copy(bos.toByteArray(), response.getOutputStream());
		}
	}
}
