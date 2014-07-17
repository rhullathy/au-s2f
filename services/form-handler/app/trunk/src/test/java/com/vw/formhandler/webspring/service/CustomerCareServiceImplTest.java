package com.vw.formhandler.webspring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.vw.formhandler.common.HttpClientUtilResponse;
import com.vw.formhandler.webspring.dao.CustomerCaseDAOImpl;
import com.vw.formhandler.webspring.model.customercare.CustomerCareServiceResponseVO;
import com.vw.formhandler.webspring.mvc.ExtendedParams;
import com.vw.formhandler.webspring.mvc.model.ServiceResponseException;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.FormHandlerResponseInterface;
import com.vw.formhandler.webspring.mvc.response.Formhandler;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public class CustomerCareServiceImplTest {

    private static final Logger LOG = Logger.getLogger(CustomerCareServiceImplTest.class.getName());

    private CustomerCaseDAOImpl customerCaseDAO;
    private CustomerCareServiceImpl customerCareService;
    private Jaxb2Marshaller marshaller;

    @Before
    public void setUp() throws Exception {
        customerCaseDAO = PowerMockito.mock(CustomerCaseDAOImpl.class);
        marshaller = PowerMockito.mock(Jaxb2Marshaller.class);

        customerCareService = new CustomerCareServiceImpl();
        customerCareService.setCustomerCaseDAO(customerCaseDAO);
        customerCareService.setMarshaller(marshaller);
    }

    @Test
    public void testProcessCustomerCareRequests() throws Exception {
        final HttpClientUtilResponse mockResponse = new HttpClientUtilResponse();
        mockResponse.setStatus(403);
        PowerMockito.when(
                customerCaseDAO.saveWebCase(Mockito.eq("http://localhost:8080/dealer/CustomerCare.do"),
                        Mockito.anyString())).thenReturn(mockResponse);

        final HttpClientUtilResponse result = customerCareService.processCustomerCareRequests(createExtendedParams(),
                new StreamResult(new StringWriter()), "http://localhost:8080/dealer/CustomerCare.do");

        assertNotNull(result);
        assertEquals(403, result.getStatus());
    }

    @Test
    public void testProcessCustomerCareServiceResponse() throws Exception {
        final HttpClientUtilResponse mockResponse = new HttpClientUtilResponse();
        final String sample = "<customer-care-case>\n" + "<vin>WAURFAFR7AA078877</vin>\n"
                + "<case-number>71300077</case-number>\n" + "</customer-care-case>\n";
        mockResponse.setResponseByteArray(sample.getBytes("UTF-8"));

        final CustomerCareServiceResponseVO mockMarshallResponse = new CustomerCareServiceResponseVO();
        mockMarshallResponse.setVin("WAURFAFR7AA078877");
        mockMarshallResponse.setCaseNumber("71300077");

        PowerMockito.when(marshaller.unmarshal(Mockito.any(Source.class))).thenReturn(mockMarshallResponse);

        final FormHandlerResponseInterface result = customerCareService.processCustomerCareServiceResponse(null,
                mockResponse);

        assertNotNull(result);
        assertEquals("WAURFAFR7AA078877", ((Formhandler) result).getVin());
        assertEquals("71300077", ((Formhandler) result).getCaseNumber());
    }

    @Test
    public void testProcessErrorResponse() throws Exception {
        final HttpClientUtilResponse response = new HttpClientUtilResponse();
        final String mockError = "<error>\n" + "<errorCode>905</errorCode>\n"
                + "<message>contactZipCode parameter should be 5 numeric characters</message>\n" + "</error>";
        response.setResponseByteArray(mockError.getBytes("UTF-8"));

        final ServiceResponseException mockServiceResponseException = new ServiceResponseException();
        mockServiceResponseException.setErrorCode("905");
        mockServiceResponseException.setMessage("contactZipCode parameter should be 5 numeric characters");

        PowerMockito.when(marshaller.unmarshal(Mockito.any(Source.class))).thenReturn(mockServiceResponseException);

        final FormHandlerResponseInterface result = customerCareService.processErrorResponse(null, response, true);

        assertNotNull(result);
        assertEquals(new Integer(905), ((Errors) result).getErrors().get(0).getCode());
        assertEquals("contactZipCode parameter should be 5 numeric characters", ((Errors) result).getErrors().get(0)
                .getMessage());
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
        params.setContactEmailIndicator("E");
        params.setIsOwner("1");
        params.setRecordStatusCode("I");
        params.setDealershipFlag("0");
        params.setComments("comments");
        params.setReasonCode("ARC");

        return params;
    }
}
