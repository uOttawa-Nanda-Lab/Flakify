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

package org.springframework.boot.autoconfigure.mail;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link MailSenderAutoConfiguration}.
 *
 * @author Stephane Nicoll
 */
public class MailSenderAutoConfigurationTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private AnnotationConfigApplicationContext context;

	@Test public void smptHostWithSettings(){String host="192.168.1.234";load(EmptyConfig.class,"spring.mail.host:" + host,"spring.mail.port:42","spring.mail.username:john","spring.mail.password:secret","spring.mail.default-encoding:ISO-9");JavaMailSenderImpl bean=(JavaMailSenderImpl)this.context.getBean(JavaMailSender.class);assertEquals(host,bean.getHost());assertEquals(42,bean.getPort());assertEquals("john",bean.getUsername());assertEquals("secret",bean.getPassword());assertEquals("ISO-9",bean.getDefaultEncoding());}

	private void load(Class<?> config, String... environment) {
		this.context = doLoad(new Class<?>[] { config }, environment);
	}

	private AnnotationConfigApplicationContext doLoad(Class<?>[] configs,
			String... environment) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(applicationContext, environment);
		applicationContext.register(configs);
		applicationContext.register(MailSenderAutoConfiguration.class);
		applicationContext.refresh();
		return applicationContext;
	}

	@Configuration
	static class EmptyConfig {

	}

	@Configuration
	static class ManualMailConfiguration {

		@Bean
		JavaMailSender customMailSender() {
			return new JavaMailSenderImpl();
		}

	}

}
