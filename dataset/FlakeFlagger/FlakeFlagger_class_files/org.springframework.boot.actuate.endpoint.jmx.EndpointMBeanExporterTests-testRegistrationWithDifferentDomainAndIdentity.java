/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.endpoint.jmx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.management.MBeanInfo;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.util.ObjectUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link EndpointMBeanExporter}
 *
 * @author Christian Dupuis
 */
public class EndpointMBeanExporterTests {

	GenericApplicationContext context = null;

	@Test public void testRegistrationWithDifferentDomainAndIdentity() throws Exception{Map<String, Object> properties=new HashMap<String, Object>();properties.put("domain","test-domain");properties.put("ensureUniqueRuntimeObjectNames",true);this.context=new GenericApplicationContext();this.context.registerBeanDefinition("endpointMbeanExporter",new RootBeanDefinition(EndpointMBeanExporter.class,null,new MutablePropertyValues(properties)));this.context.registerBeanDefinition("endpoint1",new RootBeanDefinition(TestEndpoint.class));this.context.refresh();MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);assertNotNull(mbeanExporter.getServer().getMBeanInfo(getObjectName("test-domain","endpoint1",true,this.context)));}

	private ObjectName getObjectName(String beanKey, GenericApplicationContext context)
			throws MalformedObjectNameException {
		return getObjectName("org.springframework.boot", beanKey, false, context);
	}

	private ObjectName getObjectName(String domain, String beanKey,
			boolean includeIdentity, ApplicationContext applicationContext)
			throws MalformedObjectNameException {
		if (includeIdentity) {
			return ObjectNameManager
					.getInstance(String.format("%s:type=Endpoint,name=%s,identity=%s",
							domain, beanKey, ObjectUtils
									.getIdentityHexString(applicationContext
											.getBean(beanKey))));
		}
		else {
			return ObjectNameManager.getInstance(String.format(
					"%s:type=Endpoint,name=%s", domain, beanKey));
		}
	}

	public static class TestEndpoint extends AbstractEndpoint<String> {

		public TestEndpoint() {
			super("test");
		}

		@Override
		public String invoke() {
			return "hello world";
		}
	}

}
