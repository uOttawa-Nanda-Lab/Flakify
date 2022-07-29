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

package org.springframework.boot.loader.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.loader.ByteArrayStartsWith;
import org.springframework.boot.loader.data.RandomAccessData.ResourceAccess;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link RandomAccessDataFile}.
 *
 * @author Phillip Webb
 */
public class RandomAccessDataFileTests {

	private static final byte[] BYTES;
	static {
		BYTES = new byte[256];
		for (int i = 0; i < BYTES.length; i++) {
			BYTES[i] = (byte) i;
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private File tempFile;

	private RandomAccessDataFile file;

	private InputStream inputStream;

	@Before
	public void setup() throws Exception {
		this.tempFile = this.temporaryFolder.newFile();
		FileOutputStream outputStream = new FileOutputStream(this.tempFile);
		outputStream.write(BYTES);
		outputStream.close();
		this.file = new RandomAccessDataFile(this.tempFile);
		this.inputStream = this.file.getInputStream(ResourceAccess.PER_READ);
	}

	@Test public void subsectionTooBig() throws Exception{this.file.getSubsection(0,256);this.thrown.expect(IndexOutOfBoundsException.class);this.file.getSubsection(0,257);}

	private static Matcher<? super byte[]> startsWith(byte[] bytes) {
		return new ByteArrayStartsWith(bytes);
	}

}
