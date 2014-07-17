package com.vw.formhandler.webspring.mvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import javax.servlet.ServletRequest;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.FormHandlerConstants;
import com.vw.formhandler.common.HttpClientUtil;
import com.vw.formhandler.common.HttpClientUtilResponse;
import com.vw.formhandler.webspring.common.ControllerUtils;
import com.vw.formhandler.webspring.common.InvalidRequestException;
import com.vw.formhandler.webspring.common.VOBuilderUtil;
import com.vw.formhandler.webspring.model.kuba.KubaServiceResponseVO;
import com.vw.formhandler.webspring.model.leads.LeadsServiceResponseVO;
import com.vw.formhandler.webspring.model.leads.LeadsServiceVO;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.brands;
import com.vw.formhandler.webspring.mvc.FormHandlerParams.formHandlerTypes;
import com.vw.formhandler.webspring.mvc.model.ServiceResponseException;
import com.vw.formhandler.webspring.mvc.response.AbstractError;
import com.vw.formhandler.webspring.mvc.response.AbstractServiceResponseErrorVO;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.FormHandlerResponseInterface;
import com.vw.formhandler.webspring.mvc.response.Formhandler;
import com.vw.formhandler.webspring.mvc.response.ValidationError;
import com.vw.formhandler.webspring.mvc.response.kuba.KubaServiceResponseErrorVO;
import com.vw.formhandler.webspring.mvc.response.leads.LeadsServiceResponseErrorVO;
import com.vw.formhandler.webspring.mvc.validators.CustomerCareValidator;
import com.vw.formhandler.webspring.mvc.validators.KubaValidator;
import com.vw.formhandler.webspring.mvc.validators.LeadsValidator;
import com.vw.formhandler.webspring.service.CustomerCareService;
import com.vw.formhandler.webspring.service.ProcessLeadService;

@Controller
public class FormHandlerController {
    private static final Logger log = Logger.getLogger(FormHandlerController.class);

    private static final String JSON = "json";

    @Autowired
    private LeadsValidator leadsValidator;

    @Autowired
    private KubaValidator kubaValidator;

    @Autowired
    private CustomerCareValidator customerCareValidator;

    @Autowired
    private Jaxb2Marshaller marshaller;

    private String leadsURL, kubaURL, customerCareURL, wegaURL;

    @Autowired
    HttpClientUtil httpClientUtil;

    @Autowired
    private CustomerCareService customerCareService;
    //refactored to extract method, moved into ProcessLeadService
    @Autowired
    private ProcessLeadService processLeadService;

    private void setupPropsIfNeeded() throws InvalidPropertiesFormatException, IOException {
        // if (this.props == null) {
        // this.props = new Properties();
        // this.props.loadFromXML(appContext.getResource("WEB-INF/httpClientUtilProperties.xml").getInputStream());
        // this.leadsURL = new
        // StringBuilder(props.getProperty(FormHandlerConstants.SERVCE_URL_PREFIX)).append(
        // props.getProperty(FormHandlerConstants.LEADS_PATH)).toString();
        // this.kubaURL = new
        // StringBuilder(props.getProperty(FormHandlerConstants.SERVCE_URL_PREFIX)).append(
        // props.getProperty(FormHandlerConstants.KUBA_PATH)).toString();
        // this.customerCareURL = new
        // StringBuilder(props.getProperty(FormHandlerConstants.SERVCE_URL_PREFIX)).append(
        // props.getProperty(FormHandlerConstants.CUSTOMER_CARE_PATH)).toString();
        // this.wegaURL = new
        // StringBuilder(props.getProperty(FormHandlerConstants.WEGA_URL)).toString();
        // }
    }

    @RequestMapping(value = "{formHandlerType}", method = { RequestMethod.GET, RequestMethod.POST })
    public Object formHandlerMethod1(
            @PathVariable(FormHandlerConstants.formHandlerTypeParamName) String formHandlerType,
            final ServletRequest request) throws Throwable {
        log.debug("Inside FormHarequestndlerController.formHandlerMethod1 method");
        logParams(request);
        setupPropsIfNeeded();
        ExtendedParams inputParams = new ExtendedParams();
        inputParams.setFormHandlerType(formHandlerType);
        if (!CommonUtils.isNullOrBlank(request.getParameter(JSON))) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonObject = mapper.readValue(request.getParameter(JSON),
                    new TypeReference<Map<String, Object>>() {
                    });
            ;
            setupInputParamsFromJson(inputParams, jsonObject);
        } else {
            setupInputParams(inputParams, request.getParameterMap());
        }
        return returnResponseObjectOrRedirect((FormHandlerResponseInterface) validateAndExecute(inputParams),
                inputParams.getSuccessPageURL(), inputParams.getErrorPageURL(), inputParams.getEmail());
    }

    @RequestMapping(value = "leads-crm", method = { RequestMethod.GET, RequestMethod.POST })
    public Object formHandlerMethod2(final ServletRequest request) throws Throwable {
        log.debug("Inside FormHandlerController.formHandlerMethod2 method");
        logParams(request);
        setupPropsIfNeeded();
        ExtendedParams inputParams = new ExtendedParams();
        inputParams.setFormHandlerType(FormHandlerParams.formHandlerTypes.both.toString());
        if (!CommonUtils.isNullOrBlank(request.getParameter(JSON))) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonObject = mapper.readValue(request.getParameter(JSON),
                    new TypeReference<Map<String, Object>>() {
                    });
            ;
            setupInputParamsFromJson(inputParams, jsonObject);
        } else {
            setupInputParams(inputParams, request.getParameterMap());
        }
        return returnResponseObjectOrRedirect((FormHandlerResponseInterface) validateAndExecute(inputParams),
                inputParams.getSuccessPageURL(), inputParams.getErrorPageURL(), inputParams.getEmail());
    }

    private void logParams(ServletRequest request) {
    	Map<String,String[]> paramMap=request.getParameterMap();
        for(Entry<String,String[]> entry:paramMap.entrySet())
        {
        	String[] str=entry.getValue();
        	log.debug(entry.getKey()+": "+StringUtils.join(str, "$#$"));
        }
	}

	protected void setupInputParamsFromJson(ExtendedParams inputParams, Map<String, Object> parameterMap)
            throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        Field[] objFields = ExtendedParams.class.getFields();
        for (Field field : objFields) {
            // for those surveyQs and surveyAs where a param name match isnt
            // that obvious
            if (field.getName().equals(FormHandlerConstants.fieldSurveyQs)
                    || field.getName().equals(FormHandlerConstants.fieldSurveyAs)) {
                String paramPrefixStr = field.getName().equals(FormHandlerConstants.fieldSurveyQs) ? FormHandlerConstants.surveyQParamPrefix
                        : FormHandlerConstants.surveyAParamPrefix;
                String tempStr = null;
                LinkedHashMap<Integer, String> surveyQAParamValue = new LinkedHashMap<Integer, String>();
                for (int i = 1; parameterMap.containsKey(tempStr = (new StringBuilder(paramPrefixStr).append(i)
                        .toString())); i++) {
                    surveyQAParamValue.put(new Integer(i), (String)parameterMap.get(tempStr));
                }
                ExtendedParams.class.getMethod(
                        new StringBuilder("set").append(field.getName().substring(0, 1).toUpperCase())
                                .append(field.getName().substring(1)).toString(), LinkedHashMap.class).invoke(
                        inputParams, surveyQAParamValue);
            } else if (parameterMap.containsKey(field.getName())) {
                if (field.getType().isAssignableFrom(List.class)) {
                    ExtendedParams.class.getMethod(
                            new StringBuilder("set").append(field.getName().substring(0, 1).toUpperCase())
                                    .append(field.getName().substring(1)).toString(), List.class).invoke(inputParams,
                            (Object) parameterMap.get(field.getName()));
                } else
                    ExtendedParams.class.getMethod(
                            new StringBuilder("set").append(field.getName().substring(0, 1).toUpperCase())
                                    .append(field.getName().substring(1)).toString(), String.class).invoke(inputParams,
                            parameterMap.get(field.getName()));

            }
        }
    }

    private Object validateAndExecute(ExtendedParams inputParams) throws Throwable {
        log.debug("Inside FormHandlerController.validateAndExecute method");
        FormHandlerResponseInterface response = null;

        // validate routine
        Errors errors = validate(inputParams);
        if (errors.getErrors() != null && errors.getErrors().size() > 0)
            throw new InvalidRequestException(errors, inputParams.getErrorPageURL(), inputParams.getEmail());

        HttpClientUtilResponse leadServiceResponse = null;
        HttpClientUtilResponse kubaServiceResponse = null;
        HttpClientUtilResponse customerCareResponse = null;

        StreamResult result = new StreamResult(new StringWriter());
        switch (formHandlerTypes.valueOf(inputParams.getFormHandlerType())) {
        case leads:
            leadServiceResponse = processLeads(inputParams, httpClientUtil, result);
            break;

        case crm:
            kubaServiceResponse = processKubaRequests(inputParams, httpClientUtil, result);
            break;

        case both:
            leadServiceResponse = processLeads(inputParams, httpClientUtil, result);

            result.setWriter(new StringWriter());
            kubaServiceResponse = processKubaRequests(inputParams, httpClientUtil, result);
            break;

        case customer_care:
            log.debug("Customer Care calling...");
            customerCareResponse = customerCareService
                    .processCustomerCareRequests(inputParams, result, customerCareURL);
            break;
        }

        if (leadServiceResponse != null) {
            response = processLeadServiceResponse(response, leadServiceResponse);
        }

        if (kubaServiceResponse != null) {
            response = processKubaServiceResponse(response, kubaServiceResponse);
        }

        if (customerCareResponse != null) {
            log.debug("customerCareResponse is not null, creating response...");
            response = customerCareService.processCustomerCareServiceResponse(response, customerCareResponse);
        }

        return response;
    }



    /**
     * 
     * @param inputParams
     * @param httpClientUtil
     * @param result
     * @return
     * @throws ParseException
     * @throws Exception
     */
    private HttpClientUtilResponse processKubaRequests(ExtendedParams inputParams, HttpClientUtil httpClientUtil,
            StreamResult result) throws ParseException, Exception {
        HttpClientUtilResponse kubaServiceResponse;
        String tempStr;

        marshaller.marshal(VOBuilderUtil.buildKubaServiceVOFromFormHandlerParamsObj(inputParams), result);
        tempStr = result.getWriter().toString();
        log.debug(new StringBuilder("Hand Raiser service request input XML:\n").append(tempStr).append("\n"));
        kubaServiceResponse = httpClientUtil.invokeService(kubaURL, tempStr);
        return kubaServiceResponse;
    }

    /**
     * 
     * @param inputParams
     * @param httpClientUtil
     * @param result
     * @return
     * @throws ParseException
     * @throws Exception
     */
    private HttpClientUtilResponse processLeads(ExtendedParams inputParams, HttpClientUtil httpClientUtil,
            StreamResult result) throws ParseException, Exception {
        HttpClientUtilResponse leadServiceResponse;
        //refactored to extract method, moved into ProcessLeadService 
        String tempStr = processLeadService.produceXmlToSend(inputParams, result);
        log.debug(new StringBuilder("Leads service request input XML:\n").append(tempStr).append("\n"));
        leadServiceResponse = httpClientUtil.invokeService(leadsURL, tempStr);
        return leadServiceResponse;
    }

	

    /**
     * 
     * @param inputParams
     * @param httpClientUtil
     * @return
     * @throws Exception
     */
    private HttpClientUtilResponse processWegaRequest(ExtendedParams inputParams, HttpClientUtil httpClientUtil)
            throws Exception {

        String soapXml = VOBuilderUtil.buildWegaRequestFromFormHandlerParamsObj(inputParams);
        log.debug(new StringBuilder("WEGA CRM service request input XML:\n").append(soapXml).append("\n"));
        return httpClientUtil.invokeService(wegaURL, soapXml);

    }

    /**
     * 
     * @param response
     * @param kubaServiceResponse
     * @return
     */
    private FormHandlerResponseInterface processKubaServiceResponse(FormHandlerResponseInterface response,
            HttpClientUtilResponse kubaServiceResponse) {
        Errors tempErrors;
        Object unMarshallResponse;
        log.debug("kubaServiceResponse: " + new String(kubaServiceResponse.getResponseByteArray()));
        unMarshallResponse = marshaller.unmarshal(new StreamSource(new ByteArrayInputStream(kubaServiceResponse
                .getResponseByteArray())));
        if (unMarshallResponse instanceof KubaServiceResponseVO) {
            if (response == null)
                response = new Formhandler();
            else if (response instanceof Errors) {
                tempErrors = (Errors) response;
                response = new Formhandler();
                ((Formhandler) response).getErrors().addAll(tempErrors.getErrors());
            }

            KubaServiceResponseVO tempKubaServiceResponseVO = (KubaServiceResponseVO) unMarshallResponse;
            ((Formhandler) response).setKubaId(tempKubaServiceResponseVO.getRequestId().toString());

            if (!CommonUtils.isNullOrEmpty(kubaServiceResponse.getPreviousTryResponsesStack())
                    && kubaServiceResponse.getPreviousTryResponsesStack().size() > 0) {
                tempErrors = getErrorsFromPreviousResponsesStack(kubaServiceResponse.getPreviousTryResponsesStack(),
                        false);
                ((Formhandler) response).getErrors().addAll(tempErrors.getErrors());
            }
        } else {
            boolean isLeadsServiceResponse = false;
            response = customerCareService.processErrorResponse(response, kubaServiceResponse, isLeadsServiceResponse);
        }
        return response;
    }

    /**
     * 
     * @param response
     * @param leadServiceResponse
     * @return
     */
    private FormHandlerResponseInterface processLeadServiceResponse(FormHandlerResponseInterface response,
            HttpClientUtilResponse leadServiceResponse) {
        // FormHandlerResponseInterface response = null;
        Errors tempErrors;
        Object unMarshallResponse;
        log.debug("leadServiceResponse: " + new String(leadServiceResponse.getResponseByteArray()));
        unMarshallResponse = marshaller.unmarshal(new StreamSource(new ByteArrayInputStream(leadServiceResponse
                .getResponseByteArray())));
        if (unMarshallResponse instanceof LeadsServiceResponseVO) {
            response = new Formhandler();
            LeadsServiceResponseVO tempLeadsServiceResponseVO = (LeadsServiceResponseVO) unMarshallResponse;
            ((Formhandler) response).setLeadId(tempLeadsServiceResponseVO.getLeadId().toString());

            if (!CommonUtils.isNullOrEmpty(leadServiceResponse.getPreviousTryResponsesStack())
                    && leadServiceResponse.getPreviousTryResponsesStack().size() > 0) {
                tempErrors = getErrorsFromPreviousResponsesStack(leadServiceResponse.getPreviousTryResponsesStack(),
                        true);
                ((Formhandler) response).getErrors().addAll(tempErrors.getErrors());
            }
        } else {
            boolean isLeadsServiceResponse = true;
            response = customerCareService.processErrorResponse(response, leadServiceResponse, isLeadsServiceResponse);
        }
        return response;
    }

    /**
     * 
     * @param previousResponsesStack
     * @param isLeadsServiceResponse
     * @return
     */
    private Errors getErrorsFromPreviousResponsesStack(Stack<HttpClientUtilResponse> previousResponsesStack,
            boolean isLeadsServiceResponse) {
        Errors tempErrors = new Errors();
        AbstractServiceResponseErrorVO tempError;
        ServiceResponseException tempServiceResponseException;
        for (HttpClientUtilResponse currentResponse = previousResponsesStack.peek(); currentResponse != null; currentResponse = previousResponsesStack
                .pop()) {
            tempServiceResponseException = (ServiceResponseException) marshaller.unmarshal(new StreamSource(
                    new ByteArrayInputStream(currentResponse.getResponseByteArray())));

            tempError = constructServiceResponseErrorFromServiceResponseException(tempServiceResponseException,
                    currentResponse.getStatus(), isLeadsServiceResponse);
            tempErrors.getErrors().add(tempError);
        }
        return tempErrors;
    }

    /**
     * 
     * @param serviceResponseException
     * @param serviceResponseStatus
     * @param isLeadsServiceResponse
     * @return
     */
    private AbstractServiceResponseErrorVO constructServiceResponseErrorFromServiceResponseException(
            ServiceResponseException serviceResponseException, Integer serviceResponseStatus,
            boolean isLeadsServiceResponse) {
        AbstractServiceResponseErrorVO tempError;
        if (isLeadsServiceResponse)
            tempError = new LeadsServiceResponseErrorVO();
        else
            tempError = new KubaServiceResponseErrorVO();
        tempError.setCode(serviceResponseException.getErrorCode() != null ? (Integer.valueOf(serviceResponseException
                .getErrorCode())) : (null));
        tempError.setMessage(serviceResponseException.getMessage());
        tempError.setXmlSource(serviceResponseException.getXmlSource());
        tempError.setResponseCode(serviceResponseStatus);
        return tempError;
    }

    /**
     * All fundamental validations are done here; different from CommonValidator
     * or its subclasses
     * 
     * @param inputParams
     * @return
     */
    private Errors validate(ExtendedParams inputParams) {
        Errors errors = new Errors();

        if (CommonUtils.enumContains(formHandlerTypes.values(), inputParams.getFormHandlerType())) {
            switch (formHandlerTypes.valueOf(inputParams.getFormHandlerType())) {
            case leads:
                errors.getErrors().addAll(leadsValidator.validateAll(inputParams).getErrors());
                errors.getErrors().addAll(leadsValidator.validateLeadsOnlyRequiredFields(inputParams).getErrors());
                errors.getErrors().addAll(leadsValidator.validateLeadsOnlyOptionalFields(inputParams).getErrors());
                break;

            case crm:
                errors.getErrors().addAll(kubaValidator.validateAll(inputParams).getErrors());
                errors.getErrors().addAll(kubaValidator.validateKubaOnlyOptionalFields(inputParams).getErrors());
                break;

            case both:
                errors.getErrors().addAll(leadsValidator.validateAll(inputParams).getErrors());
                errors.getErrors().addAll(
                        kubaValidator.validateKubaOrProcessBothRequiredFields(inputParams).getErrors());
                errors.getErrors().addAll(
                        kubaValidator.validateKubaOrProcessBothOptionalFields(inputParams).getErrors());
                if("AU".equals(inputParams.getBrand()))
                {
                	if(!CommonUtils.isNullOrEmpty(inputParams.getVehicles()))
                	{
	                	for(Map<String,Object> vehicle:inputParams.getVehicles())
	                	{
	                		if(CommonUtils.isNullOrBlank((String) vehicle.get(FormHandlerConstants.KUBAMODELID)))
	                		{
	                			errors.getErrors().add(new ValidationError(298));
	                		}
	                	}
                	}
                }
                break;

            case customer_care:
                /* New changes: verifying the brand values both for Audi and VW*/
                if (inputParams.getBrand() == null){
                    inputParams.setBrand(brands.VW.toString());
                } 
                else if (StringUtils.isNotBlank(inputParams.getBrand())) {
                    if (inputParams.getBrand().equalsIgnoreCase("AU")) {
                        inputParams.setBrand(brands.AU.toString());
                    } else {
                        if (inputParams.getBrand().equalsIgnoreCase("VW")) {
                            inputParams.setBrand(brands.VW.toString());
                        } else errors.getErrors().add(new ValidationError(205));
                    }

                }

                errors.getErrors().addAll(customerCareValidator.validateCustomerCareFields(inputParams).getErrors());

                break;

            }
        } else {
            errors.getErrors().add(new ValidationError(1));
        }

        // we're also going to check errorPageURL and successPageURL params,
        // regardless if formHandlerType is known
        if (inputParams.getSuccessPageURL() != null) {
            List<AbstractError> tempErrors = ControllerUtils.validateUrlString(FormHandlerConstants.successPageURL,
                    inputParams.getSuccessPageURL()).getErrors();
            if (!CommonUtils.isNullOrEmpty(tempErrors)) {
                inputParams.setSuccessPageURL(null);
                errors.getErrors().addAll(tempErrors);
            }
        }

        if (inputParams.getErrorPageURL() != null) {
            List<AbstractError> tempErrors = ControllerUtils.validateUrlString(FormHandlerConstants.errorPageURL,
                    inputParams.getErrorPageURL()).getErrors();
            if (!CommonUtils.isNullOrEmpty(tempErrors)) {
                inputParams.setErrorPageURL(null);
                errors.getErrors().addAll(tempErrors);
            }
        }

        return errors;
    }

    private void setupInputParams(ExtendedParams inputParams, Map<String, String[]> parameterMap)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException,
            NoSuchMethodException {
    	
    	//1) to support FormHandler all interfaces that support Leads Submission:
    	List<Map<String, Object>> tags = new ArrayList<Map<String, Object>>();
    	for(Entry<String, String[]> paramEntry: parameterMap.entrySet()){
    		if(paramEntry.getKey().startsWith("tag-")){
    			log.debug("tag found: " + paramEntry.getKey() + " / " + paramEntry.getValue().toString());
    			Map<String, Object> myMap = new HashMap<String, Object>();
    			myMap.put("key", paramEntry.getKey());
    			myMap.put("value", paramEntry.getValue()[0]);
    			tags.add(myMap);
    			//tags.put(paramEntry.getKey(), paramEntry.getValue()[0]);
    		}
    		
    	}
    	if(!tags.isEmpty()){
    		inputParams.setTags(tags);
    	}
    	
        Field[] objFields = ExtendedParams.class.getFields();
        for (Field field : objFields) {
            // for those surveyQs and surveyAs where a param name match isnt
            // that obvious
        	/*if(field.getName().equals("tags")){
        		
        		log.debug("we have a tag parameter" );
        		if (field.getType().isArray()){
        			log.debug("we have a tag parameter that is an array type" );
        		}
        		
        	}*/
            if (field.getName().equals(FormHandlerConstants.fieldSurveyQs)
                    || field.getName().equals(FormHandlerConstants.fieldSurveyAs)) {
                String paramPrefixStr = field.getName().equals(FormHandlerConstants.fieldSurveyQs) ? FormHandlerConstants.surveyQParamPrefix
                        : FormHandlerConstants.surveyAParamPrefix;
                String tempStr = null;
                LinkedHashMap<Integer, String> surveyQAParamValue = new LinkedHashMap<Integer, String>();
                for (int i = 1; parameterMap.containsKey(tempStr = (new StringBuilder(paramPrefixStr).append(i)
                        .toString())); i++) {
                    surveyQAParamValue.put(new Integer(i), parameterMap.get(tempStr)[0]);
                }
                ExtendedParams.class.getMethod(
                        new StringBuilder("set").append(field.getName().substring(0, 1).toUpperCase())
                                .append(field.getName().substring(1)).toString(), LinkedHashMap.class).invoke(
                        inputParams, surveyQAParamValue);
            } else if (parameterMap.containsKey(field.getName())) {
                if (field.getType().isArray())
                    ExtendedParams.class.getMethod(
                            new StringBuilder("set").append(field.getName().substring(0, 1).toUpperCase())
                                    .append(field.getName().substring(1)).toString(), String[].class).invoke(
                            inputParams, (Object) parameterMap.get(field.getName()));
                else
                    ExtendedParams.class.getMethod(
                            new StringBuilder("set").append(field.getName().substring(0, 1).toUpperCase())
                                    .append(field.getName().substring(1)).toString(), String.class).invoke(inputParams,
                            parameterMap.get(field.getName())[0]);

            }
        }

        if (!inputParams.getFormHandlerType().equals("customer_care")
                && parameterMap.get(FormHandlerConstants.MODELNAME) != null
                && !CommonUtils.isNullOrBlank(parameterMap.get(FormHandlerConstants.MODELNAME)[0])) {
            inputParams.setVehicles(new ArrayList<Map<String, Object>>());
            Map<String, Object> vehicleMap = new HashMap<String, Object>();
            // public String modelName;
            if (parameterMap.get(FormHandlerConstants.MODELNAME) != null)
                vehicleMap.put(FormHandlerConstants.MODELNAME, parameterMap.get(FormHandlerConstants.MODELNAME)[0]);
            // public String modelCode;
            if (parameterMap.get(FormHandlerConstants.MODELCODE) != null)
                vehicleMap.put(FormHandlerConstants.MODELCODE, parameterMap.get(FormHandlerConstants.MODELCODE)[0]);
            // public String trim;
            if (parameterMap.get(FormHandlerConstants.TRIM) != null)
                vehicleMap.put(FormHandlerConstants.TRIM, parameterMap.get(FormHandlerConstants.TRIM)[0]);
            // public String interiorColor;
            if (parameterMap.get(FormHandlerConstants.INTERIORCOLOR) != null)
                vehicleMap.put(FormHandlerConstants.INTERIORCOLOR,
                        parameterMap.get(FormHandlerConstants.INTERIORCOLOR)[0]);
            // public String exteriorColor;
            if (parameterMap.get(FormHandlerConstants.EXTERIORCOLOR) != null)
                vehicleMap.put(FormHandlerConstants.EXTERIORCOLOR,
                        parameterMap.get(FormHandlerConstants.EXTERIORCOLOR)[0]);
            // public String year;
            if (parameterMap.get(FormHandlerConstants.YEAR) != null)
                vehicleMap.put(FormHandlerConstants.YEAR, parameterMap.get(FormHandlerConstants.YEAR)[0]);
            // public String engine;
            if (parameterMap.get(FormHandlerConstants.ENGINE) != null)
                vehicleMap.put(FormHandlerConstants.ENGINE, parameterMap.get(FormHandlerConstants.ENGINE)[0]);
            // public String msrp;
            if (parameterMap.get(FormHandlerConstants.MSRP) != null)
                vehicleMap.put(FormHandlerConstants.MSRP, parameterMap.get(FormHandlerConstants.MSRP)[0]);
            // public String msrpBase;
            if (parameterMap.get(FormHandlerConstants.MSRPBASE) != null)
                vehicleMap.put(FormHandlerConstants.MSRPBASE, parameterMap.get(FormHandlerConstants.MSRPBASE)[0]);
            // public String msrpTotal;
            if (parameterMap.get(FormHandlerConstants.MSRPTOTAL) != null)
                vehicleMap.put(FormHandlerConstants.MSRPTOTAL, parameterMap.get(FormHandlerConstants.MSRPTOTAL)[0]);
            // public String saleType;
            if (parameterMap.get(FormHandlerConstants.SALETYPE) != null)
                vehicleMap.put(FormHandlerConstants.SALETYPE, parameterMap.get(FormHandlerConstants.SALETYPE)[0]);
            // String bodyStyle;
            if (parameterMap.get(FormHandlerConstants.BODYSTYLE) != null)
                vehicleMap.put(FormHandlerConstants.BODYSTYLE, parameterMap.get(FormHandlerConstants.BODYSTYLE)[0]);
            // transmission
            if (parameterMap.get(FormHandlerConstants.TRANSMISSION) != null)
                vehicleMap.put(FormHandlerConstants.TRANSMISSION,
                        parameterMap.get(FormHandlerConstants.TRANSMISSION)[0]);
            // vin
            if (parameterMap.get(FormHandlerConstants.VIN) != null)
                vehicleMap.put(FormHandlerConstants.VIN, parameterMap.get(FormHandlerConstants.VIN)[0]);
            // options
            if (parameterMap.get(FormHandlerConstants.OPTIONS) != null)
                vehicleMap.put(FormHandlerConstants.OPTIONS, parameterMap.get(FormHandlerConstants.OPTIONS)[0]);
            // public static final String ENGINEPOWER = "enginePower";
            if (parameterMap.get(FormHandlerConstants.ENGINEPOWER) != null)
                vehicleMap.put(FormHandlerConstants.ENGINEPOWER, parameterMap.get(FormHandlerConstants.ENGINEPOWER)[0]);

            // public static final String ENGINEVERSION = "engineVersion";
            if (parameterMap.get(FormHandlerConstants.ENGINEVERSION) != null)
                vehicleMap.put(FormHandlerConstants.ENGINEVERSION,
                        parameterMap.get(FormHandlerConstants.ENGINEVERSION)[0]);

            // public static final String MODELDERIVAT = "modelDerivatID";
            if (parameterMap.get(FormHandlerConstants.MODELDERIVAT) != null)
                vehicleMap.put(FormHandlerConstants.MODELDERIVAT,
                        parameterMap.get(FormHandlerConstants.MODELDERIVAT)[0]);

            if (parameterMap.get(FormHandlerConstants.KUBAMODELID) != null)
                vehicleMap.put(FormHandlerConstants.KUBAMODELID,
                        parameterMap.get(FormHandlerConstants.KUBAMODELID)[0]);
            inputParams.getVehicles().add(vehicleMap);
        }
    }

    private Object returnResponseObjectOrRedirect(FormHandlerResponseInterface responseObj, String successAbsoluteURL,
            String errorAbsoluteURL, String emailStr) {
        StringBuilder finalUrl = null;
        if (responseObj instanceof Formhandler && !CommonUtils.isNullOrBlank(successAbsoluteURL)) {
            finalUrl = new StringBuilder(successAbsoluteURL);
            if (successAbsoluteURL.contains(FormHandlerConstants.QUESTION))
                finalUrl.append(FormHandlerConstants.AMPERSAND);
            else
                finalUrl.append(FormHandlerConstants.QUESTION);

            if (!CommonUtils.isNullOrBlank(emailStr))
                finalUrl.append(FormHandlerConstants.email).append(FormHandlerConstants.EQUAL).append(emailStr)
                        .append(FormHandlerConstants.AMPERSAND);

            if (!CommonUtils.isNullOrBlank(((Formhandler) responseObj).getLeadId()))
                finalUrl.append(FormHandlerConstants.leadid).append(FormHandlerConstants.EQUAL)
                        .append(((Formhandler) responseObj).getLeadId()).append(FormHandlerConstants.AMPERSAND);

            if (!CommonUtils.isNullOrBlank(((Formhandler) responseObj).getKubaId()))
                finalUrl.append(FormHandlerConstants.kubaid).append(FormHandlerConstants.EQUAL)
                        .append(((Formhandler) responseObj).getKubaId()).append(FormHandlerConstants.AMPERSAND);

            if (!CommonUtils.isNullOrEmpty(((Formhandler) responseObj).getErrors())) {
                for (AbstractError currError : ((Formhandler) responseObj).getErrors())
                    finalUrl.append(ControllerUtils.getAbstractErrorSubclassURIQueryString(currError));
            }

            String finalUrlStr = finalUrl.toString();
            log.debug("successPageURL given; redirect URL:" + finalUrlStr);

            return new RedirectView(finalUrlStr, false);
        } else if (responseObj instanceof Errors && !CommonUtils.isNullOrBlank(errorAbsoluteURL)) {
            String finalUrlStr = ControllerUtils.getErrorURLWithParamsQuery((Errors) responseObj, errorAbsoluteURL,
                    emailStr);
            log.debug("errorPageURL given; redirect URL:" + finalUrlStr);

            return new RedirectView(finalUrlStr, false);
        } else
            return responseObj;
    }

    public void setLeadsValidator(LeadsValidator leadsValidator) {
        this.leadsValidator = leadsValidator;
    }

    public void setKubaValidator(KubaValidator kubaValidator) {
        this.kubaValidator = kubaValidator;
    }

    public void setLeadsKubaVOMarshaller(Jaxb2Marshaller leadsKubaVOMarshaller) {
        this.marshaller = leadsKubaVOMarshaller;
    }

    public String getLeadsURL() {
        return leadsURL;
    }

    public void setLeadsURL(String leadsURL) {
        this.leadsURL = leadsURL;
    }

    public String getKubaURL() {
        return kubaURL;
    }

    public void setKubaURL(String kubaURL) {
        this.kubaURL = kubaURL;
    }

    public String getCustomerCareURL() {
        return customerCareURL;
    }

    public void setCustomerCareURL(String customerCareURL) {
        this.customerCareURL = customerCareURL;
    }

    public String getWegaURL() {
        return wegaURL;
    }

    public void setWegaURL(String wegaURL) {
        this.wegaURL = wegaURL;
    }

    public HttpClientUtil getHttpClientUtil() {
        return httpClientUtil;
    }

    public void setHttpClientUtil(HttpClientUtil httpClientUtil) {
        this.httpClientUtil = httpClientUtil;
    }

}
