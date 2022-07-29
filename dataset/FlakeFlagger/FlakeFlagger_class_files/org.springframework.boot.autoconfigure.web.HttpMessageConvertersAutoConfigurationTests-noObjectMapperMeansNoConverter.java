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

package org.springframework.boot.autoconfigure.web;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link HttpMessageConvertersAutoConfiguration}.
 *
 * @author Dave Syer
 * @author Oliver Gierke
 * @author David Liu
 * @author Andy Wilkinson
 * @author Sebastien Deleuze
 */
public class HttpMessageConvertersAutoConfigurationTests {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();;

	@Test public void noObjectMapperMeansNoConverter() throws Exception{this.context.register(HttpMessageConvertersAutoConfiguration.class);this.context.refresh();assertTrue(this.context.getBeansOfType(ObjectMapper.class).isEmpty());assertTrue(this.context.getBeansOfType(MappingJackson2HttpMessageConverter.class).isEmpty());assertTrue(this.context.getBeansOfType(MappingJackson2XmlHttpMessageConverter.class).isEmpty());}

	private void assertConverterBeanExists(Class<?> type, String beanName) {
		assertEquals(1, this.context.getBeansOfType(type).size());
		List<String> beanNames = Arrays.asList(this.context.getBeanDefinitionNames());
		assertTrue(beanName + " not found in " + beanNames, beanNames.contains(beanName));
	}

	private void assertConverterBeanRegisteredWithHttpMessageConverters(Class<?> type) {

		Object converter = this.context.getBean(type);
		HttpMessageConverters converters = this.context
				.getBean(HttpMessageConverters.class);
		assertTrue(converters.getConverters().contains(converter));
	}

	@Configuration
	protected static class JacksonObjectMapperConfig {
		@Bean
		public ObjectMapper objectMapper() {
			return new ObjectMapper();
		}
	}

	@Configuration
	protected static class JacksonObjectMapperBuilderConfig {

		@Bean
		public ObjectMapper objectMapper() {
			return new ObjectMapper();
		}

		@Bean
		public Jackson2ObjectMapperBuilder builder() {
			return new Jackson2ObjectMapperBuilder();
		}
	}

	@Configuration
	protected static class JacksonConverterConfig {

		@Bean
		public MappingJackson2HttpMessageConverter customJacksonMessageConverter(
				ObjectMapper objectMapper) {
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setObjectMapper(objectMapper);
			return converter;
		}
	}

	@Configuration
	protected static class GsonConfig {

		@Bean
		public Gson gson() {
			return new Gson();
		}
	}

	@Configuration
	protected static class GsonConverterConfig {

		@Bean
		public GsonHttpMessageConverter customGsonMessageConverter(Gson gson) {
			GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
			converter.setGson(gson);
			return converter;
		}
	}

}
