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

package org.springframework.boot.dependency.tools;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link PropertiesFileDependencies}.
 *
 * @author Phillip Webb
 */
public class PropertiesFileDependenciesTests {

	private PropertiesFileDependencies dependencies;

	@Before
	public void setup() throws Exception {
		this.dependencies = new PropertiesFileDependencies(getClass()
				.getResourceAsStream("external.properties"));
	}

	@Test
	public void iterate() throws Exception {
		Iterator<Dependency> iterator = this.dependencies.iterator();
		assertThat(iterator.next().toString(), equalTo("org.sample:sample01:1.0.0"));
		assertThat(iterator.next().toString(), equalTo("org.sample:sample02:1.0.0"));
		assertThat(iterator.next().toString(),
				equalTo("org.springframework.boot:spring-boot:1.0.0.BUILD-SNAPSHOT"));
		assertThat(iterator.hasNext(), equalTo(false));
	}

}
