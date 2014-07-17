/*
 * (c) 2012 Compuware Corporation. All Rights Reserved.
 */

package com.vw.formhandler.webspring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alexandr.Lorcencov@compuware.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:formhandler-spring-web-servlet.xml","classpath:serviceContext.xml"  })
public abstract class AbstractSpringTest extends UnitTestBase {
}
