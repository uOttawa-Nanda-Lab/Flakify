/*
 * Copyright 2012-2014 the original author or authors.
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

package org.springframework.boot.cloudfoundry;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link VcapApplicationListener}.
 *
 * @author Dave Syer
 */
public class VcapApplicationListenerTests {

	private final VcapApplicationListener initializer = new VcapApplicationListener();
	private final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext();
	private final ApplicationEnvironmentPreparedEvent event = new ApplicationEnvironmentPreparedEvent(
			new SpringApplication(), new String[0], this.context.getEnvironment());

	@Test public void testServicePropertiesWithoutNA(){EnvironmentTestUtils.addEnvironment(this.context,"VCAP_SERVICES:{\"rds-mysql\":[{\"name\":\"mysql\",\"label\":\"rds-mysql\",\"plan\":\"10mb\",\"credentials\":{\"name\":\"d04fb13d27d964c62b267bbba1cffb9da\",\"hostname\":\"mysql-service-public.clqg2e2w3ecf.us-east-1.rds.amazonaws.com\",\"host\":\"mysql-service-public.clqg2e2w3ecf.us-east-1.rds.amazonaws.com\",\"port\":3306,\"user\":\"urpRuqTf8Cpe6\",\"username\":\"urpRuqTf8Cpe6\",\"password\":\"pxLsGVpsC9A5S\"}}]}");this.initializer.onApplicationEvent(this.event);assertEquals("mysql",this.context.getEnvironment().getProperty("vcap.services.mysql.name"));}
}
