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

package org.springframework.boot.cli;

import java.io.File;
import java.net.URI;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests to exercise the samples.
 *
 * @author Dave Syer
 * @author Greg Turnquist
 * @author Roy Clarkson
 * @author Phillip Webb
 */
public class SampleIntegrationTests {

	@Rule
	public CliTester cli = new CliTester("samples/");

	@Test public void uiSample() throws Exception{this.cli.run("ui.groovy","--classpath=.:src/test/resources");String result=this.cli.getHttpOutput();assertTrue("Wrong output: " + result,result.contains("Hello World"));result=this.cli.getHttpOutput("/css/bootstrap.min.css");assertTrue("Wrong output: " + result,result.contains("container"));}

}
