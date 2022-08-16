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

package org.springframework.boot.bind;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.constraints.NotNull;

import org.junit.Test;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.yaml.snakeyaml.error.YAMLException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link YamlConfigurationFactory}
 *
 * @author Dave Syer
 */
public class YamlConfigurationFactoryTests {

	private Validator validator;

	private final Map<Class<?>, Map<String, String>> aliases = new HashMap<Class<?>, Map<String, String>>();

	private Foo createFoo(final String yaml) throws Exception {
		YamlConfigurationFactory<Foo> factory = new YamlConfigurationFactory<Foo>(
				Foo.class);
		factory.setYaml(yaml);
		factory.setExceptionIfInvalid(true);
		factory.setPropertyAliases(this.aliases);
		factory.setValidator(this.validator);
		factory.setMessageSource(new StaticMessageSource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	private Jee createJee(final String yaml) throws Exception {
		YamlConfigurationFactory<Jee> factory = new YamlConfigurationFactory<Jee>(
				Jee.class);
		factory.setYaml(yaml);
		factory.setExceptionIfInvalid(true);
		factory.setPropertyAliases(this.aliases);
		factory.setValidator(this.validator);
		factory.setMessageSource(new StaticMessageSource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Test public void testValidYamlLoadsWithNoErrors() throws Exception{Foo foo=createFoo("name: blah\nbar: blah");assertEquals("blah",foo.bar);}

	private static class Foo {
		@NotNull
		public String name;

		public String bar;
	}

	private static class Jee {
		public Map<Object, Object> mymap;
	}
}
