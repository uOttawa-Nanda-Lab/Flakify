/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.commons.exec.util;

import org.apache.commons.exec.environment.EnvironmentUtil;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class MapUtilTest extends TestCase
{
    /**
	 * Test copying of map
	 */
	public void testCopyMap() throws Exception {
		HashMap procEnvironment = new HashMap();
		procEnvironment.put("JAVA_HOME", "/usr/opt/java");
		Map result = MapUtils.copy(procEnvironment);
		assertTrue(result.size() == 1);
		assertTrue(procEnvironment.size() == 1);
		assertEquals("/usr/opt/java", result.get("JAVA_HOME"));
		result.remove("JAVA_HOME");
		assertTrue(result.size() == 0);
		assertTrue(procEnvironment.size() == 1);
	}
}