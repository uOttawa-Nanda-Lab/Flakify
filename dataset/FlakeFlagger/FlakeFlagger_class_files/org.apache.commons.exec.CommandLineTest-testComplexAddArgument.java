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
 * Create a command line with pre-quoted strings to test SANDBOX-192, e.g. "runMemorySud.cmd", "10", "30", "-XX:+UseParallelGC", "\"-XX:ParallelGCThreads=2\""
 */
public void testComplexAddArgument() {
	CommandLine cmdl = new CommandLine("runMemorySud.cmd");
	cmdl.addArgument("10", false);
	cmdl.addArgument("30", false);
	cmdl.addArgument("-XX:+UseParallelGC", false);
	cmdl.addArgument("\"-XX:ParallelGCThreads=2\"", false);
	assertEquals("runMemorySud.cmd 10 30 -XX:+UseParallelGC \"-XX:ParallelGCThreads=2\"", cmdl.toString());
	assertEquals(new String[] { "runMemorySud.cmd", "10", "30", "-XX:+UseParallelGC", "\"-XX:ParallelGCThreads=2\"" },
			cmdl.toStrings());
}    
}
