package com.vw.formhandler.webspring.service;

import java.io.ByteArrayInputStream;
import java.util.Stack;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vw.formhandler.common.CommonUtils;
import com.vw.formhandler.common.HttpClientUtilResponse;
import com.vw.formhandler.webspring.dao.CustomerCaseDAO;
import com.vw.formhandler.webspring.model.customercare.CustomerCareServiceResponseVO;
import com.vw.formhandler.webspring.model.customercare.CustomerCareServiceVO;
import com.vw.formhandler.webspring.mvc.ExtendedParams;
import com.vw.formhandler.webspring.mvc.model.ServiceResponseException;
import com.vw.formhandler.webspring.mvc.response.AbstractServiceResponseErrorVO;
import com.vw.formhandler.webspring.mvc.response.Errors;
import com.vw.formhandler.webspring.mvc.response.FormHandlerResponseInterface;
import com.vw.formhandler.webspring.mvc.response.Formhandler;
import com.vw.formhandler.webspring.mvc.response.kuba.KubaServiceResponseErrorVO;
import com.vw.formhandler.webspring.mvc.response.leads.LeadsServiceResponseErrorVO;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
@Service
public class CustomerCareServiceImpl implements CustomerCareService {
    private static final Logger log = Logger.getLogger(CustomerCareServiceImpl.class);

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    private CustomerCaseDAO customerCaseDAO;


    /**
     * 
     * @param inputParams
     * @param result
     * @param customerCareURL
     * @return
     * @throws Exception
     */
    @Transactional
    public HttpClientUtilResponse processCustomerCareRequests(final ExtendedParams inputParams,
            final StreamResult result, final String customerCareURL) throws Exception {

        final CustomerCareServiceVO customerCareServiceVO = new CustomerCareXmlTemplateConverter().convert(inputParams);

        marshaller.marshal(customerCareServiceVO, result);

        String tempStr = result.getWriter().toString();

        final HttpClientUtilResponse customerCareResponse = customerCaseDAO.saveWebCase(customerCareURL, tempStr);

        return customerCareResponse;
    }

    /**
     * 
     * @param response
     * @param customerCareServiceResponse
     * @return
     */
    @Transactional
    public FormHandlerResponseInterface processCustomerCareServiceResponse(FormHandlerResponseInterface response,
            HttpClientUtilResponse customerCareServiceResponse) {

        if (log.isDebugEnabled()) {
            log.debug("CustomerCareServiceResponse: " + new String(customerCareServiceResponse.getResponseByteArray()));
        }

        final Object unMarshallResponse = marshaller.unmarshal(new StreamSource(new ByteArrayInputStream(
                customerCareServiceResponse.getResponseByteArray())));

        if (unMarshallResponse instanceof CustomerCareServiceResponseVO) {
            response = new Formhandler();
            CustomerCareServiceResponseVO tempCustomerCareServiceResponseVO = (CustomerCareServiceResponseVO) unMarshallResponse;
            ((Formhandler) response).setCaseNumber(tempCustomerCareServiceResponseVO.getCaseNumber().toString());
            if(tempCustomerCareServiceResponseVO.getVin()!=null)
            {
            	((Formhandler) response).setVin(tempCustomerCareServiceResponseVO.getVin().toString());
            }
            if (!CommonUtils.isNullOrEmpty(customerCareServiceResponse.getPreviousTryResponsesStack())
                    && customerCareServiceResponse.getPreviousTryResponsesStack().size() > 0) {
                final Errors tempErrors = getErrorsFromPreviousResponsesStack(
                        customerCareServiceResponse.getPreviousTryResponsesStack(), true);
                ((Formhandler) response).getErrors().addAll(tempErrors.getErrors());
            }
        } else {
            boolean isLeadsServiceResponse = false;
            response = processErrorResponse(response, customerCareServiceResponse, isLeadsServiceResponse);
        }

        if (log.isDebugEnabled()) {
            log.debug("result response: " + response);
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
     * 
     * @param response
     * @param serviceResponse
     * @param isLeadsServiceResponse
     * @return
     */
    public FormHandlerResponseInterface processErrorResponse(FormHandlerResponseInterface response,
            HttpClientUtilResponse serviceResponse, boolean isLeadsServiceResponse) {
        Errors tempErrors;
        Errors temp2Errors;
        AbstractServiceResponseErrorVO tempError;
        ServiceResponseException tempServiceResponseException;
        if (response == null)
            response = new Errors();
        else if (response instanceof Errors) {
            tempErrors = (Errors) response;
            response = new Errors();
            ((Errors) response).getErrors().addAll(tempErrors.getErrors());
        }

        tempErrors = new Errors();
        tempServiceResponseException = (ServiceResponseException) marshaller.unmarshal(new StreamSource(
                new ByteArrayInputStream(serviceResponse.getResponseByteArray())));

        tempError = constructServiceResponseErrorFromServiceResponseException(tempServiceResponseException,
                serviceResponse.getStatus(), false);
        tempErrors.getErrors().add(tempError);
        if (!CommonUtils.isNullOrEmpty(serviceResponse.getPreviousTryResponsesStack())
                && serviceResponse.getPreviousTryResponsesStack().size() > 0) {
            temp2Errors = getErrorsFromPreviousResponsesStack(serviceResponse.getPreviousTryResponsesStack(),
                    isLeadsServiceResponse);
            tempErrors.getErrors().addAll(temp2Errors.getErrors());
        }

        if (response instanceof Errors)
            ((Errors) response).getErrors().addAll(tempErrors.getErrors());
        else if (response instanceof Formhandler)
            ((Formhandler) response).getErrors().addAll(tempErrors.getErrors());

        return response;
    }

    public void setMarshaller(final Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setCustomerCaseDAO(final CustomerCaseDAO customerCaseDAO) {
        this.customerCaseDAO = customerCaseDAO;
    }
}
