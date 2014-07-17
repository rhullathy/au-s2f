package com.vw.formhandler.webspring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vw.formhandler.common.HttpClientUtilResponse;
import com.vw.formhandler.webspring.AbstractSpringTest;
import com.vw.formhandler.webspring.mvc.ExtendedParams;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.FormHandlerResponseInterface;
import com.vw.formhandler.webspring.mvc.response.Formhandler;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public class FuncCustomerCareServiceImplTest extends AbstractSpringTest {
    private static final Logger LOG = Logger.getLogger(FuncCustomerCareServiceImplTest.class.getName());

    @Autowired
    private CustomerCareService customerCareService;

    @Test
    public void testProcessCustomerCareRequests() throws Exception {
        final HttpClientUtilResponse response = customerCareService.processCustomerCareRequests(createExtendedParams(),
                new StreamResult(new StringWriter()), "http://localhost:8080/dealer/CustomerCare.do");

        assertNotNull(response);
        assertEquals(200, response.getStatus());

        LOG.info("response: \n" + new String(response.getResponseByteArray()));
    }

    @Test
    public void testProcessCustomerCareServiceResponse() throws Exception {
        final HttpClientUtilResponse customerCareResponse = new HttpClientUtilResponse();
        final String sample = "<customer-care-case>\n" + "<vin>WAURFAFR7AA078897</vin>\n"
                + "<case-number>71300081</case-number>\n" + "</customer-care-case>\n";
        customerCareResponse.setResponseByteArray(sample.getBytes("UTF-8"));

        final FormHandlerResponseInterface response = customerCareService.processCustomerCareServiceResponse(null,
                customerCareResponse);

        assertNotNull(response);
        assertEquals("WAURFAFR7AA078897", ((Formhandler) response).getVin());
        assertEquals("71300081", ((Formhandler) response).getCaseNumber());
    }

    @Test
    public void testProcessErrorResponse() throws Exception {
        final HttpClientUtilResponse customerCareResponse = new HttpClientUtilResponse();
        final String mockError = "<error>\n" + "<errorCode>905</errorCode>\n"
                + "<message>contactZipCode parameter should be 5 numeric characters</message>\n" + "</error>";
        customerCareResponse.setResponseByteArray(mockError.getBytes("UTF-8"));

        final FormHandlerResponseInterface response = customerCareService.processErrorResponse(null,
                customerCareResponse, true);

        assertNotNull(response);
        assertEquals(new Integer(905), ((Errors) response).getErrors().get(0).getCode());
        assertEquals("contactZipCode parameter should be 5 numeric characters", ((Errors) response).getErrors().get(0)
                .getMessage());
    }

    @Override
    public Logger getLog() {
        return LOG;
    }


    @Test
    public void testProcessCustomerCareRequestsOnLocalEnv() throws Exception {
        final HttpClientUtilResponse response = customerCareService.processCustomerCareRequests(createExtendedParamsForQaDB(),
                new StreamResult(new StringWriter()), "http://localhost:8080/dealer/CustomerCare.do");

        assertNotNull(response);
        assertEquals(200, response.getStatus());

        LOG.info("response: \n" + new String(response.getResponseByteArray()));
    }
    private ExtendedParams createExtendedParamsForQaDB() {
        final ExtendedParams params = new ExtendedParams();
        params.setBrand("AU");
        params.setTemplateName("customerCare");
        params.setVin("WVWAK73C07P119977");
        params.setMileage("0");
        params.setContactFirstName("John");
        params.setContactLastName("Bukowski");
        params.setContactAddress1("contact_address_1");
        params.setContactAddress2("contact_address_2");
        params.setContactCity("Detroit");
        params.setContactZipCode("12345");
        params.setContactPhone("212-233-2345");
        params.setContactBestTimeToCallCode("1");
        params.setContactState("MI");
        params.setCountry("US");
        params.setContactEmail("abc@abc.com");
        params.setContactEmailIndicator("N");
        params.setIsOwner("1");
        params.setRecordStatusCode("I");
        params.setDealershipFlag("0");
        params.setComments("comments");
        params.setReasonCode("ARC");

        return params;
    }

    private ExtendedParams createExtendedParams() {
        final ExtendedParams params = new ExtendedParams();
        params.setBrand("AU");
        params.setTemplateName("customerCare");
        params.setVin("123456789");
        params.setMileage("0");
        params.setContactFirstName("first_name");
        params.setContactLastName("last_name");
        params.setContactAddress1("contact_address_1");
        params.setContactAddress2("contact_address_2");
        params.setContactCity("Detroit");
        params.setContactZipCode("12345");
        params.setContactPhone("212-233-2345");
        params.setContactBestTimeToCallCode("1");
        params.setContactState("MI");
        params.setCountry("US");
        params.setContactEmail("abc@abc.com");
        params.setContactEmailIndicator("N");
        params.setIsOwner("1");
        params.setRecordStatusCode("I");
        params.setDealershipFlag("0");
        params.setComments("comments");
        params.setReasonCode("ARC");

        return params;
    }
}
