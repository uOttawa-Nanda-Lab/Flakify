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

package org.springframework.boot.autoconfigure.condition;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.internal.matchers.ThrowableMessageMatcher.hasMessage;

/**
 * Tests for {@link ConditionalOnProperty}.
 *
 * @author Maciej Walkowiak
 * @author Stephane Nicoll
 * @author Phillip Webb
 */
public class ConditionalOnPropertyTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private AnnotationConfigApplicationContext context;

	@Test public void allPropertiesAreDefined(){load(MultiplePropertiesRequiredConfiguration.class,"property1=value1","property2=value2");assertTrue(this.context.containsBean("foo"));}

	private void load(Class<?> config, String... environment) {
		this.context = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(this.context, environment);
		this.context.register(config);
		this.context.refresh();
	}

	@Configuration
	@ConditionalOnProperty(name = { "property1", "property2" })
	protected static class MultiplePropertiesRequiredConfiguration {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(prefix = "spring.", name = "the-relaxed-property")
	protected static class RelaxedPropertiesRequiredConfiguration {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(prefix = "spring", name = "property")
	protected static class RelaxedPropertiesRequiredConfigurationWithShortPrefix {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(name = "the-relaxed-property", relaxedNames = false)
	protected static class NonRelaxedPropertiesRequiredConfiguration {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	// i.e ${simple.myProperty:true}
	@ConditionalOnProperty(prefix = "simple", name = "my-property", havingValue = "true", matchIfMissing = true)
	static class EnabledIfNotConfiguredOtherwiseConfig {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	// i.e ${simple.myProperty:false}
	@ConditionalOnProperty(prefix = "simple", name = "my-property", havingValue = "true", matchIfMissing = false)
	static class DisabledIfNotConfiguredOtherwiseConfig {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(prefix = "simple", name = "my-property", havingValue = "bar")
	static class SimpleValueConfig {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(name = "simple.myProperty", havingValue = "bar", matchIfMissing = true)
	static class DefaultValueConfig {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(prefix = "simple", name = "my-property", havingValue = "bar")
	static class PrefixValueConfig {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(prefix = "simple", name = "my-property", havingValue = "bar", relaxedNames = false)
	static class StrictNameConfig {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(prefix = "simple", name = { "my-property",
			"my-another-property" }, havingValue = "bar")
	static class MultiValuesConfig {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty("some.property")
	protected static class ValueAttribute {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty
	protected static class NoNameOrValueAttribute {

		@Bean
		public String foo() {
			return "foo";
		}

	}

	@Configuration
	@ConditionalOnProperty(value = "x", name = "y")
	protected static class NameAndValueAttribute {

		@Bean
		public String foo() {
			return "foo";
		}

	}
}
