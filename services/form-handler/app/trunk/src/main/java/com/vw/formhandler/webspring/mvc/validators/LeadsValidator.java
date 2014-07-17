package com.vw.formhandler.webspring.mvc.validators;

import java.util.Map;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.webspring.mvc.FormHandlerParams;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.brands;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.contactTimeValues;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaTransmissionTypes;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.saleTypes;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.ValidationError;

public class LeadsValidator extends CommonValidator
{
	private static final String MESSAGE_266_LESSTHAN255CHARS = "tag value should be less than or equal to 255 characters";
	private static final String MESSAGE_256_LESSTHAN50CHARS = "tag key should be less than or equal to 50 characters";
	private static final String VALUE = "value";
	private static final String KEY = "key";
	private static final String TAG_PREFIX = "tag-";

	@Override
	protected Errors validateRequiredFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = super.validateRequiredFields(inputParams);

		//requestType for AU brands can have any value but is required
//		if (brands.AU.toString().equals(inputParams.getBrand()))
//		{
			if (CommonUtils.isNullOrBlank(inputParams.getRequestType()))
				returnErrors.getErrors().add(new ValidationError(261));
//		}
//		else //requestType for all other brands is also required but must be one of prescribed requestTypes enum values
//			if (!CommonUtils.enumContains(requestTypes.values(), inputParams.getRequestType()))
//				returnErrors.getErrors().add(new ValidationError(250, FormHandlerParams.enumStringsAsCommaSeparatedSentence(requestTypes.values())));

		//sourceType for AU brands can have any value but is required
//		if (brands.AU.toString().equals(inputParams.getBrand()))
//		{
			if (CommonUtils.isNullOrBlank(inputParams.getSourceType()))
				returnErrors.getErrors().add(new ValidationError(262));
//		}
//		else //sourceType for all other brands is also required but must be one of prescribed sourceTypes enum values
//			if (!CommonUtils.enumContains(sourceTypes.values(), inputParams.getSourceType()))
//				returnErrors.getErrors().add(new ValidationError(251, FormHandlerParams.enumStringsAsCommaSeparatedSentence(sourceTypes.values())));

//		if (CommonUtils.isNullOrBlank(inputParams.getSubType()))
//			returnErrors.getErrors().add(new ValidationError(252));
//		else
			if (!CommonUtils.isNullOrBlank(inputParams.getSubType()) && inputParams.getSubType().length() > 8000)
				returnErrors.getErrors().add(new ValidationError(253));
		
		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
			for(Map<String,Object> vehicle:inputParams.getVehicles())
			{
//				if (CommonUtils.isNullOrBlank((String)vehicle.get(FormHandlerConstants.MODELCODE)))
//					returnErrors.getErrors().add(new ValidationError(254));
//				else 
					if (!CommonUtils.isNullOrBlank((String)vehicle.get(FormHandlerConstants.MODELCODE)) &&
							((String)vehicle.get(FormHandlerConstants.MODELCODE)).length()>6)
						returnErrors.getErrors().add(new ValidationError(255));
			}
		}
//		if (CommonUtils.isNullOrBlank(inputParams.getDealerId()))
//			returnErrors.getErrors().add(new ValidationError(256));
//		else
			if (!CommonUtils.isNullOrBlank(inputParams.getDealerId()) && inputParams.getDealerId().length()>6)
				returnErrors.getErrors().add(new ValidationError(257));

		return returnErrors;
	}

	public Errors validateLeadsOnlyRequiredFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = super.validateRequiredFields(inputParams);

		if (CommonUtils.isNullOrBlank(inputParams.getBrand()) || !CommonUtils.enumContains(brands.values(), inputParams.getBrand()))
			returnErrors.getErrors().add(new ValidationError(263, FormHandlerParams.enumStringsAsCommaSeparatedSentence(brands.values())));
		
		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
			for(Map<String,Object> vehicle:inputParams.getVehicles())
			{
				if (vehicle.get(FormHandlerConstants.MODELNAME)!=null && ((String)vehicle.get(FormHandlerConstants.MODELNAME)).length()>64)
					returnErrors.getErrors().add(new ValidationError(249));
			}
		}
		else
		{
				returnErrors.getErrors().add(new ValidationError(249));
		}
		return returnErrors;
	}

	@Override
	protected Errors validateOptionalFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = super.validateOptionalFields(inputParams);

		if (!CommonUtils.isNullOrEmpty(inputParams.getPreferredContactMethod()))
		{
			StringBuilder preferredContactMethodsStrB = new StringBuilder();
			for (int i=0; i<inputParams.getPreferredContactMethod().length; i++)
			{
				preferredContactMethodsStrB.append(inputParams.getPreferredContactMethod()[i]);
				if (i+1!=inputParams.getPreferredContactMethod().length)
					preferredContactMethodsStrB.append(FormHandlerConstants.COMMA);
			}
			if (preferredContactMethodsStrB.toString().length()>50)
				returnErrors.getErrors().add(new ValidationError(276));
		}

		//leads (or process both) specific optional fields
		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
					
			for(Map<String,Object> vehicle:inputParams.getVehicles())
			{
				if (vehicle.get(FormHandlerConstants.TRIM)!=null && ((String)vehicle.get(FormHandlerConstants.TRIM)).length()>500)
					returnErrors.getErrors().add(new ValidationError(270));
		
				if (vehicle.get(FormHandlerConstants.INTERIORCOLOR)!=null && ((String)vehicle.get(FormHandlerConstants.INTERIORCOLOR)).length()>500)
					returnErrors.getErrors().add(new ValidationError(271));
		
				if (vehicle.get(FormHandlerConstants.EXTERIORCOLOR)!=null && ((String)vehicle.get(FormHandlerConstants.EXTERIORCOLOR)).length()>500)
					returnErrors.getErrors().add(new ValidationError(272));
		
				if (vehicle.get(FormHandlerConstants.YEAR)!=null && ((String)vehicle.get(FormHandlerConstants.YEAR)).length()!=4)
					returnErrors.getErrors().add(new ValidationError(274));
		
				if (vehicle.get(FormHandlerConstants.ENGINE)!=null && ((String)vehicle.get(FormHandlerConstants.ENGINE)).length()>500)
					returnErrors.getErrors().add(new ValidationError(273));
		
				if (vehicle.get(FormHandlerConstants.MSRP)!=null && ((String)vehicle.get(FormHandlerConstants.MSRP)).length()>10)
					returnErrors.getErrors().add(new ValidationError(275));
		
				if (vehicle.get(FormHandlerConstants.MSRPBASE)!=null && ((String)vehicle.get(FormHandlerConstants.MSRPBASE)).length()>10)
					returnErrors.getErrors().add(new ValidationError(278));
		
				if (vehicle.get(FormHandlerConstants.MSRPTOTAL)!=null && ((String)vehicle.get(FormHandlerConstants.MSRPTOTAL)).length()>22)
					returnErrors.getErrors().add(new ValidationError(279));		
			}
		}

		if (inputParams.getComments()!=null && inputParams.getComments().length()>2000)
			returnErrors.getErrors().add(new ValidationError(296));
		//tradeInVehicleMileage required if tradeInVehicleYear!=null
		if (inputParams.getTradeInVehicleYear()!=null)
		{
			if(inputParams.getTradeInVehicleYear().length()!=4)
				returnErrors.getErrors().add(new ValidationError(277));

			if (CommonUtils.isNullOrBlank(inputParams.getTradeInVehicleMileage()))
				returnErrors.getErrors().add(new ValidationError(297));
		}

		if (inputParams.getTradeInVehicleMake()!=null && inputParams.getTradeInVehicleMake().length()>100)
			returnErrors.getErrors().add(new ValidationError(280));

		if (inputParams.getTradeInVehicleModel()!=null && inputParams.getTradeInVehicleModel().length()>100)
			returnErrors.getErrors().add(new ValidationError(281));

		if (inputParams.getTradeInVehicleMileage()!=null && inputParams.getTradeInVehicleMileage().length()>6)
			returnErrors.getErrors().add(new ValidationError(284));

		if (inputParams.getTradeInVehicleRemainingBalance()!=null && inputParams.getTradeInVehicleRemainingBalance().length()>10)
			returnErrors.getErrors().add(new ValidationError(282));

		if (inputParams.getTradeInVehicleCondition()!=null && inputParams.getTradeInVehicleCondition().length()>100)
			returnErrors.getErrors().add(new ValidationError(283));

		if (inputParams.getContactbydealer()!=null  && !(CommonUtils.isStringABoolean(inputParams.getContactbydealer())))
			returnErrors.getErrors().add(new ValidationError(285));

		if (inputParams.getContactDate1()!=null && !CommonUtils.isCorrectDateFormat(CommonUtils.getValidDateFormat(), inputParams.getContactDate1()))
			returnErrors.getErrors().add(new ValidationError(286));

		if (inputParams.getContactTime1()!=null && !CommonUtils.enumContains(contactTimeValues.values(), inputParams.getContactTime1()))
			returnErrors.getErrors().add(new ValidationError(287, FormHandlerParams.enumStringsAsCommaSeparatedSentence(contactTimeValues.values())));

		if (inputParams.getContactDate2()!=null && !CommonUtils.isCorrectDateFormat(CommonUtils.getValidDateFormat(), inputParams.getContactDate2()))
			returnErrors.getErrors().add(new ValidationError(288));

		if (inputParams.getContactTime2()!=null && !CommonUtils.enumContains(contactTimeValues.values(), inputParams.getContactTime2()))
			returnErrors.getErrors().add(new ValidationError(289, FormHandlerParams.enumStringsAsCommaSeparatedSentence(contactTimeValues.values())));

		if (inputParams.getRequestTestDrive()!=null  && !(CommonUtils.isStringABoolean(inputParams.getRequestTestDrive())))
			returnErrors.getErrors().add(new ValidationError(290));

		if (inputParams.getTimeToPurchase()!=null && inputParams.getTimeToPurchase().length()>50)
			returnErrors.getErrors().add(new ValidationError(291));

		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
			for(Map<String,Object> vehicle:inputParams.getVehicles())
			{
				if (vehicle.get(FormHandlerConstants.SALETYPE)!=null && !CommonUtils.enumContains(saleTypes.values(), (String) vehicle.get(FormHandlerConstants.SALETYPE)))
					returnErrors.getErrors().add(new ValidationError(292, FormHandlerParams.enumStringsAsCommaSeparatedSentence(saleTypes.values())));
		
				if (vehicle.get(FormHandlerConstants.BODYSTYLE)!=null && ((String)vehicle.get(FormHandlerConstants.BODYSTYLE)).length()>20)
					returnErrors.getErrors().add(new ValidationError(293));
			}
		}
		return returnErrors;
	}

	public Errors validateLeadsOnlyOptionalFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();

		if (inputParams.getFinanceMethod()!=null && inputParams.getFinanceMethod().length()>30)
			returnErrors.getErrors().add(new ValidationError(294));

		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
			for(Map<String,Object> vehicle:inputParams.getVehicles())
			{
				String transmission=(String) vehicle.get(FormHandlerConstants.TRANSMISSION);
				if (transmission!=null && transmission.length()>500)
					returnErrors.getErrors().add(new ValidationError(295));
			}
		}
		//validating tags
		if(!CommonUtils.isNullOrEmpty(inputParams.getTags())) {
			for(Map<String,Object> tag : inputParams.getTags())
			{
				String key =(String) tag.get(KEY);
				String value = (String) tag.get(VALUE);
				if (key!=null && key.replace(TAG_PREFIX, "").length()>50){
					returnErrors.getErrors().add(new ValidationError(265, MESSAGE_256_LESSTHAN50CHARS));
				}
				if (value!=null && value.length()>255){
					returnErrors.getErrors().add(new ValidationError(266, MESSAGE_266_LESSTHAN255CHARS));
				}
			}
			
		}
		
		return returnErrors;
	}
}
