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

package org.springframework.boot.cli.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.junit.Test;
import org.springframework.util.ClassUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ResourceUtils}.
 *
 * @author Dave Syer
 */
public class ResourceUtilsTests {

	@Test public void explicitClasspathResourceWithSlash(){List<String> urls=ResourceUtils.getUrls("classpath:/init.groovy",ClassUtils.getDefaultClassLoader());assertEquals(1,urls.size());assertTrue(urls.get(0).startsWith("file:"));}

}
