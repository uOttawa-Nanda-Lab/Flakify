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

package org.springframework.boot.autoconfigure.web;

import javax.servlet.MultipartConfigElement;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link MultipartAutoConfiguration}. Tests an empty configuration, no
 * multipart configuration, and a multipart configuration (with both Jetty and Tomcat).
 *
 * @author Greg Turnquist
 * @author Dave Syer
 * @author Josh Long
 */
public class MultipartAutoConfigurationTests {

	private AnnotationConfigEmbeddedWebApplicationContext context;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Configuration
	public static class ContainerWithNothing {
	}

	@Configuration
	public static class ContainerWithNoMultipartJetty {
		@Bean
		JettyEmbeddedServletContainerFactory containerFactory() {
			return new JettyEmbeddedServletContainerFactory();
		}

		@Bean
		WebController controller() {
			return new WebController();
		}
	}

	@Test public void containerWithNoMultipartTomcatConfiguration(){this.context=new AnnotationConfigEmbeddedWebApplicationContext(ContainerWithNoMultipartTomcat.class,BaseConfiguration.class);DispatcherServlet servlet=this.context.getBean(DispatcherServlet.class);assertNull(servlet.getMultipartResolver());assertThat(this.context.getBeansOfType(StandardServletMultipartResolver.class).size(),equalTo(1));assertThat(this.context.getBeansOfType(MultipartResolver.class).size(),equalTo(1));verifyServletWorks();}

	private void verifyServletWorks() {
		RestTemplate restTemplate = new RestTemplate();
		assertEquals("Hello", restTemplate.getForObject("http://localhost:"
				+ this.context.getEmbeddedServletContainer().getPort() + "/",
				String.class));
	}

	@Configuration
	@Import({ EmbeddedServletContainerAutoConfiguration.class,
			DispatcherServletAutoConfiguration.class, MultipartAutoConfiguration.class,
			ServerPropertiesAutoConfiguration.class })
	protected static class BaseConfiguration {

		@Bean
		public ServerProperties serverProperties() {
			ServerProperties properties = new ServerProperties();
			properties.setPort(0);
			return properties;
		}

	}

	@Configuration
	public static class ContainerWithNoMultipartTomcat {

		@Bean
		TomcatEmbeddedServletContainerFactory containerFactory() {
			return new TomcatEmbeddedServletContainerFactory();
		}

		@Bean
		WebController controller() {
			return new WebController();
		}

	}

	@Configuration
	public static class ContainerWithEverythingJetty {

		@Bean
		MultipartConfigElement multipartConfigElement() {
			return new MultipartConfigElement("");
		}

		@Bean
		JettyEmbeddedServletContainerFactory containerFactory() {
			return new JettyEmbeddedServletContainerFactory();
		}

		@Bean
		WebController webController() {
			return new WebController();
		}

	}

	@Configuration
	@EnableWebMvc
	public static class ContainerWithEverythingTomcat {

		@Bean
		MultipartConfigElement multipartConfigElement() {
			return new MultipartConfigElement("");
		}

		@Bean
		TomcatEmbeddedServletContainerFactory containerFactory() {
			return new TomcatEmbeddedServletContainerFactory();
		}

		@Bean
		WebController webController() {
			return new WebController();
		}

	}

	@Controller
	public static class WebController {

		@RequestMapping("/")
		@ResponseBody
		public String index() {
			return "Hello";
		}

	}

}
