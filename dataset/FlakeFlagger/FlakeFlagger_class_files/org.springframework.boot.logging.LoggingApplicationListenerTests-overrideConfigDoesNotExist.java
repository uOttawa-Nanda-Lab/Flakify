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

package org.springframework.boot.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.SLF4JLogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.logging.java.JavaLoggingSystem;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.boot.test.OutputCapture;
import org.springframework.context.support.GenericApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link LoggingApplicationListener}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 */
public class LoggingApplicationListenerTests {

	private static final String[] NO_ARGS = {};

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	private final LoggingApplicationListener initializer = new LoggingApplicationListener();

	private final Log logger = new SLF4JLogFactory().getInstance(getClass());

	private final SpringApplication springApplication = new SpringApplication();

	private final GenericApplicationContext context = new GenericApplicationContext();

	@Before
	public void init() throws SecurityException, IOException {
		LogManager.getLogManager().readConfiguration(
				JavaLoggingSystem.class.getResourceAsStream("logging.properties"));
		this.initializer.onApplicationEvent(new ApplicationStartedEvent(
				new SpringApplication(), NO_ARGS));
		new File("target/foo.log").delete();
		new File(tmpDir() + "/spring.log").delete();
	}

	private String tmpDir() {
		String path = this.context.getEnvironment().resolvePlaceholders(
				"${java.io.tmpdir}");
		path = path.replace("\\", "/");
		if (path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}
		return path;
	}

	@Test public void overrideConfigDoesNotExist() throws Exception{EnvironmentTestUtils.addEnvironment(this.context,"logging.config: doesnotexist.xml");this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());this.logger.info("Hello world");String output=this.outputCapture.toString().trim();assertTrue("Wrong output:\n" + output,output.contains("Hello world"));assertFalse("Wrong output:\n" + output,output.contains("???"));assertFalse(new File(tmpDir() + "/spring.log").exists());}
}
