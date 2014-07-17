package com.vw.formhandler.common;

public interface FormHandlerConstants 
{
	//param names
	public static final String formatTypeParamName = "formatType";
	public static final String formHandlerTypeParamName = "formHandlerType";
	public static final String surveyQParamPrefix = "surveyQ";
	public static final String surveyAParamPrefix = "surveyA";
	public static final String successPageURL = "successPageURL";
	public static final String errorPageURL = "errorPageURL";

	//params for success/error URLs
	public static final String leadid = "leadid";
	public static final String kubaid = "kubaid";
	public static final String formhandlerError = "formhandlerError";
	public static final String validationError = "validationError";
	public static final String kubaResponseError = "kubaResponseError";
	public static final String leadsResponseError = "leadsResponseError";

	//leads/kuba constants
	public static final String phone = "phone";
	public static final String mobile = "mobile";
	public static final String email = "email";
	
	//leads/kuba default values
	public static final String defaultLeadsCountryId = "35";
	public static final String defaultLeadsLanguageId = "70";
	public static final String defaultLeadsPreferredContactMethodStr = "phone or email";
	public static final String defaultFinanceMethodValueForPurchase = "cash";
	public static final String defaultFinanceMethodValueForLeasing = "lease";
	public static final String defaultFinanceMethodValueForFinancing = "finance";
	
	//spring view level constants
	public static final String ERROR = "error";
	public static final String ERRORS = "errors";
	public static final String invalidMediaContentTypeError = "invalidMediaContentTypeError";
	public static final String defaultContentType = "application/xml";
	public static final int byteBufferSize = 2048;
	
	//field names
	public static final String fieldSurveyQs = "surveyQs";
	public static final String fieldSurveyAs = "surveyAs";
	
	//Date format constant
	public static final String validDateFormatStr = "yyyy-MM-dd";
	
	//String contants
	public static final String EMPTY_STR = "";
	public static final String EQUAL = "=";
	public static final String AMPERSAND = "&";
	public static final String QUESTION = "?";
	public static final String COMMA = ",";
	public static final String SPACE = " ";
	public static final String OR_STR = "or";
	public static final char Y_CHAR = 'Y';
	public static final char N_CHAR = 'N';
	
	//HttpClientUtil constants
	public static final String XMLPARAM_NAME="xml";
	public static final String USE_PROXY = "httpClientUtil.useproxy";
	public static final String PROXY_HOST = "httpClientUtil.proxy.host";
	public static final String PROXY_USER = "httpClientUtil.proxy.user";
	public static final String PROXY_PORT = "httpClientUtil.proxy.port";
	public static final String PROXY_PASSWD = "httpClientUtil.proxy.password";

	public static final String LEADS_PATH = "leads.path";
	public static final String KUBA_PATH = "kuba.path";
	public static final String CUSTOMER_CARE_PATH = "customerCare.path";
	public static final String WEGA_URL = "wega.url";
	public static final String PING_MAX_RETRYCOUNT = "ping.max.retry";
	public static final String MAX_CONNECTIONS = "maxConnections";
	public static final String DEFAULT_ENCDING = "default_encoding";
	
	//brand
	public static final String AU = "AU";
	public static final String VW = "VW";
	public static final String MODELNAME = "modelName";
	public static final String MODELCODE = "modelCode";
	public static final String TRIM = "trim";
	public static final String INTERIORCOLOR = "interiorColor";
	public static final String EXTERIORCOLOR = "exteriorColor";
	public static final String YEAR = "year";
	public static final String ENGINE = "engine";
	public static final String MSRP = "msrp";
	public static final String MSRPBASE = "msrpBase";
	public static final String MSRPTOTAL = "msrpTotal";
	public static final String SALETYPE = "saleType";
	public static final String BODYSTYLE = "bodyStyle";
	public static final String TRANSMISSION = "transmission";
	public static final String VIN = "vin";
	public static final String OPTIONS = "options";
	public static final String ENGINEPOWER = "enginePower";
	public static final String ENGINEVERSION = "engineVersion";
	public static final String MODELDERIVAT = "modelDerivatID";
	public static final String KUBAMODELID = "kubaModelID";
        
}
