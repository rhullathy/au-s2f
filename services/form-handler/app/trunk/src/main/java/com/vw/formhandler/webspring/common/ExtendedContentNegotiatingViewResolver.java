package com.vw.formhandler.webspring.common;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.ValidationError;

public class ExtendedContentNegotiatingViewResolver extends
		ContentNegotiatingViewResolver {

	private MediaType defaultContentType;
	public void setDefaultContentType(MediaType defaultContentType) {
		this.defaultContentType = defaultContentType;
		super.setDefaultContentType(this.defaultContentType);
	}
	
	private MessageSource messageSource;
	
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception
	{
		View tempView = super.resolveViewName(viewName, locale);
		//if media content type was not understood, then we must use InvalidRequestExceptionView class object, which throws InvalidRequestException with appropriate error messages
		if (tempView==null || tempView.getContentType()==null)
		{
			Errors errors=new Errors();
			ValidationError tempValidationError = new ValidationError(8);
			tempValidationError.setMessage(messageSource.getMessage(new StringBuilder(FormHandlerConstants.ERROR).append(".").append(8).toString(), null, null));
			errors.getErrors().add(tempValidationError);
			tempView = new XmlBasedMarshalledErrorView(errors);
		}
		
		return tempView;
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
