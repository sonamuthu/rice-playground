/*
 * Copyright 2005-2006 The Kuali Foundation.
 *
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.iu.uis.eden.messaging;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.kuali.bus.test.KSBTestCase;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.kuali.rice.ksb.messaging.RemoteResourceServiceLocatorImpl;
import org.kuali.rice.ksb.messaging.resourceloading.KSBResourceLoaderFactory;
import org.kuali.rice.ksb.messaging.serviceconnectors.BusLocalConnector;
import org.kuali.rice.ksb.messaging.serviceconnectors.ServiceConnector;
import org.kuali.rice.ksb.messaging.serviceconnectors.ServiceConnectorFactory;
import org.kuali.rice.ksb.services.KSBServiceLocator;

import edu.iu.uis.eden.messaging.remotedservices.GenericTestService;
import edu.iu.uis.eden.messaging.remotedservices.TestServiceInterface;

/**
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class DevModeTest extends KSBTestCase {

	@Override
	public void setUp() throws Exception {
		System.setProperty("dev.mode", "true");
		// included in ksb-test-config.xml
		System.setProperty("additional.config.locations", "classpath:edu/iu/uis/eden/messaging/dev_mode_config.xml");
		super.setUp();
	}



	@Override
	public void tearDown() throws Exception {
	    try {
		System.clearProperty("dev.mode");
	    // included in ksb-test-config.xml
		System.clearProperty("additional.config.locations");
	    } finally {
		super.tearDown();
	    }
	}



	@Test public void testCallInDevMode() throws Exception {
		QName serviceName = new QName("KEW", "testLocalServiceFavoriteCall");
		TestServiceInterface service = (TestServiceInterface) GlobalResourceLoader.getService(serviceName);
		service.invoke();
		assertTrue("No calls to dev defined service", GenericTestService.NUM_CALLS > 0);

		RemoteResourceServiceLocatorImpl rrsl = (RemoteResourceServiceLocatorImpl)KSBResourceLoaderFactory.getRemoteResourceLocator();

		ServiceConnector serviceConnector = ServiceConnectorFactory.getServiceConnector(rrsl.getAllServices(serviceName).get(0).getServiceInfo());
		assertTrue("Not BusLocalConnector", serviceConnector instanceof BusLocalConnector);
		assertNull("Service in service definition needs to be null for async communications serialization", ((BusLocalConnector)serviceConnector).getServiceInfo().getServiceDefinition().getService());

		service = (TestServiceInterface) KSBServiceLocator.getMessageHelper().getServiceAsynchronously(serviceName);
		service.invoke();
		assertTrue("No calls to dev defined service", GenericTestService.NUM_CALLS > 1);

		assertTrue("should be no registered services", KSBServiceLocator.getIPTableService().fetchAll().size() == 0);
	}
}