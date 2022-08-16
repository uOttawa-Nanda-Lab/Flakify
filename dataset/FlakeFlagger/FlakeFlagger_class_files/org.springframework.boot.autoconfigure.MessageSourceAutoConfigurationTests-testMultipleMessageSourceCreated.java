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

import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link MessageSourceAutoConfiguration}.
 *
 * @author Dave Syer
 */
public class MessageSourceAutoConfigurationTests {

	private AnnotationConfigApplicationContext context;

	@Test public void testMultipleMessageSourceCreated() throws Exception{this.context=new AnnotationConfigApplicationContext();EnvironmentTestUtils.addEnvironment(this.context,"spring.messages.basename:test/messages,test/messages2");this.context.register(MessageSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);this.context.refresh();assertEquals("bar",this.context.getMessage("foo",null,"Foo message",Locale.UK));assertEquals("bar-bar",this.context.getMessage("foo-foo",null,"Foo-Foo message",Locale.UK));}

	@Configuration
	@PropertySource("classpath:/switch-messages.properties")
	protected static class Config {

	}
}
