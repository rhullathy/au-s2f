package com.vw.formhandler.webspring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.view.RedirectView;
import com.vw.formhandler.webspring.common.InvalidRequestException;
import com.vw.formhandler.webspring.mvc.response.AbstractError;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.FormHandlerError;
import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;

public class ExceptionHandlerResolver extends DefaultHandlerExceptionResolver {
	
	private static final Logger log=Logger.getLogger(ExceptionHandlerResolver.class);
	
	private MessageSource messageSource;
	
	public ExceptionHandlerResolver()
	{
		super();
		this.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {

		if(ex instanceof InvalidRequestException)
		{
			return handleInvalidRequestException((InvalidRequestException) ex, response);
		}
		if (ex instanceof NoSuchRequestHandlingMethodException) {
			return handleInvalidRequestException((NoSuchRequestHandlingMethodException) ex, response);
		}
		else if (ex instanceof HttpRequestMethodNotSupportedException) {
			return handleInvalidRequestException(ex, response);
		}
		else if (ex instanceof HttpMediaTypeNotSupportedException) {
			return handleInvalidRequestException(ex, response);
		}
		else if (ex instanceof HttpMediaTypeNotAcceptableException) {
			return handleInvalidRequestException(ex, response);
		}
		else if (ex instanceof MissingServletRequestParameterException) {
			return handleInvalidRequestException(ex, response);
		}
		else if (ex instanceof ConversionNotSupportedException) {
			return handleInvalidRequestException(ex, response);
		}
		else if (ex instanceof TypeMismatchException) {
			return handleInvalidRequestException((TypeMismatchException) ex, response);
		}
		else if (ex instanceof HttpMessageNotReadableException) {
			return handleInvalidRequestException(ex, response);
		}
		else if (ex instanceof org.codehaus.jackson.map.JsonMappingException)
		{
			return handleInvalidRequestException(ex, response);
		}
		else if (ex instanceof JAXBException)
		{
			return handleInvalidRequestException(ex, response);
		}
		else
		{
			return handleException(ex, response);
		}
	}

	private ModelAndView handleInvalidRequestException(
			Exception ex, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		log.error(ex, ex);
		Errors error=new Errors();
		FormHandlerError o=new FormHandlerError(100);
		o.setMessage(ex.getMessage());
		error.getErrors().add(o);
		return new ModelAndView(FormHandlerConstants.ERROR, FormHandlerConstants.ERRORS, error);
	}
	private ModelAndView handleInvalidRequestException(InvalidRequestException ex, HttpServletResponse response) {
		Errors error=ex.getErrors();
		try
		{
			for(AbstractError err:error.getErrors())
				err.setMessage(messageSource.getMessage(new StringBuilder(FormHandlerConstants.ERROR).append(".").append(err.getCode()).toString(), err.getMessageArgs(), null));
		}
		catch(NoSuchMessageException nsme)
		{
			ModelAndView tempMView = handleException(nsme, response);
			Object tempObj = tempMView.getModelMap().get(FormHandlerConstants.ERRORS);
			if (tempObj instanceof Errors)
				error.getErrors().addAll(((Errors)tempObj).getErrors());
		}

		if (!CommonUtils.isNullOrBlank(ex.getErrorAbsoluteURL()))
		{
			String finalUrlStr = ControllerUtils.getErrorURLWithParamsQuery(error, ((InvalidRequestException)ex).getErrorAbsoluteURL(), ((InvalidRequestException)ex).getEmailStr());
			log.debug("errorPageURL given; redirect URL:"+finalUrlStr);

			return new ModelAndView(new RedirectView(finalUrlStr, false));
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new ModelAndView(FormHandlerConstants.ERROR, FormHandlerConstants.ERRORS, error);
		}

	}
	private ModelAndView handleException(Exception ex, HttpServletResponse response) {
		log.error(ex, ex);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		Errors error=new Errors();
		FormHandlerError o=new FormHandlerError(150);
		o.setMessage(ex.getClass().getName()+"\t"+ex.getMessage());
		error.getErrors().add(o);
		return new ModelAndView(FormHandlerConstants.ERROR, FormHandlerConstants.ERRORS, error);
	}
	public MessageSource getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
