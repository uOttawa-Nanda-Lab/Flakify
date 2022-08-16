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

package org.springframework.boot.autoconfigure.jmx;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jmx.config.EnableIntegrationMBeanExport;
import org.springframework.integration.monitor.IntegrationMBeanExporter;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link JmxAutoConfiguration}
 *
 * @author Christian Dupuis
 */
public class JmxAutoConfigurationTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private AnnotationConfigApplicationContext context;

	@Test public void testParentContext(){this.context=new AnnotationConfigApplicationContext();this.context.register(JmxAutoConfiguration.class);this.context.refresh();AnnotationConfigApplicationContext parent=this.context;this.context=new AnnotationConfigApplicationContext();this.context.setParent(parent);this.context.register(JmxAutoConfiguration.class);this.context.refresh();}

	@Configuration
	@EnableIntegrationMBeanExport(defaultDomain = "foo.my")
	public static class CustomJmxDomainConfiguration {

	}

	@Configuration
	public static class TestConfiguration {

		@Bean
		public Counter counter() {
			return new Counter();
		}

		@ManagedResource
		public static class Counter {

			private int counter = 0;

			@ManagedAttribute
			public int get() {
				return this.counter;
			}

			@ManagedOperation
			public void increment() {
				this.counter++;
			}

		}

	}
}
