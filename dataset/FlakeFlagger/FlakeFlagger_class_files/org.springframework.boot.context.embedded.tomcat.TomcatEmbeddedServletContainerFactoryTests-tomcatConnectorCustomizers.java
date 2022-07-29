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

package org.springframework.boot.context.embedded.tomcat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Service;
import org.apache.catalina.Valve;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.AbstractHttp11JsseProtocol;
import org.junit.Test;
import org.mockito.InOrder;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactoryTests;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.util.SocketUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link TomcatEmbeddedServletContainerFactory} and
 * {@link TomcatEmbeddedServletContainer}.
 *
 * @author Phillip Webb
 * @author Dave Syer
 * @author Stephane Nicoll
 */
public class TomcatEmbeddedServletContainerFactoryTests extends
		AbstractEmbeddedServletContainerFactoryTests {

	@Test public void tomcatConnectorCustomizers() throws Exception{TomcatEmbeddedServletContainerFactory factory=getFactory();TomcatConnectorCustomizer[] listeners=new TomcatConnectorCustomizer[4];for (int i=0;i < listeners.length;i++){listeners[i]=mock(TomcatConnectorCustomizer.class);}factory.setTomcatConnectorCustomizers(Arrays.asList(listeners[0],listeners[1]));factory.addConnectorCustomizers(listeners[2],listeners[3]);this.container=factory.getEmbeddedServletContainer();InOrder ordered=inOrder((Object[])listeners);for (TomcatConnectorCustomizer listener:listeners){ordered.verify(listener).customize((Connector)anyObject());}}

	private void assertTimeout(TomcatEmbeddedServletContainerFactory factory, int expected) {
		Tomcat tomcat = getTomcat(factory);
		Context context = (Context) tomcat.getHost().findChildren()[0];
		assertThat(context.getSessionTimeout(), equalTo(expected));
	}

	private Tomcat getTomcat(TomcatEmbeddedServletContainerFactory factory) {
		this.container = factory.getEmbeddedServletContainer();
		return ((TomcatEmbeddedServletContainer) this.container).getTomcat();
	}

	private void doWithBlockedPort(final int port, Runnable action) throws IOException {
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress(port));

		try {
			action.run();
		}
		finally {
			serverSocket.close();
		}
	}

}
