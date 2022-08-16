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

package org.springframework.boot.autoconfigure;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests to reproduce reported issues.
 *
 * @author Phillip Webb
 */
public class AutoConfigurationReproTests {

	private ConfigurableApplicationContext context;

	@Test public void doesNotEarlyInitializeFactoryBeans() throws Exception{SpringApplication application=new SpringApplication(EarlyInitConfig.class,PropertySourcesPlaceholderConfigurer.class,EmbeddedServletContainerAutoConfiguration.class,ServerPropertiesAutoConfiguration.class);this.context=application.run("--server.port=0");String bean=(String)this.context.getBean("earlyInit");assertThat(bean,equalTo("bucket"));}

	@Configuration
	public static class Config {
	}

	@Configuration
	@ImportResource("classpath:/early-init-test.xml")
	public static class EarlyInitConfig {

	}
}
