package com.vw.formhandler.webspring.common;

import java.net.MalformedURLException;
import java.net.URL;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.webspring.mvc.response.AbstractError;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.ValidationError;
import com.vw.formhandler.webspring.mvc.response.AbstractError.absractErrorSubclassSimpleNames;

public class ControllerUtils
{
	public static String getErrorURLWithParamsQuery(Errors errorsObj, String errorAbsoluteURL, String emailStr)
	{
		StringBuilder finalUrl;
		finalUrl = new StringBuilder(errorAbsoluteURL);
		if (errorAbsoluteURL.contains(FormHandlerConstants.QUESTION))
			finalUrl.append(FormHandlerConstants.AMPERSAND);
		else
			finalUrl.append(FormHandlerConstants.QUESTION);

		if (!CommonUtils.isNullOrBlank(emailStr))
			finalUrl.append(FormHandlerConstants.email).append(FormHandlerConstants.EQUAL).append(emailStr).append(FormHandlerConstants.AMPERSAND);
		
		if (!CommonUtils.isNullOrEmpty(errorsObj.getErrors()))
		{
			for (AbstractError currError : errorsObj.getErrors())
				finalUrl.append(getAbstractErrorSubclassURIQueryString(currError));
		}
		return finalUrl.toString();
	}

	public static StringBuilder getAbstractErrorSubclassURIQueryString(AbstractError currError)
	{
		StringBuilder returnURIStrB = new StringBuilder();
		switch(absractErrorSubclassSimpleNames.valueOf(currError.getClass().getSimpleName()))
		{
			case FormHandlerError:
				returnURIStrB.append(FormHandlerConstants.formhandlerError).append(FormHandlerConstants.EQUAL).append(currError.getMessage()).append(FormHandlerConstants.AMPERSAND);
				break;

			case ValidationError:
				returnURIStrB.append(FormHandlerConstants.validationError).append(FormHandlerConstants.EQUAL).append(currError.getMessage()).append(FormHandlerConstants.AMPERSAND);
				break;

			case KubaServiceResponseErrorVO:
				returnURIStrB.append(FormHandlerConstants.kubaResponseError).append(FormHandlerConstants.EQUAL).append(currError.getMessage()).append(FormHandlerConstants.AMPERSAND);
				break;

			case LeadsServiceResponseErrorVO:
				returnURIStrB.append(FormHandlerConstants.leadsResponseError).append(FormHandlerConstants.EQUAL).append(currError.getMessage()).append(FormHandlerConstants.AMPERSAND);
				break;
		}
		return returnURIStrB;
	}

	public static Errors validateUrlString(String urlParamName, String urlStr)
	{
		Errors returnErrors = new Errors();
	
		try
		{
		   new URL(urlStr);
		}
		catch (MalformedURLException e)
		{
		    // the URL is not in a valid form
			returnErrors.getErrors().add(new ValidationError(2, urlParamName, urlStr));
		}
		
		return returnErrors;
	}
}
