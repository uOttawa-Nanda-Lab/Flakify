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

package org.springframework.boot.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.cli.command.grab.GrabCommand;
import org.springframework.util.FileSystemUtils;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Integration tests for {@link GrabCommand}
 *
 * @author Andy Wilkinson
 * @author Dave Syer
 */
public class GrabCommandIntegrationTests {

	@Rule
	public CliTester cli = new CliTester("src/test/resources/grab-samples/");

	@Before
	@After
	public void deleteLocalRepository() {
		FileSystemUtils.deleteRecursively(new File("target/repository"));
		System.clearProperty("grape.root");
		System.clearProperty("groovy.grape.report.downloads");
	}

	@Test public void grab() throws Exception{System.setProperty("grape.root","target");System.setProperty("groovy.grape.report.downloads","true");String output=this.cli.grab("grab.groovy","--autoconfigure=false");assertTrue(new File("target/repository/joda-time/joda-time").isDirectory());assertTrue(output.contains("Downloading: file:"));}
}
