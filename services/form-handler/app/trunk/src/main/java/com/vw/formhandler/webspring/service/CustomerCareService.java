package com.vw.formhandler.webspring.service;

import javax.xml.transform.stream.StreamResult;

import com.vw.formhandler.common.HttpClientUtilResponse;
import com.vw.formhandler.webspring.mvc.ExtendedParams;
import com.vw.formhandler.webspring.mvc.response.FormHandlerResponseInterface;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public interface CustomerCareService {
    HttpClientUtilResponse processCustomerCareRequests(ExtendedParams inputParams, StreamResult result,
            String customerCareURL) throws Exception;

    FormHandlerResponseInterface processCustomerCareServiceResponse(FormHandlerResponseInterface response,
            HttpClientUtilResponse customerCareServiceResponse);

    FormHandlerResponseInterface processErrorResponse(FormHandlerResponseInterface response,
            HttpClientUtilResponse serviceResponse, boolean isLeadsServiceResponse);
}
