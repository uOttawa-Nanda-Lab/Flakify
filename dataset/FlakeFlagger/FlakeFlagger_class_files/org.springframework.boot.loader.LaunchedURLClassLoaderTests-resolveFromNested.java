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

package org.springframework.boot.loader;

import java.io.File;
import java.net.URL;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.loader.jar.JarFile;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link LaunchedURLClassLoader}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 */
@SuppressWarnings("resource")
public class LaunchedURLClassLoaderTests {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test public void resolveFromNested() throws Exception{File file=this.temporaryFolder.newFile();TestJarCreator.createTestJar(file);JarFile jarFile=new JarFile(file);URL url=jarFile.getUrl();LaunchedURLClassLoader loader=new LaunchedURLClassLoader(new URL[]{url},null);URL resource=loader.getResource("nested.jar!/3.dat");assertThat(resource.toString(),equalTo(url + "nested.jar!/3.dat"));assertThat(resource.openConnection().getInputStream().read(),equalTo(3));}

}
