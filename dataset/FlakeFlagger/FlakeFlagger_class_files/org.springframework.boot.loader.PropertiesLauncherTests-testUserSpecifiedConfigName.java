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

package org.springframework.boot.loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link PropertiesLauncher}.
 *
 * @author Dave Syer
 */
public class PropertiesLauncherTests {

	@Rule
	public OutputCapture output = new OutputCapture();

	@Before
	public void setup() throws IOException {
		System.setProperty("loader.home",
				new File("src/test/resources").getAbsolutePath());
	}

	@Test public void testUserSpecifiedConfigName() throws Exception{System.setProperty("loader.config.name","foo");PropertiesLauncher launcher=new PropertiesLauncher();assertEquals("my.Application",launcher.getMainClass());assertEquals("[etc/]",ReflectionTestUtils.getField(launcher,"paths").toString());}

	private void waitFor(String value) throws Exception {
		int count = 0;
		boolean timeout = false;
		while (!timeout && count < 100) {
			count++;
			Thread.sleep(50L);
			timeout = this.output.toString().contains(value);
		}
		assertTrue("Timed out waiting for (" + value + ")", timeout);
	}

	public static class TestLoader extends URLClassLoader {

		public TestLoader(ClassLoader parent) {
			super(new URL[0], parent);
		}

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			return super.findClass(name);
		}
	}

}
