package com.vw.formhandler.webspring.dao;

import com.vw.formhandler.common.HttpClientUtilResponse;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
public interface CustomerCaseDAO {
    public HttpClientUtilResponse saveWebCase(final String customerCareURL, final String inputData) throws Exception;
}
