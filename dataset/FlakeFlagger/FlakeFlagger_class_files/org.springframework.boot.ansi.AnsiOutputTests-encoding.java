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

package org.springframework.boot.ansi;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.ansi.AnsiOutput.Enabled;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.ansi.AnsiElement.BOLD;
import static org.springframework.boot.ansi.AnsiElement.FAINT;
import static org.springframework.boot.ansi.AnsiElement.GREEN;
import static org.springframework.boot.ansi.AnsiElement.NORMAL;
import static org.springframework.boot.ansi.AnsiElement.RED;

/**
 * Tests for {@link AnsiOutput}.
 *
 * @author Phillip Webb
 */
public class AnsiOutputTests {

	@BeforeClass
	public static void enable() {
		AnsiOutput.setEnabled(Enabled.ALWAYS);
	}

	@Test public void encoding() throws Exception{String encoded=AnsiOutput.toString("A",RED,BOLD,"B",NORMAL,"D",GREEN,"E",FAINT,"F");assertThat(encoded,equalTo("A[31;1mB[0mD[32mE[2mF[0;39m"));}

}
