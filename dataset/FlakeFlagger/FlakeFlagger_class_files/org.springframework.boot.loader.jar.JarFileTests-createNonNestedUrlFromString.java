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

package org.springframework.boot.loader.jar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.loader.TestJarCreator;
import org.springframework.boot.loader.data.RandomAccessDataFile;
import org.springframework.boot.loader.util.AsciiBytes;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link JarFile}.
 *
 * @author Phillip Webb
 * @author Martin Lau
 */
public class JarFileTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private File rootJarFile;

	private JarFile jarFile;

	@Before
	public void setup() throws Exception {
		this.rootJarFile = this.temporaryFolder.newFile();
		TestJarCreator.createTestJar(this.rootJarFile);
		this.jarFile = new JarFile(this.rootJarFile);
	}

	@Test public void createNonNestedUrlFromString() throws Exception{JarFile.registerUrlProtocolHandler();String spec="jar:" + this.rootJarFile.toURI() + "!/2.dat";URL url=new URL(spec);assertThat(url.toString(),equalTo(spec));InputStream inputStream=url.openStream();assertThat(inputStream,notNullValue());assertThat(inputStream.read(),equalTo(2));JarURLConnection connection=(JarURLConnection)url.openConnection();assertThat(connection.getURL().toString(),equalTo(spec));assertThat(connection.getJarFileURL().toURI(),equalTo(this.rootJarFile.toURI()));assertThat(connection.getEntryName(),equalTo("2.dat"));}

}
