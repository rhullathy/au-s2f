package com.vw.formhandler.webspring.service;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.webspring.model.customercare.ContactVO;
import com.vw.formhandler.webspring.model.customercare.CustomerCareServiceVO;
import com.vw.formhandler.webspring.model.customercare.ExperienceVO;
import com.vw.formhandler.webspring.model.customercare.OwnerVO;
import com.vw.formhandler.webspring.model.leads.VehicleVO;
import com.vw.formhandler.webspring.mvc.ExtendedParams;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public class CustomerCareXmlTemplateConverter implements Converter<ExtendedParams, CustomerCareServiceVO> {
    private static final Logger LOG = Logger.getLogger(CustomerCareXmlTemplateConverter.class);

    /**
     * <ul>
     * <li>customer-care-web-case/vehicle/vin do not create vin element if VIN
     * is not present</li>
     * <li>customer-care-web-case/experience/dealership-flag static value = N</li>
     * <li>customer-care-web-case/experience/reason-code static value for VW =
     * Q45 static value for AU = ARC</li>
     * <li>customer-care-web-case/template-name static value = customerCare</li>
     * <li>customer-care-web-case/contact create empty element</li>
     * <li>customer-care-web-case/record-status-code static value = I</li>
     * <li>customer-care-web-case/owner/best-time-to-call-code static value = 1</li>
     * <li>customer-care-web-case/vehicle/vehicle-mileage static value = 0</li>
     * </ul>
     * 
     * @param inputParams
     * @return
     */
    public CustomerCareServiceVO convert(final ExtendedParams inputParams) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("inputParams: " + reflectionToString(inputParams, SHORT_PREFIX_STYLE));
        }

        final CustomerCareServiceVO result = new CustomerCareServiceVO();

        result.setBrand(inputParams.getBrand());
        // result.setTemplateName(inputParams.getTemplateName());
        result.setTemplateName("customerCare");

        final VehicleVO vehicle = new VehicleVO();
        if (StringUtils.isNotBlank(inputParams.getVin())) {
            vehicle.setVin(inputParams.getVin());
        }
        // vehicle.setMileage(inputParams.getMileage());
        vehicle.setMileage("0");
        result.setVehicle(vehicle);

        final OwnerVO owner = new OwnerVO();
        owner.setFirstName(inputParams.getContactFirstName());
        owner.setLastName(inputParams.getContactLastName());
        owner.setAddressLine1(inputParams.getContactAddress1());
        owner.setAddressLine2(inputParams.getContactAddress2());
        owner.setCity(inputParams.getContactCity());
        owner.setState(inputParams.getContactState());
        owner.setZipCode(inputParams.getContactZipCode());
        owner.setPhone(inputParams.getContactPhone());
        // owner.setBestTimeToCallCode(inputParams.getContactBestTimeToCallCode());
        owner.setBestTimeToCallCode("1");
        
        if(CommonUtils.isNullOrBlank(inputParams.getIsOwner()))
        	owner.setIsOwner("Y");
        else
        	owner.setIsOwner(inputParams.getIsOwner());
        
        owner.setCountryCode(inputParams.getCountry());
        owner.setEmail(inputParams.getContactEmail());
        owner.setEmailIndicator(inputParams.getContactEmailIndicator());

        result.setOwner(owner);

        result.setContact(new ContactVO());

        final ExperienceVO experience = new ExperienceVO();
        if (StringUtils.equalsIgnoreCase(result.getBrand(), "AU")) {
            experience.setReasonCode("ARC");
        }
        else if (StringUtils.equalsIgnoreCase(result.getBrand(), "VW")) {
            experience.setReasonCode("Q45");
        }
        else{
        	experience.setReasonCode(inputParams.getReasonCode());
        }
        experience.setComments(inputParams.getComments());
        // experience.setDealershipFlag(inputParams.getDealershipFlag());
        experience.setDealershipFlag("N");
        result.setExperience(experience);

        // result.setRecordStatusCode(inputParams.getRecordStatusCode());
        result.setRecordStatusCode("I");
        
        if (StringUtils.equalsIgnoreCase(inputParams.getContactEmailIndicator(), "Y")) {
            result.setContactPref("EML");
        } else if(StringUtils.equalsIgnoreCase(inputParams.getContactEmailIndicator(), "N")) {
            result.setContactPref("PHN");
        } else if(StringUtils.equalsIgnoreCase(inputParams.getContactEmailIndicator(), "A")) {
        	result.setContactPref("NA");
        }


        if (LOG.isDebugEnabled()) {
            LOG.debug("result: " + reflectionToString(result, SHORT_PREFIX_STYLE));
        }

        return result;
    }
}
