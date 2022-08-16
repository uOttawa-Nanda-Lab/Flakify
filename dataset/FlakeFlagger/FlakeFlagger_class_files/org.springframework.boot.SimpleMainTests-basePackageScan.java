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

package org.springframework.boot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link SpringApplication} main method.
 *
 * @author Dave Syer
 */
@Configuration
public class SimpleMainTests {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	private static final String SPRING_STARTUP = "root of context hierarchy";

	@Test public void basePackageScan() throws Exception{SpringApplication.main(getArgs(ClassUtils.getPackageName(getClass()) + ".sampleconfig"));assertTrue(getOutput().contains(SPRING_STARTUP));}

	private String[] getArgs(String... args) {
		List<String> list = new ArrayList<String>(Arrays.asList(
				"--spring.main.webEnvironment=false", "--spring.main.showBanner=false"));
		if (args.length > 0) {
			list.add("--spring.main.sources="
					+ StringUtils.arrayToCommaDelimitedString(args));
		}
		return list.toArray(new String[list.size()]);
	}

	private String getOutput() {
		return this.outputCapture.toString();
	}

}
