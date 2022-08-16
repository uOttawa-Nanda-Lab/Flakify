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

	@Test public void testNullApplicationProperties(){EnvironmentTestUtils.addEnvironment(this.context,"VCAP_APPLICATION:{\"application_users\":null,\"instance_id\":\"bb7935245adf3e650dfb7c58a06e9ece\",\"instance_index\":0,\"version\":\"3464e092-1c13-462e-a47c-807c30318a50\",\"name\":\"foo\",\"uris\":[\"foo.cfapps.io\"],\"started_at\":\"2013-05-29 02:37:59 +0000\",\"started_at_timestamp\":1369795079,\"host\":\"0.0.0.0\",\"port\":61034,\"limits\":{\"mem\":128,\"disk\":1024,\"fds\":16384},\"version\":\"3464e092-1c13-462e-a47c-807c30318a50\",\"name\":\"dsyerenv\",\"uris\":[\"dsyerenv.cfapps.io\"],\"users\":[],\"start\":\"2013-05-29 02:37:59 +0000\",\"state_timestamp\":1369795079}");this.initializer.onApplicationEvent(this.event);assertNull(this.context.getEnvironment().getProperty("vcap"));}
}
