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

package org.springframework.boot.loader.tools;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.loader.tools.sample.ClassWithMainMethod;
import org.springframework.boot.loader.tools.sample.ClassWithoutMainMethod;
import org.springframework.util.FileCopyUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link Repackager}.
 *
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
public class RepackagerTests {

	private static final Libraries NO_LIBRARIES = new Libraries() {
		@Override
		public void doWithLibraries(LibraryCallback callback) throws IOException {
		}
	};

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private TestJarFile testJarFile;

	@Before
	public void setup() throws IOException {
		this.testJarFile = new TestJarFile(this.temporaryFolder);
	}

	@Test public void springBootVersion() throws Exception{this.testJarFile.addClass("a/b/C.class",ClassWithMainMethod.class);File file=this.testJarFile.getFile();Repackager repackager=new Repackager(file);repackager.repackage(NO_LIBRARIES);Manifest actualManifest=getManifest(file);assertThat(actualManifest.getMainAttributes().containsKey(new Attributes.Name("Spring-Boot-Version")),equalTo(true));}

	private boolean hasLauncherClasses(File file) throws IOException {
		return hasEntry(file, "org/springframework/boot/")
				&& hasEntry(file, "org/springframework/boot/loader/JarLauncher.class");
	}

	private boolean hasEntry(File file, String name) throws IOException {
		return getEntry(file, name) != null;
	}

	private JarEntry getEntry(File file, String name) throws IOException {
		JarFile jarFile = new JarFile(file);
		try {
			return jarFile.getJarEntry(name);
		}
		finally {
			jarFile.close();
		}
	}

	private Manifest getManifest(File file) throws IOException {
		JarFile jarFile = new JarFile(file);
		try {
			return jarFile.getManifest();
		}
		finally {
			jarFile.close();
		}
	}

}
