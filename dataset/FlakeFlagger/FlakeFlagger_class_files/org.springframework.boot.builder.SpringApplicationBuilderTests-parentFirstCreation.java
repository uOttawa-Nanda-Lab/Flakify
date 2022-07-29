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

package org.springframework.boot.builder;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.test.ApplicationContextTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link SpringApplicationBuilder}.
 *
 * @author Dave Syer
 */
public class SpringApplicationBuilderTests {

	private ConfigurableApplicationContext context;

	@Test public void parentFirstCreation() throws Exception{SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).child(ChildConfig.class);application.contextClass(SpyApplicationContext.class);this.context=application.run();verify(((SpyApplicationContext)this.context).getApplicationContext()).setParent(any(ApplicationContext.class));assertThat(((SpyApplicationContext)this.context).getRegisteredShutdownHook(),equalTo(false));}

	@Configuration
	static class ExampleConfig {

	}

	@Configuration
	static class ChildConfig {

	}

	public static class SpyApplicationContext extends AnnotationConfigApplicationContext {

		private final ConfigurableApplicationContext applicationContext = spy(new AnnotationConfigApplicationContext());

		private ResourceLoader resourceLoader;

		private boolean registeredShutdownHook;

		@Override
		public void setParent(ApplicationContext parent) {
			this.applicationContext.setParent(parent);
		}

		public ConfigurableApplicationContext getApplicationContext() {
			return this.applicationContext;
		}

		@Override
		public void setResourceLoader(ResourceLoader resourceLoader) {
			super.setResourceLoader(resourceLoader);
			this.resourceLoader = resourceLoader;
		}

		public ResourceLoader getResourceLoader() {
			return this.resourceLoader;
		}

		@Override
		public void registerShutdownHook() {
			super.registerShutdownHook();
			this.registeredShutdownHook = true;
		}

		public boolean getRegisteredShutdownHook() {
			return this.registeredShutdownHook;
		}
	}
}
