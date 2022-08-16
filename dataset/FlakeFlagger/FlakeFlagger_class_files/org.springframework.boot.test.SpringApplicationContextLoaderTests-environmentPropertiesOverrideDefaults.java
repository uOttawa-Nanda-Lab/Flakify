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

package org.springframework.boot.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link SpringApplicationContextLoader}
 *
 * @author Stephane Nicoll
 */
public class SpringApplicationContextLoaderTests {

	private final SpringApplicationContextLoader loader = new SpringApplicationContextLoader();

	@Test public void environmentPropertiesOverrideDefaults() throws Exception{Map<String, Object> config=getEnvironmentProperties(OverrideConfig.class);assertKey(config,"server.port","2345");}

	private Map<String, Object> getEnvironmentProperties(Class<?> testClass)
			throws Exception {
		TestContext context = new ExposedTestContextManager(testClass)
				.getExposedTestContext();
		new IntegrationTestPropertiesListener().prepareTestInstance(context);
		MergedContextConfiguration config = (MergedContextConfiguration) ReflectionTestUtils
				.getField(context, "mergedContextConfiguration");
		return this.loader.extractEnvironmentProperties(config
				.getPropertySourceProperties());
	}

	private void assertKey(Map<String, Object> actual, String key, Object value) {
		assertTrue("Key '" + key + "' not found", actual.containsKey(key));
		assertEquals(value, actual.get(key));
	}

	@IntegrationTest({ "key=myValue", "anotherKey:anotherValue" })
	static class SimpleConfig {
	}

	@IntegrationTest({ "server.port=2345" })
	static class OverrideConfig {
	}

	@IntegrationTest({ "key=myValue", "otherKey=otherValue" })
	static class AppendConfig {
	}

	@IntegrationTest({ "key=my=Value", "anotherKey:another:Value" })
	static class SameSeparatorInValue {
	}

	@IntegrationTest({ "key=my:Value", "anotherKey:another=Value" })
	static class AnotherSeparatorInValue {
	}

	/**
	 * {@link TestContextManager} which exposes the {@link TestContext}.
	 */
	private static class ExposedTestContextManager extends TestContextManager {

		public ExposedTestContextManager(Class<?> testClass) {
			super(testClass);
		}

		public final TestContext getExposedTestContext() {
			return super.getTestContext();
		}

	}

}
