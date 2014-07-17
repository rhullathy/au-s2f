package com.vw.formhandler.webspring.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vw.formhandler.common.HttpClientUtil;
import com.vw.formhandler.common.HttpClientUtilResponse;

/**
 * The DAO layer for CustomerCare information. The class uses SOAP web service
 * invocation mechanism to save the data.
 * 
 * @author Alexandr.Lorcencov@compuware.com
 */
@Component
public class CustomerCaseDAOImpl implements CustomerCaseDAO {
    private static final Logger LOG = Logger.getLogger(CustomerCaseDAOImpl.class);

    @Autowired
    private HttpClientUtil httpClientUtil;

    public HttpClientUtilResponse saveWebCase(final String customerCareURL, final String inputData) throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Contact us service request input XML:\n" + inputData + "\n");
        }

        final HttpClientUtilResponse customerCareResponse = httpClientUtil.invokeService(customerCareURL, inputData);

        return customerCareResponse;
    }

    public void setHttpClientUtil(final HttpClientUtil httpClientUtil) {
        this.httpClientUtil = httpClientUtil;
    }
}
