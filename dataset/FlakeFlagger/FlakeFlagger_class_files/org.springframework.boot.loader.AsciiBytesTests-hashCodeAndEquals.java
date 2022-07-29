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

	@Test public void hashCodeAndEquals() throws Exception{AsciiBytes abcd=new AsciiBytes(new byte[]{65,66,67,68});AsciiBytes bc=new AsciiBytes(new byte[]{66,67});AsciiBytes bc_substring=new AsciiBytes(new byte[]{65,66,67,68}).substring(1,3);AsciiBytes bc_string=new AsciiBytes("BC");assertThat(bc.hashCode(),equalTo(bc.hashCode()));assertThat(bc.hashCode(),equalTo(bc_substring.hashCode()));assertThat(bc.hashCode(),equalTo(bc_string.hashCode()));assertThat(bc,equalTo(bc));assertThat(bc,equalTo(bc_substring));assertThat(bc,equalTo(bc_string));assertThat(bc.hashCode(),not(equalTo(abcd.hashCode())));assertThat(bc,not(equalTo(abcd)));}
}
