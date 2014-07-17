package com.vw.formhandler.webspring.mvc.validators;

import java.util.Map;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.webspring.mvc.FormHandlerParams;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.americanStates;
import com.vw.formhandler.webspring.mvc.response.ValidationError;
import com.vw.formhandler.webspring.mvc.response.Errors;

public abstract class CommonValidator
{
	public Errors validateAll(FormHandlerParams inputParams)
	{
		Errors returnErrors = validateRequiredFields(inputParams);
		returnErrors.getErrors().addAll(validateConditionallyRequiredFields(inputParams).getErrors());
		returnErrors.getErrors().addAll(validateOptionalFields(inputParams).getErrors());
		
		return returnErrors;
	}

	protected Errors validateRequiredFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();
		
		if (CommonUtils.isNullOrBlank(inputParams.getFirstName()))
			returnErrors.getErrors().add(new ValidationError(3));
		else 
			if (inputParams.getFirstName().length()>40)
				returnErrors.getErrors().add(new ValidationError(4));

		if (CommonUtils.isNullOrBlank(inputParams.getLastName()))
			returnErrors.getErrors().add(new ValidationError(5));
		else 
			if (inputParams.getLastName().length()>40)
				returnErrors.getErrors().add(new ValidationError(6));

		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
			for(Map<String, Object> vehicle:inputParams.getVehicles())
			{
				if (CommonUtils.isNullOrBlank((String) vehicle.get(FormHandlerConstants.MODELNAME)))
					returnErrors.getErrors().add(new ValidationError(7));
			}
		}
		else
		{
			returnErrors.getErrors().add(new ValidationError(7));
		}
		
		if (CommonUtils.isNullOrBlank(inputParams.getZip()))
			returnErrors.getErrors().add(new ValidationError(14));
		else 
			if (inputParams.getZip().length()<5 || inputParams.getZip().length()>10)
				returnErrors.getErrors().add(new ValidationError(15));

		return returnErrors;
	}
	
	protected Errors validateConditionallyRequiredFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();
		
		//Enter either: email or phone
		if (CommonUtils.isNullOrBlank(inputParams.getEmail()) 
			&& CommonUtils.isNullOrBlank(inputParams.getPhone()))
			returnErrors.getErrors().add(new ValidationError(258));

		return returnErrors;
	}
	
	protected Errors validateOptionalFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();
		
		if (inputParams.getPhone()!=null && inputParams.getPhone().length()!=10)
			returnErrors.getErrors().add(new ValidationError(9));

		if (inputParams.getEmail()!=null && inputParams.getEmail().length()>100)
			returnErrors.getErrors().add(new ValidationError(10));

		if (inputParams.getState()!=null && (inputParams.getState().length()!=2 || !CommonUtils.enumContains(americanStates.values(), inputParams.getState())))
			returnErrors.getErrors().add(new ValidationError(11));
		
		if (inputParams.getCity()!=null && inputParams.getCity().length()>35)
			returnErrors.getErrors().add(new ValidationError(12));
		
		if (inputParams.getStreet1()!=null && inputParams.getStreet1().length()>60)
			returnErrors.getErrors().add(new ValidationError(13));
		
		return returnErrors;
	}
}
