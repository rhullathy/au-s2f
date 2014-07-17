package com.vw.formhandler.webspring.service;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import com.vw.formhandler.webspring.UnitTestBase;
import org.junit.Test;

import com.vw.formhandler.webspring.model.customercare.CustomerCareServiceVO;
import com.vw.formhandler.webspring.mvc.ExtendedParams;

import java.util.logging.Logger;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public class CustomerCareXmlTemplateConverterTest extends UnitTestBase {
    private static final Logger LOG = Logger.getLogger(CustomerCareXmlTemplateConverterTest.class.getName());

    private CustomerCareXmlTemplateConverter converter = new CustomerCareXmlTemplateConverter();

    @Test
    public void testConvert() throws Exception {
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
        params.setContactEmailIndicator("Y");
        params.setIsOwner("N");
        params.setRecordStatusCode("I");
        params.setDealershipFlag("0");
        params.setComments("comments");
        params.setReasonCode("ARC");

        final CustomerCareServiceVO result = converter.convert(params);

        assertNotNull(result);
        assertEquals("AU", result.getBrand());
        assertEquals("customerCare", result.getTemplateName());
        assertEquals("123456789", result.getVehicle().getVin());
        assertEquals("0", result.getVehicle().getMileage());
        assertEquals("first_name", result.getOwner().getFirstName());
        assertEquals("last_name", result.getOwner().getLastName());
        assertEquals("contact_address_1", result.getOwner().getAddressLine1());
        assertEquals("contact_address_2", result.getOwner().getAddressLine2());
        assertEquals("Detroit", result.getOwner().getCity());
        assertEquals("12345", result.getOwner().getZipCode());
        assertEquals("212-233-2345", result.getOwner().getPhone());
        assertEquals("1", result.getOwner().getBestTimeToCallCode());
        assertEquals("MI", result.getOwner().getState());
        assertEquals("US", result.getOwner().getCountryCode());
        assertEquals("abc@abc.com", result.getOwner().getEmail());
        assertEquals("Y", result.getOwner().getEmailIndicator());
        assertEquals("N", result.getOwner().getIsOwner());
        assertEquals("ARC", result.getExperience().getReasonCode());
        assertEquals("N", result.getExperience().getDealershipFlag());
        assertEquals("comments", result.getExperience().getComments());
        assertEquals("I", result.getRecordStatusCode());

        assertNotNull(result.getContact());

        assertEquals("EML", result.getContactPref());
    }

    @Override
    public Logger getLog() {
        return LOG;
    }
}
