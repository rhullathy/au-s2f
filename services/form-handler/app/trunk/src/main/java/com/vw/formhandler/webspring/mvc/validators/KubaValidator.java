package com.vw.formhandler.webspring.mvc.validators;

import java.util.Map;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.webspring.mvc.FormHandlerParams;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.brands;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.currentCarBrands;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaCountries;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaEngineVersions;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaLanguageTypes;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaModels;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaAUModels;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaPlannedUsageAKACurrentCarUsage;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaTitles;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaTransmissionTypes;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaTypeOfPurchase;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.ValidationError;

public class KubaValidator extends CommonValidator
{
	@Override
	protected Errors validateRequiredFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = super.validateRequiredFields(inputParams);
		returnErrors.getErrors().addAll(validateKubaOrProcessBothRequiredFields(inputParams).getErrors());
		return returnErrors;
	}

	public Errors validateKubaOrProcessBothRequiredFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();

		if (!( brands.VW.toString().equals(inputParams.getBrand())|| brands.AU.toString().equals(inputParams.getBrand())))
			returnErrors.getErrors().add(new ValidationError(205));
		
		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
			for(Map<String, Object> vehicle:inputParams.getVehicles())
			{
				String modelName=(String) vehicle.get(FormHandlerConstants.MODELNAME);
//				if (brands.VW.toString().equals(inputParams.getBrand()) && modelName!=null && !CommonUtils.enumContains(kubaModels.values(), modelName))
//					returnErrors.getErrors().add(new ValidationError(206, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaModels.values())));
//			
//			    if (brands.AU.toString().equals(inputParams.getBrand()) && modelName!=null && !CommonUtils.enumContains(kubaAUModels.values(), modelName))
//			        returnErrors.getErrors().add(new ValidationError(206, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaAUModels.values())));
			}
		}

		if (CommonUtils.isNullOrBlank(inputParams.getCampaignID()))
			returnErrors.getErrors().add(new ValidationError(200));
		else 
			if (inputParams.getCampaignID().length()>24)
				returnErrors.getErrors().add(new ValidationError(201));
		 
		return returnErrors;
	}
	
	@Override
	protected Errors validateConditionallyRequiredFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();
		
		//Enter either: email, phone, mobile,
		//or 'complete address' (i.e., must enter city, state AND street1)
		if (CommonUtils.isNullOrBlank(inputParams.getEmail()) 
			&& CommonUtils.isNullOrBlank(inputParams.getPhone())
			&& CommonUtils.isNullOrBlank(inputParams.getMobile())
			&& (CommonUtils.isNullOrBlank(inputParams.getCity()) || CommonUtils.isNullOrBlank(inputParams.getState()) || CommonUtils.isNullOrBlank(inputParams.getStreet1())))
			returnErrors.getErrors().add(new ValidationError(203));

		return returnErrors;
	}
	
	@Override
	public Errors validateOptionalFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = super.validateOptionalFields(inputParams);

		//kuba (or process both) specific optional fields
		returnErrors.getErrors().addAll(validateKubaOrProcessBothOptionalFields(inputParams).getErrors());

		return returnErrors;
	}

	public Errors validateKubaOrProcessBothOptionalFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();

		if (inputParams.getMobile()!=null && inputParams.getMobile().length()!=10)
			returnErrors.getErrors().add(new ValidationError(204));
		
		if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
		{
			for(Map<String,Object> vehicle:inputParams.getVehicles())
			{
				String transmission=(String) vehicle.get(FormHandlerConstants.TRANSMISSION);
				if (transmission!=null && !CommonUtils.enumContains(kubaTransmissionTypes.values(), transmission))
					returnErrors.getErrors().add(new ValidationError(225, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaTransmissionTypes.values())));
				
				String enginePower=(String) vehicle.get(FormHandlerConstants.ENGINEPOWER);
				if (enginePower!=null && enginePower.length()>4)
					returnErrors.getErrors().add(new ValidationError(228));

				String engineVersion=(String) vehicle.get(FormHandlerConstants.ENGINEVERSION);
				if (engineVersion!=null && !CommonUtils.enumContains(kubaEngineVersions.values(), engineVersion))
					returnErrors.getErrors().add(new ValidationError(240, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaEngineVersions.values())));
			}
		}
		
		if (inputParams.getStreet2()!=null && inputParams.getStreet2().length()>40)
			returnErrors.getErrors().add(new ValidationError(226));

		if (inputParams.getLanguage()!=null && !CommonUtils.enumContains(kubaLanguageTypes.values(), inputParams.getLanguage()))
			returnErrors.getErrors().add(new ValidationError(227, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaLanguageTypes.values())));

		//currentCarModelYear & currentCarMileage required if currentCarBrand!=null
		if (inputParams.getCurrentCarBrand()!=null)
		{
			if(inputParams.getCurrentCarBrand().length()!=2 || !CommonUtils.enumContains(currentCarBrands.values(), inputParams.getCurrentCarBrand()))
				returnErrors.getErrors().add(new ValidationError(229,FormHandlerParams.enumStringsAsCommaSeparatedSentence(currentCarBrands.values())));

			if (CommonUtils.isNullOrBlank(inputParams.getCurrentCarModelYear()))
				returnErrors.getErrors().add(new ValidationError(223));
			
			if (CommonUtils.isNullOrBlank(inputParams.getCurrentCarMileage()))
				returnErrors.getErrors().add(new ValidationError(224));
		}

		if (inputParams.getCurrentCarModel()!=null && inputParams.getCurrentCarModel().length()>6)
			returnErrors.getErrors().add(new ValidationError(230));

		if (inputParams.getCurrentCarModelYear()!=null && inputParams.getCurrentCarModelYear().length()!=4)
			returnErrors.getErrors().add(new ValidationError(231));

		if (inputParams.getCurrentCarMileage()!=null && inputParams.getCurrentCarMileage().length()>7)
			returnErrors.getErrors().add(new ValidationError(232));

		if (inputParams.getCurrentCarPurchaseDate()!=null && !CommonUtils.isCorrectDateFormat(CommonUtils.getValidDateFormat(), inputParams.getCurrentCarPurchaseDate()))
			returnErrors.getErrors().add(new ValidationError(233));

		if (!CommonUtils.isNullOrEmpty(inputParams.getLinks()) && inputParams.getLinks().length>3)
			returnErrors.getErrors().add(new ValidationError(234));
	
		if (!CommonUtils.isNullOrEmpty(inputParams.getSurveyQs()) || !CommonUtils.isNullOrEmpty(inputParams.getSurveyAs()))
		{
			//if either surveyQ's & surveyA's are given, first check if surveyId is provided as well; surveyId is a must in this case
			if (CommonUtils.isNullOrBlank(inputParams.getSurveyId()))
				returnErrors.getErrors().add(new ValidationError(222));
		}
		
		if (!CommonUtils.isNullOrEmpty(inputParams.getSurveyQs()) && !CommonUtils.isNullOrEmpty(inputParams.getSurveyAs()))
		{
			//if both surveyQ's & surveyA's are given, first check if surveyId is provided as well; surveyId is a must in this case
			if (CommonUtils.isNullOrBlank(inputParams.getSurveyId()))
				returnErrors.getErrors().add(new ValidationError(222));
			
			//report errors for questions in surveyQs map but not in surveyAs map
			for (Integer surveyQParamIndex : inputParams.getSurveyQs().keySet())
				if (!inputParams.getSurveyAs().containsKey(surveyQParamIndex))
					returnErrors.getErrors().add(new ValidationError(236, surveyQParamIndex.toString()));
			
			//now report errors for answers in surveyAs map but not in surveyQs map
			for (Integer surveyAParamIndex : inputParams.getSurveyAs().keySet())
				if (!inputParams.getSurveyQs().containsKey(surveyAParamIndex))
					returnErrors.getErrors().add(new ValidationError(236, surveyAParamIndex.toString()));
		}
		else
			if ( !(CommonUtils.isNullOrEmpty(inputParams.getSurveyQs()) && CommonUtils.isNullOrEmpty(inputParams.getSurveyAs())) )
				returnErrors.getErrors().add(new ValidationError(235));
				
		if (inputParams.getBPID()!=null && inputParams.getBPID().length()>50)
			returnErrors.getErrors().add(new ValidationError(237));
		
		if (inputParams.getTitle()!=null && !CommonUtils.enumContains(kubaTitles.values(), inputParams.getTitle()))
			returnErrors.getErrors().add(new ValidationError(238, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaTitles.values())));

		if (inputParams.getCountry()!=null && !CommonUtils.enumContains(kubaCountries.values(), inputParams.getCountry()))
			returnErrors.getErrors().add(new ValidationError(239, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaCountries.values())));

		if (inputParams.getPlannedUsage()!=null && !CommonUtils.enumContains(kubaPlannedUsageAKACurrentCarUsage.values(), inputParams.getPlannedUsage()))
			returnErrors.getErrors().add(new ValidationError(241, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaPlannedUsageAKACurrentCarUsage.values())));

		if (inputParams.getFinanceMethod()!=null && !CommonUtils.enumContains(kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType.values(), inputParams.getFinanceMethod()))
			returnErrors.getErrors().add(new ValidationError(242, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType.values())));

		if (inputParams.getTypeOfPurchase()!=null && !CommonUtils.enumContains(kubaTypeOfPurchase.values(), inputParams.getTypeOfPurchase()))
			returnErrors.getErrors().add(new ValidationError(248, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaTypeOfPurchase.values())));

		if (inputParams.getCurrentCarUsage()!=null && !CommonUtils.enumContains(kubaPlannedUsageAKACurrentCarUsage.values(), inputParams.getCurrentCarUsage()))
			returnErrors.getErrors().add(new ValidationError(243, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaPlannedUsageAKACurrentCarUsage.values())));

		if (inputParams.getCurrentCarFinancingType()!=null && !CommonUtils.enumContains(kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType.values(), inputParams.getCurrentCarFinancingType()))
			returnErrors.getErrors().add(new ValidationError(244, FormHandlerParams.enumStringsAsCommaSeparatedSentence(kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType.values())));

		if (inputParams.getCurrentCarRegistrationDate()!=null && !CommonUtils.isCorrectDateFormat(CommonUtils.getValidDateFormat(), inputParams.getCurrentCarRegistrationDate()))
			returnErrors.getErrors().add(new ValidationError(245));

		if (inputParams.getCurrentCarEndOfFinancingDate()!=null && !CommonUtils.isCorrectDateFormat(CommonUtils.getValidDateFormat(), inputParams.getCurrentCarEndOfFinancingDate()))
			returnErrors.getErrors().add(new ValidationError(246));

		if (inputParams.getPlannedPurchaseDate()!=null && !CommonUtils.isCorrectDateFormat(CommonUtils.getValidDateFormat(), inputParams.getPlannedPurchaseDate()))
			returnErrors.getErrors().add(new ValidationError(247));

		return returnErrors;
	}

	public Errors validateKubaOnlyOptionalFields(FormHandlerParams inputParams)
	{
		Errors returnErrors = new Errors();
		return returnErrors;
	}
}
