/*
 * Copyright 2010-2014 the original author or authors.
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

package org.springframework.boot.context.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener.ConfigurationPropertySources;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.EnumerableCompositePropertySource;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ConfigFileApplicationListener}.
 *
 * @author Phillip Webb
 * @author Dave Syer
 */
public class ConfigFileApplicationListenerTests {

	private final StandardEnvironment environment = new StandardEnvironment();

	private final ApplicationEnvironmentPreparedEvent event = new ApplicationEnvironmentPreparedEvent(
			new SpringApplication(), new String[0], this.environment);

	private final ConfigFileApplicationListener initializer = new ConfigFileApplicationListener();

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test public void loadTwoOfThreePropertiesFile() throws Exception{EnvironmentTestUtils.addEnvironment(this.environment,"spring.config.location:" + "classpath:application.properties," + "classpath:testproperties.properties," + "classpath:nonexistent.properties");this.initializer.onApplicationEvent(this.event);String property=this.environment.getProperty("the.property");assertThat(property,equalTo("frompropertiesfile"));}

	private static Matcher<? super ConfigurableEnvironment> containsPropertySource(
			final String sourceName) {
		return new TypeSafeDiagnosingMatcher<ConfigurableEnvironment>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("environment containing property source ")
						.appendValue(sourceName);
			}

			@Override
			protected boolean matchesSafely(ConfigurableEnvironment item,
					Description mismatchDescription) {
				MutablePropertySources sources = new MutablePropertySources(
						item.getPropertySources());
				ConfigurationPropertySources.finishAndRelocate(sources);
				mismatchDescription.appendText("Not matched against: ").appendValue(
						sources);
				return sources.contains(sourceName);
			}
		};
	}

	private static Matcher<? super ConfigurableEnvironment> acceptsProfiles(
			final String... profiles) {
		return new TypeSafeDiagnosingMatcher<ConfigurableEnvironment>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("environment accepting profiles ").appendValue(
						profiles);
			}

			@Override
			protected boolean matchesSafely(ConfigurableEnvironment item,
					Description mismatchDescription) {
				mismatchDescription.appendText("Not matched against: ").appendValue(
						item.getActiveProfiles());
				return item.acceptsProfiles(profiles);
			}
		};
	}

	@Configuration
	protected static class Config {

	}

	@Configuration
	@PropertySource("classpath:/specificlocation.properties")
	protected static class WithPropertySource {

	}

	@Configuration
	@PropertySource("classpath:/${source.location}.properties")
	protected static class WithPropertySourcePlaceholders {

	}

	@Configuration
	@PropertySource(value = "classpath:/specificlocation.properties", name = "foo")
	protected static class WithPropertySourceAndName {

	}

	@Configuration
	@PropertySource("classpath:/enableprofile.properties")
	protected static class WithPropertySourceInProfile {

	}

	@Configuration
	@PropertySource("classpath:/enableprofile-myprofile.properties")
	@Profile("myprofile")
	protected static class WithPropertySourceAndProfile {

	}

	@Configuration
	@PropertySource({ "classpath:/specificlocation.properties",
			"classpath:/moreproperties.properties" })
	protected static class WithPropertySourceMultipleLocations {

	}

	@Configuration
	@PropertySource(value = { "classpath:/specificlocation.properties",
			"classpath:/moreproperties.properties" }, name = "foo")
	protected static class WithPropertySourceMultipleLocationsAndName {

	}

}
