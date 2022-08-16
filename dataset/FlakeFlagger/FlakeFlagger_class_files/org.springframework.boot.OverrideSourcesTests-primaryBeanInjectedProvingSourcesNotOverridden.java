/*
 * Copyright 2012-2013 the original author or authors.
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

package org.springframework.boot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link SpringApplication} {@link SpringApplication#setSources(java.util.Set)
 * source overrides}.
 *
 * @author Dave Syer
 */
public class OverrideSourcesTests {

	@Test public void primaryBeanInjectedProvingSourcesNotOverridden(){ApplicationContext context=SpringApplication.run(new Object[]{MainConfiguration.class,TestConfiguration.class},new String[]{"--spring.main.web_environment=false","--spring.main.sources=org.springframework.boot.OverrideSourcesTests.MainConfiguration"});assertEquals("bar",context.getBean(Service.class).bean.name);}

	@Configuration
	protected static class TestConfiguration {

		@Bean
		@Primary
		public TestBean another() {
			return new TestBean("bar");
		}

	}

	@Configuration
	protected static class MainConfiguration {

		@Bean
		public TestBean first() {
			return new TestBean("foo");
		}

		@Bean
		public Service Service() {
			return new Service();
		}

	}

	protected static class Service {
		@Autowired
		private TestBean bean;
	}

	protected static class TestBean {

		private final String name;

		public TestBean(String name) {
			this.name = name;
		}

	}
}
