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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.loader.util.AsciiBytes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link AsciiBytes}.
 *
 * @author Phillip Webb
 */
public class AsciiBytesTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test public void endsWith() throws Exception{AsciiBytes abc=new AsciiBytes(new byte[]{65,66,67});AsciiBytes bc=new AsciiBytes(new byte[]{65,66,67},1,2);AsciiBytes ab=new AsciiBytes(new byte[]{65,66});AsciiBytes aabc=new AsciiBytes(new byte[]{65,65,66,67});assertThat(abc.endsWith(abc),equalTo(true));assertThat(abc.endsWith(bc),equalTo(true));assertThat(abc.endsWith(ab),equalTo(false));assertThat(abc.endsWith(aabc),equalTo(false));}
}
