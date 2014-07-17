/*
 * (c) 2012 Compuware Corporation. All Rights Reserved.
 */

package com.vw.formhandler.webspring;

import com.vw.formhandler.webspring.mvc.FormHandlerControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.vw.formhandler.webspring.dao.CustomerCaseDAOImplTest;
import com.vw.formhandler.webspring.service.CustomerCareServiceImplTest;
import com.vw.formhandler.webspring.service.CustomerCareXmlTemplateConverterTest;
import com.vw.formhandler.webspring.service.FuncCustomerCareServiceImplTest;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
@RunWith(Suite.class)
@SuiteClasses({ FormHandlerControllerTest.class,CustomerCaseDAOImplTest.class, CustomerCareXmlTemplateConverterTest.class,
        CustomerCareServiceImplTest.class })
public class AllUnitTestSuite {

}
