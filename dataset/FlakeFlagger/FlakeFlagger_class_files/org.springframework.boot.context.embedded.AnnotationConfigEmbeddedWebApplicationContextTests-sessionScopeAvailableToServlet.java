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

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.config.ExampleEmbeddedWebApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link AnnotationConfigEmbeddedWebApplicationContext}.
 *
 * @author Phillip Webb
 */
public class AnnotationConfigEmbeddedWebApplicationContextTests {

	private AnnotationConfigEmbeddedWebApplicationContext context;

	@Test public void sessionScopeAvailableToServlet() throws Exception{this.context=new AnnotationConfigEmbeddedWebApplicationContext(ExampleEmbeddedWebApplicationConfiguration.class,ExampleServletWithAutowired.class,SessionScopedComponent.class);Servlet servlet=this.context.getBean(ExampleServletWithAutowired.class);assertNotNull(servlet);}

	private void verifyContext() {
		MockEmbeddedServletContainerFactory containerFactory = this.context
				.getBean(MockEmbeddedServletContainerFactory.class);
		Servlet servlet = this.context.getBean(Servlet.class);
		verify(containerFactory.getServletContext()).addServlet("servlet", servlet);
	}

	@Component
	@SuppressWarnings("serial")
	protected static class ExampleServletWithAutowired extends GenericServlet {

		@Autowired
		private SessionScopedComponent component;

		@Override
		public void service(ServletRequest req, ServletResponse res)
				throws ServletException, IOException {
			assertNotNull(this.component);
		}

	}

	@Component
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	protected static class SessionScopedComponent {

	}

	@Configuration
	@EnableWebMvc
	public static class ServletContextAwareEmbeddedConfiguration implements
			ServletContextAware {

		private ServletContext servletContext;

		@Bean
		public EmbeddedServletContainerFactory containerFactory() {
			return new MockEmbeddedServletContainerFactory();
		}

		@Bean
		public Servlet servlet() {
			return new MockServlet();
		}

		@Override
		public void setServletContext(ServletContext servletContext) {
			this.servletContext = servletContext;
		}

		public ServletContext getServletContext() {
			return this.servletContext;
		}

	}

	@Configuration
	public static class EmbeddedContainerConfiguration {

		@Bean
		public EmbeddedServletContainerFactory containerFactory() {
			return new MockEmbeddedServletContainerFactory();
		}

	}

	@Configuration
	@EnableWebMvc
	public static class ServletContextAwareConfiguration implements ServletContextAware {

		private ServletContext servletContext;

		@Bean
		public Servlet servlet() {
			return new MockServlet();
		}

		@Override
		public void setServletContext(ServletContext servletContext) {
			this.servletContext = servletContext;
		}

		public ServletContext getServletContext() {
			return this.servletContext;
		}

	}
}
