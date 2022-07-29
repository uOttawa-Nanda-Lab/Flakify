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

package org.springframework.boot.logging.logback;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.ansi.AnsiOutput;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ColorConverter}.
 *
 * @author Phillip Webb
 */
public class ColorConverterTests {

	private ColorConverter converter;
	private LoggingEvent event;
	private final String in = "in";

	@BeforeClass
	public static void setupAnsi() {
		AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
	}

	@Before
	public void setup() {
		this.converter = new ColorConverter();
		this.event = new LoggingEvent();
	}

	@Test public void highlightError() throws Exception{this.event.setLevel(Level.ERROR);String out=this.converter.transform(this.event,this.in);assertThat(out,equalTo("\033[31min\033[0;39m"));}
}
