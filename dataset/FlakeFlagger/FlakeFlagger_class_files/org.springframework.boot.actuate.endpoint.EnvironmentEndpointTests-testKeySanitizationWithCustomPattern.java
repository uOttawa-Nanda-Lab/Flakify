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

package org.springframework.boot.actuate.endpoint;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.MapPropertySource;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link EnvironmentEndpoint}.
 *
 * @author Phillip Webb
 * @author Christian Dupuis
 */
public class EnvironmentEndpointTests extends AbstractEndpointTests<EnvironmentEndpoint> {

	@SuppressWarnings("unchecked")
	@Test
	public void testKeySanitizationWithCustomPattern() throws Exception {
		System.setProperty("dbPassword", "123456");
		System.setProperty("apiKey", "123456");
		EnvironmentEndpoint report = getEndpointBean();
		report.setKeysToSanitize(".*pass.*");
		Map<String, Object> env = report.invoke();
		assertEquals("******",
				((Map<String, Object>) env.get("systemProperties")).get("dbPassword"));
		assertEquals("123456",
				((Map<String, Object>) env.get("systemProperties")).get("apiKey"));
	}

	@Configuration
	@EnableConfigurationProperties
	public static class Config {

		@Bean
		public EnvironmentEndpoint endpoint() {
			return new EnvironmentEndpoint();
		}

	}
}
