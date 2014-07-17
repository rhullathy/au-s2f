package com.vw.formhandler.webspring.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.webspring.model.customercare.ContactVO;
import com.vw.formhandler.webspring.model.customercare.CustomerCareServiceVO;
import com.vw.formhandler.webspring.model.customercare.ExperienceVO;
import com.vw.formhandler.webspring.model.customercare.OwnerVO;
import com.vw.formhandler.webspring.model.kuba.AddressVO;
import com.vw.formhandler.webspring.model.kuba.CurrentCarVO;
import com.vw.formhandler.webspring.model.kuba.DesiredCarVO;
import com.vw.formhandler.webspring.model.kuba.KubaContactVO;
import com.vw.formhandler.webspring.model.kuba.KubaServiceVO;
import com.vw.formhandler.webspring.model.kuba.KubaTelephoneVO;
import com.vw.formhandler.webspring.model.kuba.LinksVO;
import com.vw.formhandler.webspring.model.kuba.SurveyItemVO;
import com.vw.formhandler.webspring.model.kuba.SurveyItemsVO;
import com.vw.formhandler.webspring.model.kuba.SurveyVO;
import com.vw.formhandler.webspring.model.leads.DealerVO;
import com.vw.formhandler.webspring.model.leads.LeadsContactVO;
import com.vw.formhandler.webspring.model.leads.LeadsServiceVO;
import com.vw.formhandler.webspring.model.leads.TagVO;
import com.vw.formhandler.webspring.model.leads.TagsVO;
import com.vw.formhandler.webspring.model.leads.TradeInVO;
import com.vw.formhandler.webspring.model.leads.VehicleVO;
import com.vw.formhandler.webspring.model.leads.VehiclesVO;
import com.vw.formhandler.webspring.mvc.ExtendedParams;
import com.vw.formhandler.webspring.mvc.FormHandlerParams;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.americanStates;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.brands;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.formHandlerTypes;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaEngineVersions;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaModels;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaAUModels;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaPlannedUsageAKACurrentCarUsage;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaTitles;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaTransmissionTypes;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.kubaTypeOfPurchase;

public class VOBuilderUtil
{
	private static final String TAG_PREFIX = "tag-";

	//public static LeadsServiceVO buildLeadsServiceVOFromFormHandlerParamsObj(FormHandlerParams input) throws ParseException
	public static LeadsServiceVO buildLeadsServiceVOFromFormHandlerParamsObj(ExtendedParams input) throws ParseException
	{
		LeadsServiceVO output = new LeadsServiceVO();
		output.setRequestType(input.getRequestType());
		output.setSourceType(input.getSourceType());
		output.setSubType(input.getSubType());
		
		LeadsContactVO tempContactVO = new LeadsContactVO();
		tempContactVO.setCity(input.getCity());
		tempContactVO.setContactByDealerYNCharStr(String.valueOf(Boolean.parseBoolean(input.getContactbydealer())?FormHandlerConstants.Y_CHAR:FormHandlerConstants.N_CHAR));
		tempContactVO.setContactDate1((input.getContactDate1()!=null)?(CommonUtils.getValidDateFormat().parse(input.getContactDate1())):(null));
		tempContactVO.setContactDate2((input.getContactDate2()!=null)?(CommonUtils.getValidDateFormat().parse(input.getContactDate2())):(null));		
		tempContactVO.setContactTime1(input.getContactTime1());
		tempContactVO.setContactTime2(input.getContactTime2());
		tempContactVO.setCountryId(FormHandlerConstants.defaultLeadsCountryId);
		tempContactVO.setEmail(input.getEmail());
		
		//1) to support FormHandler all interfaces that support Leads Submission:

		if(input.getTags() != null && !input.getTags().isEmpty()){
			TagsVO tags = new TagsVO();
			List<Map<String, Object>> inputTags = input.getTags();
			List<TagVO> tagList = new ArrayList<TagVO>();
			for(Map tagEntry: inputTags){
				TagVO tag = new TagVO();
				if( ((String) (tagEntry.get("key"))).contains(TAG_PREFIX) ){
					tag.setKey(((String) (tagEntry.get("key"))).substring(4));
					}else{
						tag.setKey((String) (tagEntry.get("key")));
					}
				tag.setValue((String) tagEntry.get("value"));
				tagList.add(tag);
				tags.setTagList(tagList);
			}
			tags.setTagList(tagList);
			output.setTags(tags);
			
		}

		
		
		
		//set financeMethod to internal map of kuba planningFinanceType if form handler type is leads-crm both, otherwise set financeMethod as is
		if (input.getFormHandlerType().equals(formHandlerTypes.both.toString()))
		{
			if (!CommonUtils.isNullOrBlank(input.getFinanceMethod()))
				tempContactVO.setFinanceMethod(getCorrectFinanceMethodValueForLeadsInput(input.getFinanceMethod()));
		}
		else
			tempContactVO.setFinanceMethod(input.getFinanceMethod());
		
		tempContactVO.setFirstName(input.getFirstName());
		tempContactVO.setLanguageId(FormHandlerConstants.defaultLeadsLanguageId);
		tempContactVO.setLastName(input.getLastName());
		tempContactVO.setPhone(input.getPhone());
		
		tempContactVO.setReferrer(input.getReferrer()); 
		tempContactVO.setVisits(input.getVisits()); 
		tempContactVO.setSessionZip(input.getSessionZip()); 
		tempContactVO.setLoggedInBefore(input.getLoggedInBefore()); 
		tempContactVO.setAccountId(input.getAccountId()); 
		tempContactVO.setSessionId(input.getSessionId()); 
		tempContactVO.setDevice(input.getDevice()); 
		tempContactVO.setUserAgent(input.getUserAgent()); 
		tempContactVO.setSearchZip(input.getSearchZip()); 
		tempContactVO.setSearchRadius(input.getSearchRadius()); 
		tempContactVO.setListingsViewed(input.getListingsViewed()); 
		tempContactVO.setFinanceTerm(input.getFinanceTerm()); 
		tempContactVO.setDownPayment(input.getDownPayment()); 
		tempContactVO.setExpectedApr(input.getExpectedApr()); 
		tempContactVO.setCampaignId(input.getCampaignID()); 
		tempContactVO.setFinanceAmount(input.getFinanceAmount()); 
		
		tempContactVO.setFilterSelections(input.getFilterSelections());

		if (!CommonUtils.isNullOrEmpty(input.getPreferredContactMethod()))
		{
			StringBuilder preferredContactMethodsStrB = new StringBuilder();
			for (int i=0; i<input.getPreferredContactMethod().length; i++)
			{
				preferredContactMethodsStrB.append(input.getPreferredContactMethod()[i]);
				if (i+1!=input.getPreferredContactMethod().length)
					preferredContactMethodsStrB.append(FormHandlerConstants.COMMA);
			}
			tempContactVO.setPreferredContactMethodStr(preferredContactMethodsStrB.toString());
		}
		else
			tempContactVO.setPreferredContactMethodStr(FormHandlerConstants.defaultLeadsPreferredContactMethodStr);

		tempContactVO.setRequestTestDrive(Boolean.parseBoolean(input.getRequestTestDrive()));
		tempContactVO.setState((input.getState()!=null)?(americanStates.valueOf(input.getState()).getLongForm()):(null));
		tempContactVO.setStreet1(input.getStreet1());
		tempContactVO.setTimeToPurchase(input.getTimeToPurchase());
		tempContactVO.setZip(input.getZip());
		output.setContact(tempContactVO);
		
		DealerVO tempDealer = new DealerVO();
		tempDealer.setDealerId(input.getDealerId());
		output.setDealer(tempDealer);
		
		TradeInVO tempTradeInVO = new TradeInVO();
		if (!CommonUtils.isNullOrBlank(input.getTradeInVehicleYear()))
		{
			tempTradeInVO.setTradeInVehicleYear(Integer.parseInt(input.getTradeInVehicleYear()));
			tempTradeInVO.setTradeInVehicleCondition(input.getTradeInVehicleCondition());
			tempTradeInVO.setTradeInVehicleMake(input.getTradeInVehicleMake());
			tempTradeInVO.setTradeInVehicleMileage((input.getTradeInVehicleMileage()!=null)?(Integer.parseInt(input.getTradeInVehicleMileage())):(null));
			tempTradeInVO.setTradeInVehicleModel(input.getTradeInVehicleModel());
			tempTradeInVO.setTradeInVehicleRemainingBalance((input.getTradeInVehicleRemainingBalance()!=null)?(new BigDecimal(input.getTradeInVehicleRemainingBalance())):(null));
			tempTradeInVO.setTradeInValue((input.getTradeInValue()!=null)?(new BigDecimal(input.getTradeInValue())):(null));
			output.setTradeIn(tempTradeInVO);
		}
		
		if(!CommonUtils.isNullOrEmpty(input.getVehicles()))
		{
			output.setVehicles(new VehiclesVO());
			output.getVehicles().setVehicleList(new ArrayList<VehicleVO>());
			boolean commentsSet=false;
			for(Map<String, Object> vehicle:input.getVehicles())
			{
				VehicleVO tempVehicleVO = new VehicleVO();
				tempVehicleVO.setBrand(brands.valueOf(input.getBrand()).getBrandLongForm());
				if(!commentsSet)
				{
					tempVehicleVO.setComments(input.getComments());
					commentsSet=true;
				}
				tempVehicleVO.setBodyStyle((String) vehicle.get(FormHandlerConstants.BODYSTYLE));
				tempVehicleVO.setSaleType((String) vehicle.get(FormHandlerConstants.SALETYPE));
				tempVehicleVO.setEngine((String) vehicle.get(FormHandlerConstants.ENGINE));
				tempVehicleVO.setExteriorColor((String) vehicle.get(FormHandlerConstants.EXTERIORCOLOR));
				tempVehicleVO.setInteriorColor((String) vehicle.get(FormHandlerConstants.INTERIORCOLOR));
				tempVehicleVO.setModelCode((String) vehicle.get(FormHandlerConstants.MODELCODE));
				tempVehicleVO.setModelName((String) vehicle.get(FormHandlerConstants.MODELNAME));
				tempVehicleVO.setMsrp((String) vehicle.get(FormHandlerConstants.MSRP));
				tempVehicleVO.setMsrpBase((String) vehicle.get(FormHandlerConstants.MSRPBASE));
				tempVehicleVO.setMsrpTotal((String) vehicle.get(FormHandlerConstants.MSRPTOTAL));
				tempVehicleVO.setTransmission((String) vehicle.get(FormHandlerConstants.TRANSMISSION));
				tempVehicleVO.setTrim((String) vehicle.get(FormHandlerConstants.TRIM));
				tempVehicleVO.setVin((String) vehicle.get(FormHandlerConstants.VIN));
				tempVehicleVO.setOptions((String) vehicle.get(FormHandlerConstants.OPTIONS));                
				tempVehicleVO.setYear(((String) vehicle.get(FormHandlerConstants.YEAR)!=null)?(Integer.parseInt((String) vehicle.get(FormHandlerConstants.YEAR))):(null));
				
				output.getVehicles().getVehicleList().add(tempVehicleVO);
			}
		}
		return output;
	}
	
	public static KubaServiceVO buildKubaServiceVOFromFormHandlerParamsObj(FormHandlerParams input) throws ParseException
	{
		KubaServiceVO output = new KubaServiceVO();
		output.setCampaignID(input.getCampaignID());
		output.setBPID(input.getBPID());
		output.setBrand(input.getBrand());
		
		KubaContactVO tempContactVO = new KubaContactVO();
		tempContactVO.setFirstName(input.getFirstName());
		tempContactVO.setLastName(input.getLastName());
		tempContactVO.setNickName(input.getNickName());
		tempContactVO.setEmail(input.getEmail());
		tempContactVO.setLanguage(input.getLanguage());
		tempContactVO.setTitleID((input.getTitle()!=null)?(kubaTitles.valueOf(input.getTitle()).getId()):(null));

		KubaTelephoneVO phoneVO, mobileVO;
		List<String> preferredContacts = null;
		if (!CommonUtils.isNullOrEmpty(input.getPreferredContactMethod()))
			preferredContacts = Arrays.asList(input.getPreferredContactMethod());
		if (input.getPhone()!=null)
		{
			phoneVO = new KubaTelephoneVO();
			phoneVO.setTelephoneStr(input.getPhone());
			phoneVO.setPreferredFlag(preferredContacts!=null && preferredContacts.contains(FormHandlerConstants.phone));
			tempContactVO.setPhone(phoneVO);
		}
		if (input.getMobile()!=null)
		{
			mobileVO = new KubaTelephoneVO();
			mobileVO.setTelephoneStr(input.getMobile());
			mobileVO.setPreferredFlag(preferredContacts!=null && preferredContacts.contains(FormHandlerConstants.mobile));
			tempContactVO.setMobile(mobileVO);
		}
		
		AddressVO addressVO = new AddressVO();
		addressVO.setStreet1(input.getStreet1());
		addressVO.setStreet2(input.getStreet2());
		addressVO.setCity(input.getCity());
		addressVO.setState(input.getState());
		addressVO.setZip(input.getZip());
		addressVO.setCountry(input.getCountry());
		tempContactVO.setAddress(addressVO);
		output.setContact(tempContactVO);
		
		CurrentCarVO currentCarVO = new CurrentCarVO();
		if (!CommonUtils.isNullOrBlank(input.getCurrentCarBrand()))
		{
			currentCarVO.setCurrentCarBrand(input.getCurrentCarBrand());
			currentCarVO.setCurrentCarEndOfFinancingDate((input.getCurrentCarEndOfFinancingDate()!=null)?(CommonUtils.getValidDateFormat().parse(input.getCurrentCarEndOfFinancingDate())):(null));
			currentCarVO.setCurrentCarFinancingTypeID((input.getCurrentCarFinancingType()!=null)?(((kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType) 
						CommonUtils.getEnumConstant(kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType.values(), input.getCurrentCarFinancingType())).getId()):(null));
			currentCarVO.setCurrentCarMileage((input.getCurrentCarMileage()!=null)?(Integer.parseInt(input.getCurrentCarMileage())):(null));
			currentCarVO.setCurrentCarModel(input.getCurrentCarModel());
			currentCarVO.setCurrentCarModelYear((input.getCurrentCarModelYear()!=null)?(Integer.parseInt(input.getCurrentCarModelYear())):(null));
			currentCarVO.setCurrentCarPurchaseDate((input.getCurrentCarPurchaseDate()!=null)?(CommonUtils.getValidDateFormat().parse(input.getCurrentCarPurchaseDate())):(null));
			currentCarVO.setCurrentCarRegistrationDate((input.getCurrentCarRegistrationDate()!=null)?(CommonUtils.getValidDateFormat().parse(input.getCurrentCarRegistrationDate())):(null));
			currentCarVO.setCurrentCarUsageID((input.getCurrentCarUsage()!=null)?(((kubaPlannedUsageAKACurrentCarUsage)
						CommonUtils.getEnumConstant(kubaPlannedUsageAKACurrentCarUsage.values(), input.getCurrentCarUsage())).getId()):(null));

			output.setCurrentCar(currentCarVO);
		}

		if(!CommonUtils.isNullOrEmpty(input.getVehicles()))
		{
			output.setDesiredCar(new ArrayList<DesiredCarVO>());
			for(Map<String,Object> vehicle:input.getVehicles())
			{
				DesiredCarVO desiredCarVO = new DesiredCarVO();
				desiredCarVO.setEnginePower((String) vehicle.get(FormHandlerConstants.ENGINEPOWER));
				desiredCarVO.setEngineVersionID(vehicle.get(FormHandlerConstants.ENGINEVERSION)!=null?(((kubaEngineVersions)
								CommonUtils.getEnumConstant(kubaEngineVersions.values(), (String) vehicle.get(FormHandlerConstants.ENGINEVERSION))).getId()):(null));
				desiredCarVO.setFinanceMethodID((input.getFinanceMethod()!=null)?(((kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType)
								CommonUtils.getEnumConstant(kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType.values(), input.getFinanceMethod())).getId()):(null));
				
				String modelID = null;
				String modelName=(String) vehicle.get(FormHandlerConstants.MODELNAME);
				String transmission=(String) vehicle.get(FormHandlerConstants.TRANSMISSION);
				String kubaModelId=(String) vehicle.get(FormHandlerConstants.KUBAMODELID);
				if(FormHandlerConstants.AU.equalsIgnoreCase(input.getBrand())){
					if(!CommonUtils.isNullOrBlank(kubaModelId))
					{
						modelID=kubaModelId;
					}
					else
					{
						kubaAUModels models = (kubaAUModels) CommonUtils.getEnumConstant(kubaAUModels.values(), modelName);
						if (models != null)
							modelID = models.getId();
					}
		                    
				}else{
					if(!CommonUtils.isNullOrBlank(kubaModelId))
					{
						modelID=kubaModelId;
					}
					else
					{
		        		kubaModels models = (kubaModels)CommonUtils.getEnumConstant(kubaModels.values(), modelName);
		        		if(models != null)
		        		    modelID = models.getId();
					}
				}
				
				if(modelID == null)
				    modelID = modelName;
				
				desiredCarVO.setModelNameID(modelID);
				
				desiredCarVO.setModelDerivatID((String) vehicle.get(FormHandlerConstants.MODELDERIVAT));
				
				desiredCarVO.setPlannedPurchaseDate((input.getPlannedPurchaseDate()!=null)?(CommonUtils.getValidDateFormat().parse(input.getPlannedPurchaseDate())):(null));
				desiredCarVO.setPlannedUsageID((input.getPlannedUsage()!=null)?(((kubaPlannedUsageAKACurrentCarUsage)
								CommonUtils.getEnumConstant(kubaPlannedUsageAKACurrentCarUsage.values(), input.getPlannedUsage())).getId()):(null));
				desiredCarVO.setTransmissionTypeID((transmission!=null)?(((kubaTransmissionTypes)
								CommonUtils.getEnumConstant(kubaTransmissionTypes.values(), transmission)).getId()):(null));
				desiredCarVO.setTypeOfPurchaseID((input.getTypeOfPurchase()!=null)?(((kubaTypeOfPurchase)
								CommonUtils.getEnumConstant(kubaTypeOfPurchase.values(), input.getTypeOfPurchase())).getId()):(null));
				output.getDesiredCar().add(desiredCarVO);
			}
		}
		if (!CommonUtils.isNullOrEmpty(input.getLinks()))
		{
			LinksVO links = new LinksVO();
			links.setLinkId1((input.getLinks().length>0)?(input.getLinks()[0]):(null));
			links.setLinkId2((input.getLinks().length>1)?(input.getLinks()[1]):(null));
			links.setLinkId3((input.getLinks().length>2)?(input.getLinks()[2]):(null));
			output.setLinks(links);
		}

		SurveyVO tempSurveyVO = new SurveyVO();
		tempSurveyVO.setSurveyID(input.getSurveyId());
		if (!CommonUtils.isNullOrEmpty(input.getSurveyQs()))
		{
			SurveyItemVO tempSurveyItemVO =null;
			ArrayList<SurveyItemVO> surveyItemsList = new ArrayList<SurveyItemVO>(input.getSurveyQs().size());
			SurveyItemsVO tempSurveyItemsVO = new SurveyItemsVO();
			for (Integer surveyQParamIndex : input.getSurveyQs().keySet())
			{
				tempSurveyItemVO = new SurveyItemVO();
				tempSurveyItemVO.setQuestion(input.getSurveyQs().get(surveyQParamIndex));
				tempSurveyItemVO.setAnswer(input.getSurveyAs().get(surveyQParamIndex));
				surveyItemsList.add(tempSurveyItemVO);
			}
			tempSurveyItemsVO.setSurveyItemsList(surveyItemsList);
			tempSurveyVO.setSurveyItems(tempSurveyItemsVO);
		}
		if (!tempSurveyVO.isSurveyVOEmpty())
			output.setSurvey(tempSurveyVO);
		
		return output;
	}
	
	private static String getCorrectFinanceMethodValueForLeadsInput(String inputFinanceMethod)
	{
		String returnFinanceMethod = inputFinanceMethod;
		
		switch ((kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType)CommonUtils.getEnumConstant(kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType.values(), inputFinanceMethod))
		{
			case Purchase:
				returnFinanceMethod = FormHandlerConstants.defaultFinanceMethodValueForPurchase;
				break;
				
			case Leasing:
				returnFinanceMethod = FormHandlerConstants.defaultFinanceMethodValueForLeasing;
				break;
				
			case Financing:
				returnFinanceMethod = FormHandlerConstants.defaultFinanceMethodValueForFinancing;
				break;
				
			case Undecided: 
			case Answer_Denied:
				returnFinanceMethod = null;
				break;
		}
		return returnFinanceMethod;
	}

    public static CustomerCareServiceVO buildCustomerCareServiceVOFromFormHandlerParamsObj(ExtendedParams inputParams) {
        CustomerCareServiceVO customerCareServiceVO = new CustomerCareServiceVO();
        
        customerCareServiceVO.setAddByUser(inputParams.getAddByUser());
        customerCareServiceVO.setAddedDateTime(inputParams.getAddedDateTime());
        customerCareServiceVO.setCaseNumber(inputParams.getCaseNumber());
        customerCareServiceVO.setCustId(inputParams.getCustId());
        customerCareServiceVO.setRecordStatusCode(inputParams.getRecordStatusCode());
        customerCareServiceVO.setTemplateName(inputParams.getTemplateName());
        customerCareServiceVO.setUpdatedByUser(inputParams.getUpdatedByUser());
        customerCareServiceVO.setUpdatedDateTime(inputParams.getUpdatedDateTime());
        customerCareServiceVO.setUpdId(inputParams.getUpdId());
        customerCareServiceVO.setVwActTxt(inputParams.getVwActTxt());
        
        ContactVO contact = new ContactVO();
        contact.setBestTimeToCallCode(inputParams.getContactBestTimeToCallCode());
        contact.setEmail(inputParams.getContactEmail());
        contact.setEmailIndicator(inputParams.getContactEmailIndicator());
        contact.setFirstName(inputParams.getContactFirstName());
        contact.setLastName(inputParams.getContactLastName());
        contact.setPhone(inputParams.getContactPhone());
        contact.setZipCode(inputParams.getContactZipCode());
        customerCareServiceVO.setContact(contact);
        
        com.vw.formhandler.webspring.model.customercare.DealerVO dealer = new com.vw.formhandler.webspring.model.customercare.DealerVO();
        dealer.setContactName(inputParams.getDealerContactName());
        dealer.setDealerId(inputParams.getDealerId());
        customerCareServiceVO.setDealer(dealer);
        
        ExperienceVO experience = new ExperienceVO();
        experience.setComments(inputParams.getComments());
        experience.setDealershipFlag(inputParams.getDealershipFlag());
        experience.setReasonCode(inputParams.getReasonCode());
        customerCareServiceVO.setExperience(experience);
        
        OwnerVO owner = new OwnerVO();
        owner.setAddressLine1(inputParams.getStreet1());
        owner.setAddressLine2(inputParams.getStreet2());
        owner.setBestTimeToCallCode(inputParams.getBestTimeToCallCode());
        owner.setCity(inputParams.getCity());
        owner.setCountryCode(inputParams.getCountry());
        owner.setEmail(inputParams.getEmail());
        owner.setEmailIndicator(inputParams.getEmailIndicator());
        owner.setFirstName(inputParams.getFirstName());
        owner.setIsOwner(inputParams.getIsOwner());
        owner.setLastName(inputParams.getLastName());
        owner.setPhone(inputParams.getPhone());
        owner.setState(inputParams.getState());
        owner.setZipCode(inputParams.getZip());
        customerCareServiceVO.setOwner(owner);
        
        VehicleVO vehicle = new VehicleVO();
        vehicle.setVin(inputParams.getVin());
        vehicle.setMileage(inputParams.getMileage());
        customerCareServiceVO.setVehicle(vehicle);
        
        
        return customerCareServiceVO;
    }

    public static String buildWegaRequestFromFormHandlerParamsObj(FormHandlerParams inputParams) throws Exception {
        // TODO Construct Soap XML 
        throw new Exception("To Be Implemented");
    }
}
