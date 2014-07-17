package com.vw.formhandler.webspring.mvc;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vw.formhandler.common.FormHandlerConstants;

@SuppressWarnings("unused")
public class FormHandlerParams
{
	private static final Logger log=Logger.getLogger(FormHandlerParams.class);

	public static enum formHandlerTypes implements FormHandlerParamsEnumsInterface
	{leads, crm, both, customer_care}

	public static enum brands implements FormHandlerParamsEnumsInterface
	{VW("Volkswagen"), AU("Audi");
		private String brandLongForm;
		private brands(String brandLongForm)
		{
			this.brandLongForm = brandLongForm;
		}
		
		public String getBrandLongForm() {
			return brandLongForm;
		}
	}

	public static enum contactTimeValues implements FormHandlerParamsEnumsInterface
	{AM, PM}
	
	public static enum saleTypes implements FormHandlerParamsEnumsInterface
	{_new("new"), used("used"), used_certified("used certified");
		private String key;
		private saleTypes(String key)
		{
			this.key = key;
		}
		
		@Override
		public String toString() {
			return key;
		}
	}
	
	public static enum kubaModels implements FormHandlerParamsEnumsInterface
	{Volkswagen(null, "VW0000"), VW_Jetta("VW Jetta", "VW0101"), VW_Passat("VW Passat", "VW0201"), VW_GLI("VW GLI", "VW0301"),
	 VW_CC("VW CC", "VW0401"), VW_New_Beetle("VW New Beetle", "VW0501"), VW_Eos("VW Eos", "VW0601"), VW_Tiguan("VW Tiguan", "VW0701"),
	 VW_Touareg_2("VW Touareg 2", "VW0801"), VW_Routan("VW Routan", "VW0901"), VW_GTI("VW GTI", "VW1001"), VW_Rabbit("VW Rabbit", "VW1201"),
	 VW_Jetta_SportWagen("VW Jetta SportWagen", "VW1301"), Golf(null, "VW1401"), Golf_R("Golf-R", "VW1404"), 
	 Passat_Sedan("Passat Sedan", "VW0202"), Passat_Wagon("Passat Wagon", "VW0203"), 
	 New_Beetle_Convertible("New Beetle Convertible", "VW0501"),Jetta_Hybrid("Jetta Hybrid","VW0101");
		private String key;
		private String id;
		private kubaModels(String key, String id)
		{
			if (key == null)
				this.key = this.name();
			else
				this.key = key;
	        this.id = id;
	    }
		
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return key;
		}
	}
	
	public static enum kubaAUModels implements FormHandlerParamsEnumsInterface
        {
         A3("A3", "AUA301"),A3_TDI("A3 TDI", "AUA301"),A4("A4", "AUA401"),A4_Cabriolet("A4 Cabriolet", "AUA401"),S4("S4", "AUS401"),S4_Cabriolet("S4 Cabriolet", "AUS401"),
         A5("A5", "AUA501"),S5("S5", "AUS501"),A5_Cabriolet("A5 Cabriolet", "AUA501"),S5_Cabriolet("S5 Cabriolet", "AUS501"),RS_5("RS 5", "AUR501"),
         A6("A6", "AUA601"),S6("S6", "AUS601"),RS6("RS6", "AUR601"),A7("A7", "AUA701"),S7("S7", "AUS701"),A8("A8", "AUA801"),allroad("allroad", "AUAQ01"),
         TT("TT", "AUTT01"),TT_RS("TT RS", "AUTT01"),TTS("TTS", "AUSTT1"),TTS_Coupe("TTS Coupe", "AUSTT1"),TTS_Roadster("TTS Roadster", "AUSTT1"),
         Q3("Q3", "AUQ301"),Q5("Q5", "AUQ501"),Q5_Hybrid("Q5 Hybrid", "AUQ501"),Q7("Q7", "AUQ701"),Q7_TDI("Q7 TDI", "AUQ701"),R8("R8", "AUR801"),
         Full_Line("Full Line", "AUNULL");
                private String key;
                private String id;
                private kubaAUModels(String key, String id)
                {
                        if (key == null)
                                this.key = this.name();
                        else
                                this.key = key;
                this.id = id;
            }
                
                public String getId() {
                        return id;
                }

                @Override
                public String toString() {
                        return key;
                }
        }
	
	public static enum kubaTransmissionTypes implements FormHandlerParamsEnumsInterface
	{Manual(null,"0001"), Automatic(null,"0002"), Tiptronic(null,"0003"), Multitronic(null,"0004"), 
	 DSG(null,"0005"), Stronic("S tronic","0006"), Rtronic("R tronic","0007"), Shiftmatic(null,"0008");
		private String key;
		private String id;
		private kubaTransmissionTypes(String key, String id)
		{
			if (key == null)
				this.key = this.name();
			else
				this.key = key;
	        this.id = id;
	    }
		
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return key;
		}
	}
	
	public static enum kubaCountries implements FormHandlerParamsEnumsInterface
	{US, CA, MX}
	
	public static enum kubaTitles implements FormHandlerParamsEnumsInterface
	{Ms("0001"), Mr("0002");
		private String id;
		private kubaTitles(String id)
		{
	        this.id = id;
	    }
	
		public String getId() {
			return id;
		}
	}

	public static enum kubaLanguageTypes implements FormHandlerParamsEnumsInterface
	{EN, ES}

	public static enum kubaEngineVersions implements FormHandlerParamsEnumsInterface
	{Other(null,"A"), Gasoline(null,"B"), Diesel(null,"D"), Electric(null,"E"), BEthanol("Benzine Ethanol","F"), 
	 LPG_Gas("LPG/Gas","G"), Hybrid(null,"H"), Not_known("Not known","N"), Fuel_Oil("Fuel Oil","O");		
		private String key;
		private String id;
		private kubaEngineVersions(String key, String id)
		{
			if (key == null)
				this.key = this.name();
			else
				this.key = key;
	        this.id = id;
	    }
		
		public String getId() {
			return id;
		}
	
		@Override
		public String toString() {
			return key;
		}
	}
	
	public static enum kubaPlannedUsageAKACurrentCarUsage implements FormHandlerParamsEnumsInterface
	{Personal(null,"0001"), Business(null,"0002"), 
	 Personal_Business("Personal and Business","0003"), Answer_Denied("Answer Denied","0009");
		private String key;
		private String id;
		private kubaPlannedUsageAKACurrentCarUsage(String key, String id)
		{
			if (key == null)
				this.key = this.name();
			else
				this.key = key;
	        this.id = id;
	    }
		
		public String getId() {
			return id;
		}
	
		@Override
		public String toString() {
			return key;
		}
	}

	public static enum kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType implements FormHandlerParamsEnumsInterface
	{Purchase(null,"0001"), Leasing(null,"0002"), Financing(null,"0003"), 
	 Undecided(null,"0004"), Answer_Denied("Answer Denied","0009");
		private String key;
		private String id;
		private kubaPlannedFinancingTypeAKAFinanceMethodAKAcurrentCarFinancingType(String key, String id)
		{
			if (key == null)
				this.key = this.name();
			else
				this.key = key;
	        this.id = id;
	    }
		
		public String getId() {
			return id;
		}
	
		@Override
		public String toString() {
			return key;
		}
	}
	
	public static enum kubaTypeOfPurchase implements FormHandlerParamsEnumsInterface
	{New_Car("New Car","0001"), Employee_Car("Employee Car","0002"), Used_Car("Used Car","0003"), 
	 Undecided(null,"0004"), Certified_Preowned("Certified Preowned Car","0005"), Answer_Denied("Answer denied","0009");
		private String key;
		private String id;
		private kubaTypeOfPurchase(String key, String id)
		{
			if (key == null)
				this.key = this.name();
			else
				this.key = key;
	        this.id = id;
	    }
	
		public String getId() {
			return id;
		}
	
		@Override
		public String toString() {
			return key;
		}
	}

	public static enum americanStates implements FormHandlerParamsEnumsInterface
	{AL("Alabama"), AK("Alaska"), AZ("Arizona"), AR("Arkansas"), CA("California"), CO("Colorado"), CT("Connecticu"), DE("Delaware"), DC("District o"),
	 FL("Florida"), GA("Georgia"), HI("Hawaii"), ID("Idaho"), IL("Illinois"), IN("Indiana"), IA("Iowa"), KS("Kansas"), KY("Kentucky"),
	 LA("Louisiana"), ME("Maine"), MD("Maryland"), MA("Massachuse"), MI("Michigan"), MN("Minnesota"), MS("Mississipp"), MO("Missouri"), MT("Montana"),
 	 NE("Nebraska"), NV("Nevada"), NH("New Hampsh"), NJ("New Jersey"), NM("New Mexico"), NY("New York"), NC("North Caro"), ND("North Dako"), OH("Ohio"),
	 OK("Oklahoma"), OR("Oregon"), PA("Pennsylvan"), RI("Rhode Isla"), SC("South Caro"), SD("South Dako"), TN("Tennessee"), TX("Texas"), UT("Utah"),
	 VT("Vermont"), VA("Virginia"), WA("Washington"), WV("West Virgi"), WI("Wisconsin"), WY("Wyoming"), AS("Samoa"), GU("Guam"), PR("Puerto Ric"), 
	 VI("Virgin Isl"), PW("Palau");
		private String longForm;
		private americanStates(String longForm)
		{
	        this.longForm = longForm;
	    }
	
		public String getLongForm() {
			return longForm;
		}
	}
	
	public static enum currentCarBrands implements FormHandlerParamsEnumsInterface
	{AB,AC,AG,AM,AR,AU,AW,
	 BE,BG,BU,BW,
	 CA,CH,CI,CV,
	 DA,DC,DE,DM,DO,DT,
	 FE,FO,FT,
	 GR,
	 HD,HL,HO,HU,
	 IN,IS,IU,IV,
	 JA,JE,
	 KI,
	 LA,LC,LD,LE,LI,LO,LR,LW,
	 MA,MB,MC,MD,MG,MH,MI,MO,MS,MY,
	 NS,NU,
	 OM,OP,
	 PC,PG,PL,PO,PT,PZ,
	 RN,RO,RR,RV,
	 SB,SD,SK,SN,SO,ST,SU,SY,
	 TA,TR,TY,
	 VN,VV,VW
	 }

	public static enum yesOrNo implements FormHandlerParamsEnumsInterface
	{Y, N}
	
	/**
	 * public variables below must have corresponding get/set methods as FormHandlerParams class variables are 
	 * set using reflection which utilises their setter methods. Plus getter methods are also used for other 
	 * purposes.
	 */
	
	//Leads absolute required
	public String requestType;
	public String sourceType;
	public String subType;
	public String dealerId;

	//Kuba absolute required
	public String campaignID;
	
	//Leads & Kuba absolute required
	public String formHandlerType;
	//public String modelName;
	public String brand;
	public String firstName;
	public String lastName;
	public String nickName;
	public String zip;
	
	//Leads & Kuba conditionally required
	public String email;
	public String phone;
	public String mobile;
	public String city;
	public String state;
	public String street1;

	//Leads optional params
	//public String modelCode;
	//public String trim;
	//public String interiorColor;
	//public String exteriorColor;
	//public String year;
	//public String engine;
	//public String msrp;
	//public String msrpBase;
	//public String msrpTotal;
	public String comments;
	public String tradeInVehicleYear;
	public String tradeInVehicleMake;
	public String tradeInVehicleModel;
	public String tradeInVehicleMileage;
	public String tradeInVehicleRemainingBalance;
	public String tradeInVehicleCondition;
	public String contactbydealer;
	public String contactDate1;
	public String contactTime1;
	public String contactDate2;
	public String contactTime2;
	public String requestTestDrive;
	public String timeToPurchase;
//	public String saleType;
//  String bodyStyle;
	
	//Kuba optional params
	public String street2;
	public String country;
	public String title;
	public String language;
//	public String engineVersion;
//	public String enginePower;
	public String plannedPurchaseDate;
	public String typeOfPurchase;
	public String plannedUsage;
	public String currentCarBrand;
	public String currentCarModel;
	public String currentCarModelYear;
	public String currentCarMileage;
	public String currentCarPurchaseDate;
	public String currentCarRegistrationDate;
	public String currentCarUsage;
	public String currentCarFinancingType;
	public String currentCarEndOfFinancingDate;
	public String surveyId;
	public LinkedHashMap<Integer, String> surveyQs;
	public LinkedHashMap<Integer, String> surveyAs;
	public String[] links;
	public String BPID;
//	public String modelDerivatID;
	
	//Leads & Kuba optional params
	//public String transmission;
	public String[] preferredContactMethod;
	public String financeMethod;
	public String successPageURL;
	public String errorPageURL;
	
	//Additional Optional Parameters for LEADS and KUBA
	public String referrer;
	public String visits;
	public String sessionZip;
	public String loggedInBefore;
	public String accountId;
	public String sessionId;
	public String device;
	public String userAgent;
	public String filterSelections;
	public String searchZip;
	public String searchRadius;
	//public String vin;
	public String listingsViewed;
	public String financeTerm;
	public String downPayment;
	public String expectedApr;
	public String tradeInValue;
	//public String options;
	public String financeAmount;
	
	
	public List<Map<String,Object>> vehicles;
	
	//1) to support FormHandler all interfaces that support Leads Submission:
	public List<Map<String,Object>> tags;
    public List<Map<String, Object>> getTags() {
		return tags;
	}
	public void setTags(List<Map<String, Object>> tags) {
		this.tags = tags;
	}
	
	public String getFormHandlerType() {
		return formHandlerType;
	}

	public void setFormHandlerType(String formHandlerType) {
		this.formHandlerType = formHandlerType;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}
//
//	public String getModelName() {
//		return modelName;
//	}
//
//	/**
//	 * sets modelName and model
//	 * @param modelName
//	 */
//	public void setModelName(String modelName) {
//		this.modelName = modelName;
//	}
//
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}
//
//	public String getModelCode() {
//		return modelCode;
//	}
//
//	public void setModelCode(String modelCode) {
//		this.modelCode = modelCode;
//	}
//
//	public String getTrim() {
//		return trim;
//	}
//
//	public void setTrim(String trim) {
//		this.trim = trim;
//	}
//
//	public String getInteriorColor() {
//		return interiorColor;
//	}
//
//	public void setInteriorColor(String interiorColor) {
//		this.interiorColor = interiorColor;
//	}
//
//	public String getExteriorColor() {
//		return exteriorColor;
//	}
//
//	public void setExteriorColor(String exteriorColor) {
//		this.exteriorColor = exteriorColor;
//	}
//
//	public String getYear() {
//		return year;
//	}
//
//	public void setYear(String year) {
//		this.year = year;
//	}
//
//	public String getEngine() {
//		return engine;
//	}
//
//	public void setEngine(String engine) {
//		this.engine = engine;
//	}
//
//	public String getMsrp() {
//		return msrp;
//	}
//
//	public void setMsrp(String msrp) {
//		this.msrp = msrp;
//	}
//
//	public String getMsrpBase() {
//		return msrpBase;
//	}
//
//	public void setMsrpBase(String msrpBase) {
//		this.msrpBase = msrpBase;
//	}
//
//	public String getMsrpTotal() {
//		return msrpTotal;
//	}
//
//	public void setMsrpTotal(String msrpTotal) {
//		this.msrpTotal = msrpTotal;
//	}
//
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTradeInVehicleYear() {
		return tradeInVehicleYear;
	}

	public void setTradeInVehicleYear(String tradeInVehicleYear) {
		this.tradeInVehicleYear = tradeInVehicleYear;
	}

	public String getTradeInVehicleMake() {
		return tradeInVehicleMake;
	}

	public void setTradeInVehicleMake(String tradeInVehicleMake) {
		this.tradeInVehicleMake = tradeInVehicleMake;
	}

	public String getTradeInVehicleModel() {
		return tradeInVehicleModel;
	}

	public void setTradeInVehicleModel(String tradeInVehicleModel) {
		this.tradeInVehicleModel = tradeInVehicleModel;
	}

	public String getTradeInVehicleMileage() {
		return tradeInVehicleMileage;
	}

	public void setTradeInVehicleMileage(String tradeInVehicleMileage) {
		this.tradeInVehicleMileage = tradeInVehicleMileage;
	}

	public String getTradeInVehicleRemainingBalance() {
		return tradeInVehicleRemainingBalance;
	}

	public void setTradeInVehicleRemainingBalance(
			String tradeInVehicleRemainingBalance) {
		this.tradeInVehicleRemainingBalance = tradeInVehicleRemainingBalance;
	}

	public String getTradeInVehicleCondition() {
		return tradeInVehicleCondition;
	}

	public void setTradeInVehicleCondition(String tradeInVehicleCondition) {
		this.tradeInVehicleCondition = tradeInVehicleCondition;
	}

	public String getContactbydealer() {
		return contactbydealer;
	}

	public void setContactbydealer(String contactbydealer) {
		this.contactbydealer = contactbydealer;
	}

	public String getContactDate1() {
		return contactDate1;
	}

	public void setContactDate1(String contactDate1) {
		this.contactDate1 = contactDate1;
	}

	public String getContactTime1() {
		return contactTime1;
	}

	public void setContactTime1(String contactTime1) {
		this.contactTime1 = contactTime1;
	}

	public String getContactDate2() {
		return contactDate2;
	}

	public void setContactDate2(String contactDate2) {
		this.contactDate2 = contactDate2;
	}

	public String getContactTime2() {
		return contactTime2;
	}

	public void setContactTime2(String contactTime2) {
		this.contactTime2 = contactTime2;
	}

	public String getRequestTestDrive() {
		return requestTestDrive;
	}

	public void setRequestTestDrive(String requestTestDrive) {
		this.requestTestDrive = requestTestDrive;
	}

	public String getTimeToPurchase() {
		return timeToPurchase;
	}

	public void setTimeToPurchase(String timeToPurchase) {
		this.timeToPurchase = timeToPurchase;
	}
//
//	public String getSaleType() {
//		return saleType;
//	}
//
//	public void setSaleType(String saleType) {
//		this.saleType = saleType;
//	}
//
//	public String getBodyStyle() {
//		return bodyStyle;
//	}
//
//	public void setBodyStyle(String bodyStyle) {
//		this.bodyStyle = bodyStyle;
//	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
//
//	public String getEngineVersion() {
//		return engineVersion;
//	}
//
//	public void setEngineVersion(String engineVersion) {
//		this.engineVersion = engineVersion;
//	}
//
//	public String getEnginePower() {
//		return enginePower;
//	}
//
//	public void setEnginePower(String enginePower) {
//		this.enginePower = enginePower;
//	}

	public String getPlannedPurchaseDate() {
		return plannedPurchaseDate;
	}

	public void setPlannedPurchaseDate(String plannedPurchaseDate) {
		this.plannedPurchaseDate = plannedPurchaseDate;
	}

	public String getTypeOfPurchase() {
		return typeOfPurchase;
	}

	public void setTypeOfPurchase(String typeOfPurchase) {
		this.typeOfPurchase = typeOfPurchase;
	}

	public String getPlannedUsage() {
		return plannedUsage;
	}

	public void setPlannedUsage(String plannedUsage) {
		this.plannedUsage = plannedUsage;
	}

	public String getCurrentCarBrand() {
		return currentCarBrand;
	}

	public void setCurrentCarBrand(String currentCarBrand) {
		this.currentCarBrand = currentCarBrand;
	}

	public String getCurrentCarModel() {
		return currentCarModel;
	}

	public void setCurrentCarModel(String currentCarModel) {
		this.currentCarModel = currentCarModel;
	}

	public String getCurrentCarModelYear() {
		return currentCarModelYear;
	}

	public void setCurrentCarModelYear(String currentCarModelYear) {
		this.currentCarModelYear = currentCarModelYear;
	}

	public String getCurrentCarMileage() {
		return currentCarMileage;
	}

	public void setCurrentCarMileage(String currentCarMileage) {
		this.currentCarMileage = currentCarMileage;
	}

	public String getCurrentCarPurchaseDate() {
		return currentCarPurchaseDate;
	}

	public void setCurrentCarPurchaseDate(String currentCarPurchaseDate) {
		this.currentCarPurchaseDate = currentCarPurchaseDate;
	}

	public String getCurrentCarRegistrationDate() {
		return currentCarRegistrationDate;
	}

	public void setCurrentCarRegistrationDate(String currentCarRegistrationDate) {
		this.currentCarRegistrationDate = currentCarRegistrationDate;
	}

	public String getCurrentCarUsage() {
		return currentCarUsage;
	}

	public void setCurrentCarUsage(String currentCarUsage) {
		this.currentCarUsage = currentCarUsage;
	}

	public String getCurrentCarFinancingType() {
		return currentCarFinancingType;
	}

	public void setCurrentCarFinancingType(String currentCarFinancingType) {
		this.currentCarFinancingType = currentCarFinancingType;
	}

	public String getCurrentCarEndOfFinancingDate() {
		return currentCarEndOfFinancingDate;
	}

	public void setCurrentCarEndOfFinancingDate(String currentCarEndOfFinancingDate) {
		this.currentCarEndOfFinancingDate = currentCarEndOfFinancingDate;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public LinkedHashMap<Integer, String> getSurveyQs() {
		return surveyQs;
	}

	public void setSurveyQs(LinkedHashMap<Integer, String> surveyQs) {
		this.surveyQs = surveyQs;
		log.debug("surveyQs LinkedHashMap contents:\n"+this.surveyQs.toString());
	}

	public LinkedHashMap<Integer, String> getSurveyAs() {
		return surveyAs;
	}

	public void setSurveyAs(LinkedHashMap<Integer, String> surveyAs) {
		this.surveyAs = surveyAs;
		log.debug("surveyAs LinkedHashMap contents:\n"+this.surveyAs.toString());
	}

	public String[] getLinks() {
		return links;
	}

	public void setLinks(String[] links) {
		this.links = links;
	}

	public String getBPID() {
		return BPID;
	}

	public void setBPID(String BPID) {
		this.BPID = BPID;
	}

//	public String getTransmission() {
//		return transmission;
//	}
//
//	/**
//	 * sets transmission and transmissionType
//	 * @param transmission
//	 */
//	public void setTransmission(String transmission) {
//		this.transmission = transmission;
//	}

	public String[] getPreferredContactMethod() {
		return preferredContactMethod;
	}

	public void setPreferredContactMethod(String[] preferredContactMethod) {
		this.preferredContactMethod = preferredContactMethod;
	}

	public String getFinanceMethod() {
		return financeMethod;
	}

	public void setFinanceMethod(String financeMethod) {
		this.financeMethod = financeMethod;
	}

	public String getSuccessPageURL() {
		return successPageURL;
	}

	public void setSuccessPageURL(String successPageURL) {
		this.successPageURL = successPageURL;
	}

	public String getErrorPageURL() {
		return errorPageURL;
	}

	public void setErrorPageURL(String errorPageURL) {
		this.errorPageURL = errorPageURL;
	}
		
	public String getNickName() {
            return nickName;
        }
    
        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

    public static String enumStringsAsCommaSeparatedSentence(FormHandlerParamsEnumsInterface[] enumValues)
	{
		StringBuilder tempStrB = new StringBuilder();
		for (int i = 0 ; i <enumValues.length; i++)
		{
			if (i+1==enumValues.length)
				tempStrB.append(FormHandlerConstants.SPACE).append(FormHandlerConstants.OR_STR).append(FormHandlerConstants.SPACE);
			else
				if (i!=0)
					tempStrB.append(FormHandlerConstants.COMMA).append(FormHandlerConstants.SPACE);
			tempStrB.append(enumValues[i].toString());
		}
		return tempStrB.toString();
	}


    @Override
	public String toString() {
		return "FormHandlerParams [requestType=" + requestType
				+ ", sourceType=" + sourceType + ", subType=" + subType
				+ ", dealerId=" + dealerId + ", campaignID=" + campaignID
				+ ", formHandlerType=" + formHandlerType + ", brand=" + brand
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", nickName=" + nickName + ", zip=" + zip + ", email="
				+ email + ", phone=" + phone + ", mobile=" + mobile + ", city="
				+ city + ", state=" + state + ", street1=" + street1
				+ ", tradeInVehicleYear=" + tradeInVehicleYear
				+ ", tradeInVehicleMake=" + tradeInVehicleMake
				+ ", tradeInVehicleModel=" + tradeInVehicleModel
				+ ", tradeInVehicleMileage=" + tradeInVehicleMileage
				+ ", tradeInVehicleRemainingBalance="
				+ tradeInVehicleRemainingBalance + ", tradeInVehicleCondition="
				+ tradeInVehicleCondition + ", contactbydealer="
				+ contactbydealer + ", contactDate1=" + contactDate1
				+ ", contactTime1=" + contactTime1 + ", contactDate2="
				+ contactDate2 + ", contactTime2=" + contactTime2
				+ ", requestTestDrive=" + requestTestDrive
				+ ", timeToPurchase=" + timeToPurchase + ", saleType="
				+ ", bodyStyle=" + ", street2="
				+ street2 + ", country=" + country + ", title=" + title
				+ ", language=" + language + ", engineVersion=" + ", enginePower=" + ", plannedPurchaseDate="
				+ plannedPurchaseDate + ", typeOfPurchase=" + typeOfPurchase
				+ ", plannedUsage=" + plannedUsage + ", currentCarBrand="
				+ currentCarBrand + ", currentCarModel=" + currentCarModel
				+ ", currentCarModelYear=" + currentCarModelYear
				+ ", currentCarMileage=" + currentCarMileage
				+ ", currentCarPurchaseDate=" + currentCarPurchaseDate
				+ ", currentCarRegistrationDate=" + currentCarRegistrationDate
				+ ", currentCarUsage=" + currentCarUsage
				+ ", currentCarFinancingType=" + currentCarFinancingType
				+ ", currentCarEndOfFinancingDate="
				+ currentCarEndOfFinancingDate + ", surveyId=" + surveyId
				+ ", surveyQs=" + surveyQs + ", surveyAs=" + surveyAs
				+ ", links=" + Arrays.toString(links) + ", BPID=" + BPID
				+ ", modelDerivatID=" + ", transmission="
				+ ", preferredContactMethod="
				+ Arrays.toString(preferredContactMethod) + ", financeMethod="
				+ financeMethod + ", successPageURL=" + successPageURL
				+ ", errorPageURL=" + errorPageURL + ", referrer=" + referrer
				+ ", visits=" + visits + ", sessionZip=" + sessionZip
				+ ", loggedInBefore=" + loggedInBefore + ", accountId="
				+ accountId + ", sessionId=" + sessionId + ", device=" + device
				+ ", userAgent=" + userAgent + ", filterSelections="
				+ filterSelections + ", searchZip=" + searchZip
				+ ", searchRadius=" + searchRadius + ", vin="  
				+ ", listingsViewed=" + listingsViewed + ", financeTerm="
				+ financeTerm + ", downPayment=" + downPayment
				+ ", expectedApr=" + expectedApr + ", tradeInValue="
				+ tradeInValue + ", options=" + ", financeAmount="
				+ financeAmount + ", vehicles=" + vehicles +  "tags=[" + tags + "]]";
	}

//	public String getModelDerivatID() {
//        return modelDerivatID;
//    }
//
//    public void setModelDerivatID(String modelDerivatID) {
//        this.modelDerivatID = modelDerivatID;
//    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getSessionZip() {
        return sessionZip;
    }

    public void setSessionZip(String sessionZip) {
        this.sessionZip = sessionZip;
    }

    public String getLoggedInBefore() {
        return loggedInBefore;
    }

    public void setLoggedInBefore(String loggedInBefore) {
        this.loggedInBefore = loggedInBefore;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getFilterSelections() {
        return filterSelections;
    }

    public void setFilterSelections(String filterSelections) {
        this.filterSelections = filterSelections;
    }

    public String getSearchZip() {
        return searchZip;
    }

    public void setSearchZip(String searchZip) {
        this.searchZip = searchZip;
    }

    
//
//    public String getVin() {
//        return vin;
//    }
//
//    public void setVin(String vin) {
//        this.vin = vin;
//    }

    public String getListingsViewed() {
        return listingsViewed;
    }

    public void setListingsViewed(String listingsViewed) {
        this.listingsViewed = listingsViewed;
    }

    public String getFinanceTerm() {
        return financeTerm;
    }

    public void setFinanceTerm(String financeTerm) {
        this.financeTerm = financeTerm;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getExpectedApr() {
        return expectedApr;
    }

    public void setExpectedApr(String expectedApr) {
        this.expectedApr = expectedApr;
    }

    public String getTradeInValue() {
        return tradeInValue;
    }

    public void setTradeInValue(String tradeInValue) {
        this.tradeInValue = tradeInValue;
    }
//
//    public String getOptions() {
//        return options;
//    }
//
//    public void setOptions(String options) {
//        this.options = options;
//    }

    public String getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(String financeAmount) {
        this.financeAmount = financeAmount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(String searchRadius) {
        this.searchRadius = searchRadius;
    }

	public List<Map<String, Object>> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Map<String, Object>> vehicles) {
		this.vehicles = vehicles;
	}
    
        
}
