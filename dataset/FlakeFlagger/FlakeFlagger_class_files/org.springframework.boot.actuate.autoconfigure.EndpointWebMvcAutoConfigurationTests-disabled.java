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

package org.springframework.boot.actuate.autoconfigure;

import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.URI;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.boot.test.ServerPortInfoApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link EndpointWebMvcAutoConfiguration}.
 *
 * @author Phillip Webb
 * @author Greg Turnquist
 */
public class EndpointWebMvcAutoConfigurationTests {

	private final AnnotationConfigEmbeddedWebApplicationContext applicationContext = new AnnotationConfigEmbeddedWebApplicationContext();

	private static ThreadLocal<Ports> ports = new ThreadLocal<Ports>();

	@Before
	public void grabPorts() {
		ports.set(new Ports());
	}

	@Test public void disabled() throws Exception{this.applicationContext.register(RootConfig.class,DisableConfig.class,BaseConfiguration.class,EndpointWebMvcAutoConfiguration.class);this.applicationContext.refresh();assertContent("/controller",ports.get().server,"controlleroutput");assertContent("/endpoint",ports.get().server,null);assertContent("/controller",ports.get().management,null);assertContent("/endpoint",ports.get().management,null);this.applicationContext.close();assertAllClosed();}

	private void assertAllClosed() throws Exception {
		assertContent("/controller", ports.get().server, null);
		assertContent("/endpoint", ports.get().server, null);
		assertContent("/controller", ports.get().management, null);
		assertContent("/endpoint", ports.get().management, null);
	}

	private static class Ports {

		int server = SocketUtils.findAvailableTcpPort();

		int management = SocketUtils.findAvailableTcpPort();

	}

	@Configuration
	@Import({ PropertyPlaceholderAutoConfiguration.class,
			EmbeddedServletContainerAutoConfiguration.class,
			HttpMessageConvertersAutoConfiguration.class,
			DispatcherServletAutoConfiguration.class, WebMvcAutoConfiguration.class,
			ManagementServerPropertiesAutoConfiguration.class,
			ServerPropertiesAutoConfiguration.class, WebMvcAutoConfiguration.class })
	protected static class BaseConfiguration {

	}

	@Configuration
	public static class RootConfig {

	}

	@Configuration
	public static class ServerPortConfig {

		private int count = 0;

		public int getCount() {
			return this.count;
		}

		@Bean
		public ServerProperties serverProperties() {
			ServerProperties properties = new ServerProperties() {
				@Override
				public void customize(ConfigurableEmbeddedServletContainer container) {
					ServerPortConfig.this.count++;
					super.customize(container);
				}
			};
			properties.setPort(ports.get().server);
			return properties;
		}

	}

	@Controller
	public static class TestController {

		@RequestMapping("/controller")
		@ResponseBody
		public String requestMappedMethod() {
			return "controlleroutput";
		}

	}

	@Configuration
	@Import(ServerPortConfig.class)
	public static class DifferentPortConfig {

		@Bean
		public ManagementServerProperties managementServerProperties() {
			ManagementServerProperties properties = new ManagementServerProperties();
			properties.setPort(ports.get().management);
			return properties;
		}

	}

	@Configuration
	@Import(ServerPortConfig.class)
	public static class RandomPortConfig {

		@Bean
		public ManagementServerProperties managementServerProperties() {
			ManagementServerProperties properties = new ManagementServerProperties();
			properties.setPort(0);
			return properties;
		}

	}

	@Configuration
	@Import(ServerPortConfig.class)
	public static class DisableConfig {

		@Bean
		public ManagementServerProperties managementServerProperties() {
			ManagementServerProperties properties = new ManagementServerProperties();
			properties.setPort(-1);
			return properties;
		}

	}

	public static class TestEndpoint implements MvcEndpoint {

		@RequestMapping
		@ResponseBody
		public String invoke() {
			return "endpointoutput";
		}

		@Override
		public String getPath() {
			return "/endpoint";
		}

		@Override
		public boolean isSensitive() {
			return true;
		}

		@Override
		@SuppressWarnings("rawtypes")
		public Class<? extends Endpoint> getEndpointType() {
			return Endpoint.class;
		}

	}

	private static class GrabManagementPort implements
			ApplicationListener<EmbeddedServletContainerInitializedEvent> {

		private ApplicationContext rootContext;

		private EmbeddedServletContainer servletContainer;

		public GrabManagementPort(ApplicationContext rootContext) {
			this.rootContext = rootContext;
		}

		@Override
		public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
			if (event.getApplicationContext() != this.rootContext) {
				this.servletContainer = event.getEmbeddedServletContainer();
			}
		}

		public EmbeddedServletContainer getServletContainer() {
			return this.servletContainer;
		}
	}

}
