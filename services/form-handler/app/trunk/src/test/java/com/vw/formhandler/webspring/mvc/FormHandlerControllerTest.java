package com.vw.formhandler.webspring.mvc;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.servlet.ServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.vw.formhandler.webspring.UnitTestBase;
import com.vw.formhandler.webspring.common.InvalidRequestException;
import com.vw.formhandler.webspring.mvc.response.AbstractError;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.validators.LeadsValidator;

public class FormHandlerControllerTest extends UnitTestBase {
    private static final Logger LOG = Logger.getLogger(FormHandlerControllerTest.class.getName());

    private static final String CUSTOMER_CARE_JSON = "/CustomerCareRequiredParams.json";
    private static final String LEAD_JSON = "/LeadRequiredParams.json";
    private static final String TAGS_JSON = "/LeadRequiredParamsWithTags.json";
    private static final String TAGS_B_JSON = "/LeadRequiredParamsWithTagsBigerThan.json";
    private static final String CUSTOMER_CARE_JSON_PRODUCTION_BUG = "/CustomerCareRequiredParamsProductionBug.json";

    private FormHandlerController controller = new FormHandlerController();

    @Test
    public void testSetupInputParamsFromJson() throws IOException, IllegalArgumentException, SecurityException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final ExtendedParams inputParams = new ExtendedParams();
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> jsonObject = mapper.readValue(this.getClass().getResourceAsStream(LEAD_JSON),
                new TypeReference<Map<String, Object>>() {
                });

        controller.setupInputParamsFromJson(inputParams, jsonObject);

        assertEquals("VW", inputParams.getBrand());
        assertEquals("quote", inputParams.getRequestType());
        assertEquals("request", inputParams.getSubType());
        assertNotNull(inputParams.getVehicles());
        assertEquals("Jetta", inputParams.getVehicles().get(0).get("modelName"));
        assertEquals("SE", inputParams.getVehicles().get(0).get("modelCode"));
    }
    
    @Test
    public void testSetupInputParamsWithTagsFromJson() throws IOException, IllegalArgumentException, SecurityException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final ExtendedParams inputParams = new ExtendedParams();
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> jsonObject = mapper.readValue(this.getClass().getResourceAsStream(TAGS_JSON),
                new TypeReference<Map<String, Object>>() {
                });

        controller.setupInputParamsFromJson(inputParams, jsonObject);

        assertEquals("VW", inputParams.getBrand());
        assertEquals("quote", inputParams.getRequestType());
        assertEquals("request", inputParams.getSubType());
        assertNotNull(inputParams.getVehicles());
        assertEquals("Jetta", inputParams.getVehicles().get(0).get("modelName"));
        assertEquals("SE", inputParams.getVehicles().get(0).get("modelCode"));
        //assertEquals("SE", inputParams.getTags().get(0).get("value"));
        Map<String, Object> z = inputParams.getTags().get(0);
        //assertEquals("", inputParams.getTags().get(0).get("Omniture"));
        assertEquals("Omniture", inputParams.getTags().get(0).get("key"));
        assertEquals("jfd93jdf9dj", inputParams.getTags().get(0).get("value"));
        //assertEquals("sd871lkasndf8", inputParams.getTags().get("BrightTag"));
    }
    
    @Test(expected=com.vw.formhandler.webspring.common.InvalidRequestException.class)
    public void testSetupInputParamsWithTagsFromJsonBigerThan() throws Throwable {
        final ExtendedParams inputParams = new ExtendedParams();
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> jsonObject = mapper.readValue(this.getClass().getResourceAsStream(TAGS_B_JSON),
                new TypeReference<Map<String, Object>>() {
                });

        controller.setupInputParamsFromJson(inputParams, jsonObject);
        LeadsValidator leadsValidator = new LeadsValidator();
		controller.setLeadsValidator(leadsValidator );
        ServletRequest request = mock(ServletRequest.class);
        
        InputStream is = getClass().getResourceAsStream(TAGS_B_JSON);
        Scanner scan = new Scanner(is);
        StringBuilder content = new StringBuilder();
        //ArrayList<String> strings = new ArrayList<String>();
        while(scan.hasNextLine())
        {
        	content.append(scan.nextLine());
        }
        when(request.getParameter("json")).thenReturn(content.toString());
        try{
        	controller.formHandlerMethod1("leads", request );
        }catch(InvalidRequestException e){
        	Errors errors = e.getErrors();
        	List<AbstractError> es = errors.getErrors();
        	AbstractError a = es.get(0);
        	assertEquals(a.getCode(), new Integer(265));
        	a = es.get(1);
        	assertEquals(a.getCode(), new Integer(266));
        	throw e;
        }

    }

    @Test
    public void testSetupInputParamsFromJsonCustomerCare() throws IOException, IllegalArgumentException,
            SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final ExtendedParams inputParams = new ExtendedParams();
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> jsonObject = mapper.readValue(
                this.getClass().getResourceAsStream(CUSTOMER_CARE_JSON), new TypeReference<Map<String, Object>>() {
                });

        controller.setupInputParamsFromJson(inputParams, jsonObject);

        assertNotNull(inputParams);
        assertEquals("AU", inputParams.getBrand());
        assertEquals("123456789", inputParams.getVin());
        assertEquals("first_name", inputParams.getContactFirstName());
        assertEquals("last_name", inputParams.getContactLastName());
        assertEquals("contact_address_1", inputParams.getContactAddress1());
        assertEquals("contact_address_2", inputParams.getContactAddress2());
        assertEquals("Detroit", inputParams.getContactCity());
        assertEquals("12345", inputParams.getContactZipCode());
        assertEquals("212-233-2345", inputParams.getContactPhone());
        assertEquals("MI", inputParams.getContactState());
        assertEquals("US", inputParams.getCountry());
        assertEquals("abc@abc.com", inputParams.getContactEmail());
        assertEquals("Y", inputParams.getContactEmailIndicator());
        assertEquals("Y", inputParams.getIsOwner());
        assertEquals("comments", inputParams.getComments());
    }
    
    @Test
    public void testSetupInputParamsFromJsonCustomerCareProductionBug() throws IOException, IllegalArgumentException,
            SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final ExtendedParams inputParams = new ExtendedParams();
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> jsonObject = mapper.readValue(
                this.getClass().getResourceAsStream(CUSTOMER_CARE_JSON_PRODUCTION_BUG), new TypeReference<Map<String, Object>>() {
                });

        controller.setupInputParamsFromJson(inputParams, jsonObject);

        assertNotNull(inputParams);
        /*assertEquals("AU", inputParams.getBrand());
        assertEquals("123456789", inputParams.getVin());
        assertEquals("first_name", inputParams.getContactFirstName());
        assertEquals("last_name", inputParams.getContactLastName());
        assertEquals("contact_address_1", inputParams.getContactAddress1());
        assertEquals("contact_address_2", inputParams.getContactAddress2());
        assertEquals("Detroit", inputParams.getContactCity());
        assertEquals("12345", inputParams.getContactZipCode());
        assertEquals("212-233-2345", inputParams.getContactPhone());
        assertEquals("MI", inputParams.getContactState());
        assertEquals("US", inputParams.getCountry());
        assertEquals("abc@abc.com", inputParams.getContactEmail());
        assertEquals("Y", inputParams.getContactEmailIndicator());
        assertEquals("Y", inputParams.getIsOwner());
        assertEquals("comments", inputParams.getComments());
        */
    }

    @Override
    public Logger getLog() {
        return LOG;
    }
}
