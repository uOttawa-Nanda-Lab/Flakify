/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.context.embedded;

import javax.servlet.Servlet;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.mockito.Mockito.verify;

/**
 * Tests for {@link XmlEmbeddedWebApplicationContext}.
 *
 * @author Phillip Webb
 */
public class XmlEmbeddedWebApplicationContextTests {

	private static final String PATH = XmlEmbeddedWebApplicationContextTests.class
			.getPackage().getName().replace(".", "/")
			+ "/";

	private static final String FILE = "exampleEmbeddedWebApplicationConfiguration.xml";

	private XmlEmbeddedWebApplicationContext context;

	@Test public void loadAndRefreshFromResource() throws Exception{this.context=new XmlEmbeddedWebApplicationContext();this.context.load(new ClassPathResource(FILE,getClass()));this.context.refresh();verifyContext();}

	private void verifyContext() {
		MockEmbeddedServletContainerFactory containerFactory = this.context
				.getBean(MockEmbeddedServletContainerFactory.class);
		Servlet servlet = this.context.getBean(Servlet.class);
		verify(containerFactory.getServletContext()).addServlet("servlet", servlet);
	}
}
