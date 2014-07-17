package com.vw.formhandler.webspring.mvc.validators;


import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.webspring.mvc.ExtendedParams;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.ValidationError;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.americanStates;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.yesOrNo;
import org.apache.commons.lang.StringUtils;

public class CustomerCareValidator extends CommonValidator
{
	public Errors validateCustomerCareFields(ExtendedParams inputParams)
	{
		Errors returnErrors = new Errors();
		
		if(!"AU".equals(inputParams.getBrand())
				&& !"VW".equals(inputParams.getBrand()))
		{
			returnErrors.getErrors().add(new ValidationError(923));
		}
		
		if (CommonUtils.isNullOrBlank(inputParams.getContactFirstName()))
			returnErrors.getErrors().add(new ValidationError(900));
		else 
			if (inputParams.getContactFirstName().length()>35)
				returnErrors.getErrors().add(new ValidationError(902));

		if (CommonUtils.isNullOrBlank(inputParams.getContactLastName()))
			returnErrors.getErrors().add(new ValidationError(901));
		else 
			if (inputParams.getContactLastName().length()>35)
				returnErrors.getErrors().add(new ValidationError(903));
	
		if (CommonUtils.isNullOrBlank(inputParams.getVin()))
		{
			if (CommonUtils.isNullOrBlank(inputParams.getContactZipCode()))
				returnErrors.getErrors().add(new ValidationError(904));
			else 
				if (inputParams.getContactZipCode().length() != 5 || !StringUtils.isNumeric(inputParams.getContactZipCode()))
					returnErrors.getErrors().add(new ValidationError(905));
			
			if (CommonUtils.isNullOrBlank(inputParams.getContactAddress1()))
				returnErrors.getErrors().add(new ValidationError(906));
			else 
				if (inputParams.getContactAddress1().length() > 98)
					returnErrors.getErrors().add(new ValidationError(907));
			
			if (CommonUtils.isNullOrBlank(inputParams.getContactCity()))
				returnErrors.getErrors().add(new ValidationError(908));
			else 
				if (inputParams.getContactCity().length() > 35)
					returnErrors.getErrors().add(new ValidationError(909));

			if (CommonUtils.isNullOrBlank(inputParams.getContactState()))
				returnErrors.getErrors().add(new ValidationError(921));
			else 		
				if (inputParams.getContactState().length() != 2 || !CommonUtils.enumContains(americanStates.values(), inputParams.getContactState()))
					returnErrors.getErrors().add(new ValidationError(914));
			
		}

		if(CommonUtils.isNullOrBlank(inputParams.getContactZipCode())
				|| CommonUtils.isNullOrBlank(inputParams.getContactAddress1())
				|| CommonUtils.isNullOrBlank(inputParams.getContactCity())
				|| CommonUtils.isNullOrBlank(inputParams.getContactState()))
		{
			if(CommonUtils.isNullOrBlank(inputParams.getVin()))
				returnErrors.getErrors().add(new ValidationError(924));
		}
		
		if (!CommonUtils.isNullOrBlank(inputParams.getContactZipCode()))
			if (inputParams.getContactZipCode().length() != 5 || !StringUtils.isNumeric(inputParams.getContactZipCode()))
				returnErrors.getErrors().add(new ValidationError(905));
		
		if (!CommonUtils.isNullOrBlank(inputParams.getContactAddress1()))
			if (inputParams.getContactAddress1().length() > 98)
				returnErrors.getErrors().add(new ValidationError(907));
		
		if (!CommonUtils.isNullOrBlank(inputParams.getContactCity()))
			if (inputParams.getContactCity().length() > 35)
				returnErrors.getErrors().add(new ValidationError(909));

		if (!CommonUtils.isNullOrBlank(inputParams.getContactState()))		
			if (inputParams.getContactState().length() != 2 || !CommonUtils.enumContains(americanStates.values(), inputParams.getContactState()))
				returnErrors.getErrors().add(new ValidationError(914));
		
		if(!"N".equals(inputParams.getContactEmailIndicator()))
		{
			if (CommonUtils.isNullOrBlank(inputParams.getContactEmail()))
				returnErrors.getErrors().add(new ValidationError(910));
		}
		
		if(!CommonUtils.isNullOrBlank(inputParams.getContactEmail()))
			if (inputParams.getContactEmail().length() > 50)
				returnErrors.getErrors().add(new ValidationError(911));
		
		if(!"Y".equals(inputParams.getContactEmailIndicator()))
		{
			if (CommonUtils.isNullOrBlank(inputParams.getContactPhone()))
				returnErrors.getErrors().add(new ValidationError(912));
		}

		if(!CommonUtils.isNullOrBlank(inputParams.getContactPhone())) 		
			if (inputParams.getContactPhone().length() != 10 || !StringUtils.isNumeric(inputParams.getContactPhone()))
				returnErrors.getErrors().add(new ValidationError(913));
		
        if (CommonUtils.isNullOrBlank(inputParams.getContactEmailIndicator())) {
            returnErrors.getErrors().add(new ValidationError(915));
        } else {
            if (!(StringUtils.equals(inputParams.getContactEmailIndicator(), "Y")
                    || StringUtils.equals(inputParams.getContactEmailIndicator(), "N") 
                    || StringUtils.equals(inputParams.getContactEmailIndicator(), "A"))) {
                returnErrors.getErrors().add(new ValidationError(916));
            }
        }
		
		if (CommonUtils.isNullOrBlank(inputParams.getComments()))
			returnErrors.getErrors().add(new ValidationError(919));
		else 
			if (inputParams.getComments().length() > 2000)
				returnErrors.getErrors().add(new ValidationError(925));
		
		if (!CommonUtils.isNullOrBlank(inputParams.getIsOwner()))
			if (inputParams.getIsOwner().length() > 1 || !CommonUtils.enumContains(yesOrNo.values(), inputParams.getIsOwner()))
				returnErrors.getErrors().add(new ValidationError(918));

		if (!CommonUtils.isNullOrBlank(inputParams.getVin()) && inputParams.getVin().length() != 17)
			returnErrors.getErrors().add(new ValidationError(920));

		if (!CommonUtils.isNullOrBlank(inputParams.getContactAddress2())) {
			if (inputParams.getContactAddress2().length() > 98)
				returnErrors.getErrors().add(new ValidationError(922));
		}
		
		return returnErrors;
	}
}
