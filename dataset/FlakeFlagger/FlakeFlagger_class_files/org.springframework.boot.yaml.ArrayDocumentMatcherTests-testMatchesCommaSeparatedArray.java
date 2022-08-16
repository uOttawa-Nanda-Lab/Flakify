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

package org.springframework.boot.yaml;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.config.YamlProcessor.MatchStatus;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link ArrayDocumentMatcher}.
 *
 * @author Dave Syer
 */
public class ArrayDocumentMatcherTests {

	@Test public void testMatchesCommaSeparatedArray() throws IOException{ArrayDocumentMatcher matcher=new ArrayDocumentMatcher("foo","bar");assertEquals(MatchStatus.FOUND,matcher.matches(getProperties("foo: bar,spam")));}

	private Properties getProperties(String values) throws IOException {
		return PropertiesLoaderUtils.loadProperties(new ByteArrayResource(values
				.getBytes()));
	}

}
