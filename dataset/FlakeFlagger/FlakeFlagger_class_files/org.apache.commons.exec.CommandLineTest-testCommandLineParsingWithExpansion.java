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

package org.apache.commons.exec;

import java.util.Arrays;
import java.util.HashMap;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public class CommandLineTest extends TestCase {

    private void assertEquals(String[] expected, String[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionFailedError("Arrays not equal");
        }
    }

    /**
	 * Test expanding the command line based on a user-supplied map.
	 */
	public void testCommandLineParsingWithExpansion() {
		CommandLine cmdl = null;
		HashMap substitutionMap = new HashMap();
		substitutionMap.put("JAVA_HOME", "/usr/local/java");
		substitutionMap.put("appMainClass", "foo.bar.Main");
		HashMap incompleteMap = new HashMap();
		incompleteMap.put("JAVA_HOME", "/usr/local/java");
		cmdl = CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}");
		assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") == 0);
		assertEquals(new String[] { "${appMainClass}" }, cmdl.getArguments());
		cmdl = CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}", new HashMap());
		assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") == 0);
		assertEquals(new String[] { "${appMainClass}" }, cmdl.getArguments());
		cmdl = CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}", substitutionMap);
		assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") < 0);
		assertTrue(cmdl.getExecutable().indexOf("local") > 0);
		assertEquals(new String[] { "foo.bar.Main" }, cmdl.getArguments());
		cmdl = CommandLine.parse("${JAVA_HOME}/bin/java ${appMainClass}", incompleteMap);
		assertTrue(cmdl.getExecutable().indexOf("${JAVA_HOME}") < 0);
		assertTrue(cmdl.getExecutable().indexOf("local") > 0);
		assertEquals(new String[] { "${appMainClass}" }, cmdl.getArguments());
	}    
}
