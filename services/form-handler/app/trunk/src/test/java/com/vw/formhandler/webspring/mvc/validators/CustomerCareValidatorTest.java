package com.vw.formhandler.webspring.mvc.validators;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.vw.formhandler.webspring.UnitTestBase;
import com.vw.formhandler.webspring.mvc.ExtendedParams;
import com.vw.formhandler.webspring.mvc.response.AbstractError;
import com.vw.formhandler.webspring.mvc.response.Errors;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public class CustomerCareValidatorTest extends UnitTestBase {
    private static final Logger LOG = Logger.getLogger(CustomerCareValidatorTest.class.getName());

    private CustomerCareValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new CustomerCareValidator();
    }

    @Test
    public void testValidateCustomerCareFields() throws Exception {
        final Errors errors = validator.validateCustomerCareFields(createExtendedParamsForQaDB());

        LOG.info("" + errors.getErrors().size());

        assertEquals(0, errors.getErrors().size());

        for (AbstractError abstractError : errors.getErrors()) {
            LOG.info(abstractError.getCode() + " " + abstractError.getMessage());
        }
    }

    @Test
    public void testValidateCustomerCareFieldsWithErrors() throws Exception {
        final ExtendedParams params = createExtendedParamsForQaDB();
        params.setIsOwner("1");

        final Errors errors = validator.validateCustomerCareFields(params);

        LOG.info("" + errors.getErrors().size());

        assertEquals(1, errors.getErrors().size());
        assertEquals(new Integer(918), errors.getErrors().get(0).getCode());

        for (AbstractError abstractError : errors.getErrors()) {
            LOG.info(abstractError.getCode() + " " + abstractError.getMessage());
        }
    }

    @Override
    public Logger getLog() {
        return LOG;
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
        params.setContactPhone("2122332345");
        params.setContactBestTimeToCallCode("1");
        params.setContactState("MI");
        params.setCountry("US");
        params.setContactEmail("abc@abc.com");
        params.setContactEmailIndicator("Y");
        params.setIsOwner("Y");
        params.setRecordStatusCode("I");
        params.setDealershipFlag("0");
        params.setComments("comments");
        params.setReasonCode("ARC");

        return params;
    }

}
