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

package org.springframework.boot.autoconfigure.data.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.TestAutoConfigurationPackage;
import org.springframework.boot.autoconfigure.data.alt.solr.CitySolrRepository;
import org.springframework.boot.autoconfigure.data.empty.EmptyDataPackage;
import org.springframework.boot.autoconfigure.data.solr.city.City;
import org.springframework.boot.autoconfigure.data.solr.city.CityRepository;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link SolrRepositoriesAutoConfiguration}.
 *
 * @author Christoph Strobl
 * @author Oliver Gierke
 */
public class SolrRepositoriesAutoConfigurationTests {

	private AnnotationConfigApplicationContext context;

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void autoConfigurationShouldNotKickInEvenIfManualConfigDidNotCreateAnyRepositories() {

		initContext(SortOfInvalidCustomConfiguration.class);
		this.context.getBean(CityRepository.class);
	}

	private void initContext(Class<?> configClass) {

		this.context = new AnnotationConfigApplicationContext();
		this.context.register(configClass, SolrAutoConfiguration.class,
				SolrRepositoriesAutoConfiguration.class,
				PropertyPlaceholderAutoConfiguration.class);
		this.context.refresh();
	}

	@Configuration
	@TestAutoConfigurationPackage(City.class)
	static class TestConfiguration {

	}

	@Configuration
	@TestAutoConfigurationPackage(EmptyDataPackage.class)
	static class EmptyConfiguration {

	}

	@Configuration
	@TestAutoConfigurationPackage(SolrRepositoriesAutoConfigurationTests.class)
	@EnableSolrRepositories(basePackageClasses = CitySolrRepository.class, multicoreSupport = true)
	protected static class CustomizedConfiguration {

	}

	@Configuration
	@TestAutoConfigurationPackage(SolrRepositoriesAutoConfigurationTests.class)
	// To not find any repositories
	@EnableSolrRepositories("foo.bar")
	protected static class SortOfInvalidCustomConfiguration {
	}
}
