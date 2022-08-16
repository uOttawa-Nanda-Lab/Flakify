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

import org.apache.http.client.config.RequestConfig;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate.CustomHttpComponentsClientHttpRequestFactory;
import org.springframework.boot.test.TestRestTemplate.HtppClientOption;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link TestRestTemplate}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 */
public class TestRestTemplateTests {

	@Test
	public void authenticated() {
		assertTrue(new TestRestTemplate("user", "password").getRequestFactory() instanceof InterceptingClientHttpRequestFactory);
	}
}
