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

package org.springframework.boot.logging.java;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.logging.impl.Jdk14Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.logging.AbstractLoggingSystemTests;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.OutputCapture;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link JavaLoggingSystem}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 */
public class JavaLoggerSystemTests extends AbstractLoggingSystemTests {

	private static final FileFilter SPRING_LOG_FILTER = new FileFilter() {

		@Override
		public boolean accept(File pathname) {
			return pathname.getName().startsWith("spring.log");
		}

	};

	private final JavaLoggingSystem loggingSystem = new JavaLoggingSystem(getClass()
			.getClassLoader());

	@Rule
	public OutputCapture output = new OutputCapture();

	private Jdk14Logger logger;

	private Locale defaultLocale;

	@Before
	public void init() throws SecurityException, IOException {
		this.defaultLocale = Locale.getDefault();
		Locale.setDefault(Locale.ENGLISH);
		this.logger = new Jdk14Logger(getClass().getName());
	}

	@Test public void testCustomFormatter() throws Exception{this.loggingSystem.beforeInitialize();this.loggingSystem.initialize(null,null);this.logger.info("Hello world");String output=this.output.toString().trim();assertTrue("Wrong output:\n" + output,output.contains("Hello world"));assertTrue("Wrong output:\n" + output,output.contains("???? INFO ["));}

}
