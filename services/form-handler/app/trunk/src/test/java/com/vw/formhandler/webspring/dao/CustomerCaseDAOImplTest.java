package com.vw.formhandler.webspring.dao;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.vw.formhandler.common.HttpClientUtil;
import com.vw.formhandler.common.HttpClientUtilResponse;
import com.vw.formhandler.webspring.UnitTestBase;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public class CustomerCaseDAOImplTest extends UnitTestBase {
    private static final Logger LOG = Logger.getLogger(CustomerCaseDAOImplTest.class.getName());

    private CustomerCaseDAOImpl customerCaseDAO;
    private HttpClientUtil httpClientUtil;

    @Before
    public void setUp() {
        httpClientUtil = mock(HttpClientUtil.class);
        customerCaseDAO = new CustomerCaseDAOImpl();
        customerCaseDAO.setHttpClientUtil(httpClientUtil);
    }

    @Test
    public void testSaveWebCase() throws Exception {
        final HttpClientUtilResponse mockResponse = new HttpClientUtilResponse();
        when(httpClientUtil.invokeService("CustomerCare.do", "testInputData")).thenReturn(mockResponse);

        final HttpClientUtilResponse result = customerCaseDAO.saveWebCase("CustomerCare.do", "testInputData");

        assertEquals(mockResponse, result);
        verify(httpClientUtil, times(1)).invokeService("CustomerCare.do", "testInputData");
    }

    @Test(expected = Exception.class)
    public void testSaveWebCaseWithException() throws Exception {
        final HttpClientUtilResponse mockResponse = new HttpClientUtilResponse();
        when(httpClientUtil.invokeService("CustomerCareWrongLink.do", "errorInputData")).thenThrow(new Exception("test"));

        final HttpClientUtilResponse result = customerCaseDAO.saveWebCase("CustomerCareWrongLink.do", "errorInputData");

        assertEquals(mockResponse, result);
        verify(httpClientUtil, times(1)).invokeService("CustomerCareWrongLink.do", "errorInputData");
    }
    @Override
    public Logger getLog() {
        return LOG;
    }
}
