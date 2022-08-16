/*
 * Copyright 2013-2014 the original author or authors.
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

import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfigurationPropertiesReportEndpointParentTests {

	private AnnotationConfigApplicationContext context;

	@Test
	public void testInvoke() throws Exception {
		AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
		parent.register(Parent.class);
		parent.refresh();
		this.context = new AnnotationConfigApplicationContext();
		this.context.setParent(parent);
		this.context.register(Config.class);
		this.context.refresh();
		ConfigurationPropertiesReportEndpoint endpoint = this.context
				.getBean(ConfigurationPropertiesReportEndpoint.class);
		Map<String, Object> result = endpoint.invoke();
		assertTrue(result.containsKey("parent"));
		assertEquals(3, result.size()); // the endpoint, the test props and the parent
		// System.err.println(result);
	}

	@Configuration
	@EnableConfigurationProperties
	public static class Parent {
		@Bean
		public TestProperties testProperties() {
			return new TestProperties();
		}
	}

	@Configuration
	@EnableConfigurationProperties
	public static class Config {

		@Bean
		public ConfigurationPropertiesReportEndpoint endpoint() {
			return new ConfigurationPropertiesReportEndpoint();
		}

		@Bean
		public TestProperties testProperties() {
			return new TestProperties();
		}

	}

	@ConfigurationProperties(prefix = "test")
	public static class TestProperties {

		private String myTestProperty = "654321";

		public String getMyTestProperty() {
			return this.myTestProperty;
		}

		public void setMyTestProperty(String myTestProperty) {
			this.myTestProperty = myTestProperty;
		}

	}
}
