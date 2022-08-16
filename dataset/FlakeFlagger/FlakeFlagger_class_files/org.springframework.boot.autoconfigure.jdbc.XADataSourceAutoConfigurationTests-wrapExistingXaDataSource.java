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

package org.springframework.boot.autoconfigure.jdbc;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.hsqldb.jdbc.pool.JDBCXADataSource;
import org.junit.Test;
import org.springframework.boot.jta.XADataSourceWrapper;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link XADataSourceAutoConfiguration}.
 *
 * @author Phillip Webb
 */
public class XADataSourceAutoConfigurationTests {

	@Test public void wrapExistingXaDataSource() throws Exception{ApplicationContext context=createContext(WrapExisting.class);context.getBean(DataSource.class);XADataSource source=context.getBean(XADataSource.class);MockXADataSourceWrapper wrapper=context.getBean(MockXADataSourceWrapper.class);assertThat(wrapper.getXaDataSource(),equalTo(source));}

	private ApplicationContext createContext(Class<?> configuration, String... env) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(context, env);
		context.register(configuration, XADataSourceAutoConfiguration.class);
		context.refresh();
		return context;
	}

	@Configuration
	static class WrapExisting {

		@Bean
		public MockXADataSourceWrapper wrapper() {
			return new MockXADataSourceWrapper();
		}

		@Bean
		public XADataSource xaDataSource() {
			return mock(XADataSource.class);
		}

	}

	@Configuration
	static class FromProperties {

		@Bean
		public MockXADataSourceWrapper wrapper() {
			return new MockXADataSourceWrapper();
		}

	}

	private static class MockXADataSourceWrapper implements XADataSourceWrapper {

		private XADataSource dataSource;

		@Override
		public DataSource wrapDataSource(XADataSource dataSource) {
			this.dataSource = dataSource;
			return mock(DataSource.class);
		}

		public XADataSource getXaDataSource() {
			return this.dataSource;
		}

	}

}
