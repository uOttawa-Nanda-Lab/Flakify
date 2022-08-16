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

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizerBeanPostProcessor;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link ServerPropertiesAutoConfiguration}.
 *
 * @author Dave Syer
 */
public class ServerPropertiesAutoConfigurationTests {

	private static AbstractEmbeddedServletContainerFactory containerFactory;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private AnnotationConfigEmbeddedWebApplicationContext context;

	@Before
	public void init() {
		containerFactory = mock(AbstractEmbeddedServletContainerFactory.class);
	}

	@Test public void tomcatProperties() throws Exception{containerFactory=mock(TomcatEmbeddedServletContainerFactory.class);this.context=new AnnotationConfigEmbeddedWebApplicationContext();this.context.register(Config.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);EnvironmentTestUtils.addEnvironment(this.context,"server.tomcat.basedir:target/foo","server.port:9000");this.context.refresh();ServerProperties server=this.context.getBean(ServerProperties.class);assertNotNull(server);assertEquals(new File("target/foo"),server.getTomcat().getBasedir());verify(containerFactory).setPort(9000);}

	@Configuration
	protected static class Config {

		@Bean
		public EmbeddedServletContainerFactory containerFactory() {
			return ServerPropertiesAutoConfigurationTests.containerFactory;
		}

		@Bean
		public EmbeddedServletContainerCustomizerBeanPostProcessor embeddedServletContainerCustomizerBeanPostProcessor() {
			return new EmbeddedServletContainerCustomizerBeanPostProcessor();
		}

	}

	@Configuration
	protected static class CustomContainerConfig {

		@Bean
		public EmbeddedServletContainerFactory containerFactory() {
			JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
			factory.setPort(3000);
			return factory;
		}

		@Bean
		public EmbeddedServletContainerCustomizerBeanPostProcessor embeddedServletContainerCustomizerBeanPostProcessor() {
			return new EmbeddedServletContainerCustomizerBeanPostProcessor();
		}

	}

	@Configuration
	protected static class CustomizeConfig {

		@Bean
		public EmbeddedServletContainerCustomizer containerCustomizer() {
			return new EmbeddedServletContainerCustomizer() {

				@Override
				public void customize(ConfigurableEmbeddedServletContainer container) {
					container.setPort(3000);
				}

			};
		}

	}

	@Configuration
	protected static class MutiServerPropertiesBeanConfig {

		@Bean
		public ServerProperties serverPropertiesOne() {
			return new ServerProperties();
		}

		@Bean
		public ServerProperties serverPropertiesTwo() {
			return new ServerProperties();
		}

	}

}
