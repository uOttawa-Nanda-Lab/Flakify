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

package org.springframework.boot.cli.compiler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ExtendedGroovyClassLoader}.
 *
 * @author Phillip Webb
 */
public class ExtendedGroovyClassLoaderTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private ClassLoader contextClassLoader;

	private ExtendedGroovyClassLoader defaultScopeGroovyClassLoader;

	@Before
	public void setup() {
		this.contextClassLoader = Thread.currentThread().getContextClassLoader();
		this.defaultScopeGroovyClassLoader = new ExtendedGroovyClassLoader(
				GroovyCompilerScope.DEFAULT);
	}

	@Test public void filtersNonGroovy() throws Exception{this.contextClassLoader.loadClass("org.springframework.util.StringUtils");this.thrown.expect(ClassNotFoundException.class);this.defaultScopeGroovyClassLoader.loadClass("org.springframework.util.StringUtils");}

}
