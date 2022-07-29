/*
 * Copyright 2012-2014 the original author or authors.
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

package org.springframework.boot.context.embedded.jetty;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.server.ssl.SslConnector;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.mockito.InOrder;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactoryTests;
import org.springframework.boot.context.embedded.Ssl;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link JettyEmbeddedServletContainerFactory} and
 * {@link JettyEmbeddedServletContainer}.
 *
 * @author Phillip Webb
 * @author Dave Syer
 * @author Andy Wilkinson
 */
public class JettyEmbeddedServletContainerFactoryTests extends
		AbstractEmbeddedServletContainerFactoryTests {

	@Test public void sslCiphersConfiguration() throws Exception{Ssl ssl=new Ssl();ssl.setKeyStore("src/test/resources/test.jks");ssl.setKeyStorePassword("secret");ssl.setKeyPassword("password");ssl.setCiphers(new String[]{"ALPHA","BRAVO","CHARLIE"});JettyEmbeddedServletContainerFactory factory=getFactory();factory.setSsl(ssl);this.container=factory.getEmbeddedServletContainer();this.container.start();JettyEmbeddedServletContainer jettyContainer=(JettyEmbeddedServletContainer)this.container;SslConnector sslConnector=(SslConnector)jettyContainer.getServer().getConnectors()[0];assertThat(sslConnector.getSslContextFactory().getIncludeCipherSuites(),equalTo(new String[]{"ALPHA","BRAVO","CHARLIE"}));}

	private void assertTimeout(JettyEmbeddedServletContainerFactory factory, int expected) {
		this.container = factory.getEmbeddedServletContainer();
		JettyEmbeddedServletContainer jettyContainer = (JettyEmbeddedServletContainer) this.container;
		Handler[] handlers = jettyContainer.getServer().getChildHandlersByClass(
				WebAppContext.class);
		WebAppContext webAppContext = (WebAppContext) handlers[0];
		int actual = webAppContext.getSessionHandler().getSessionManager()
				.getMaxInactiveInterval();
		assertThat(actual, equalTo(expected));
	}

}
