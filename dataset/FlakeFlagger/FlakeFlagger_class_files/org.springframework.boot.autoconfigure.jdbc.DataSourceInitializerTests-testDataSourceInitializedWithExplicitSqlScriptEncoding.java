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

package org.springframework.boot.autoconfigure.jdbc;

import java.sql.SQLException;
import java.util.Random;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ClassUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for {@link DataSourceInitializer}.
 *
 * @author Dave Syer
 */
public class DataSourceInitializerTests {

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

	@Before
	public void init() {
		EmbeddedDatabaseConnection.override = null;
		EnvironmentTestUtils.addEnvironment(this.context,
				"spring.datasource.initialize:false",
				"spring.datasource.url:jdbc:hsqldb:mem:testdb-" + new Random().nextInt());
	}

	@Test public void testDataSourceInitializedWithExplicitSqlScriptEncoding() throws Exception{this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:true","spring.datasource.sqlScriptEncoding:UTF-8","spring.datasource.schema:" + ClassUtils.addResourcePathToPackagePath(getClass(),"encoding-schema.sql"),"spring.datasource.data:" + ClassUtils.addResourcePathToPackagePath(getClass(),"encoding-data.sql"));this.context.refresh();DataSource dataSource=this.context.getBean(DataSource.class);assertTrue(dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource);assertNotNull(dataSource);JdbcOperations template=new JdbcTemplate(dataSource);assertEquals(new Integer(2),template.queryForObject("SELECT COUNT(*) from BAR",Integer.class));assertEquals("bar",template.queryForObject("SELECT name from BAR WHERE id=1",String.class));assertEquals("ばー",template.queryForObject("SELECT name from BAR WHERE id=2",String.class));}

	@Configuration
	@EnableConfigurationProperties
	protected static class TwoDataSources {

		@Bean
		@Primary
		@ConfigurationProperties(prefix = "datasource.one")
		public DataSource oneDataSource() {
			return DataSourceBuilder.create().build();
		}

		@Bean
		@ConfigurationProperties(prefix = "datasource.two")
		public DataSource twoDataSource() {
			return DataSourceBuilder.create().build();
		}

	}

}
