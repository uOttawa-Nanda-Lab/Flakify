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

	@Test public void testApplicationUris(){EnvironmentTestUtils.addEnvironment(this.context,"VCAP_APPLICATION:{\"instance_id\":\"bb7935245adf3e650dfb7c58a06e9ece\",\"instance_index\":0,\"uris\":[\"foo.cfapps.io\"]}");this.initializer.onApplicationEvent(this.event);assertEquals("foo.cfapps.io",this.context.getEnvironment().getProperty("vcap.application.uris[0]"));}
}
