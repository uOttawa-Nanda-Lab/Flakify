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

package org.springframework.boot.actuate.system;

import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests {@link EmbeddedServerPortFileWriter}.
 *
 * @author David Liu
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
public class EmbeddedServerPortFileWriterTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	@After
	public void reset() {
		System.clearProperty("PORTFILE");
	}

	@Test public void overridePortFile() throws Exception{File file=this.temporaryFolder.newFile();System.setProperty("PORTFILE",this.temporaryFolder.newFile().getAbsolutePath());EmbeddedServerPortFileWriter listener=new EmbeddedServerPortFileWriter(file);listener.onApplicationEvent(mockEvent("",8080));assertThat(FileCopyUtils.copyToString(new FileReader(System.getProperty("PORTFILE"))),equalTo("8080"));}

	private EmbeddedServletContainerInitializedEvent mockEvent(String name, int port) {
		EmbeddedWebApplicationContext applicationContext = mock(EmbeddedWebApplicationContext.class);
		EmbeddedServletContainer source = mock(EmbeddedServletContainer.class);
		given(applicationContext.getNamespace()).willReturn(name);
		given(source.getPort()).willReturn(port);
		EmbeddedServletContainerInitializedEvent event = new EmbeddedServletContainerInitializedEvent(
				applicationContext, source);
		return event;
	}

	private Set<String> collectFileNames(File directory) {
		Set<String> names = new HashSet<String>();
		if (directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				names.add(file.getName());
			}
		}
		return names;
	}

}
