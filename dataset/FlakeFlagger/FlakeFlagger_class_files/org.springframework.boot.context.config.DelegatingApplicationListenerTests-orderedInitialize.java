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

package org.springframework.boot.context.config;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link DelegatingApplicationListener}.
 *
 * @author Dave Syer
 */
public class DelegatingApplicationListenerTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private final DelegatingApplicationListener listener = new DelegatingApplicationListener();

	private final StaticApplicationContext context = new StaticApplicationContext();

	@Test
	public void orderedInitialize() throws Exception {
		EnvironmentTestUtils.addEnvironment(this.context, "context.listener.classes:"
				+ MockInitB.class.getName() + "," + MockInitA.class.getName());
		this.listener.onApplicationEvent(new ApplicationEnvironmentPreparedEvent(
				new SpringApplication(), new String[0], this.context.getEnvironment()));
		this.context.getBeanFactory().registerSingleton("testListener", this.listener);
		this.context.refresh();
		assertThat(this.context.getBeanFactory().getSingleton("a"), equalTo((Object) "a"));
		assertThat(this.context.getBeanFactory().getSingleton("b"), equalTo((Object) "b"));
	}

	@Order(Ordered.HIGHEST_PRECEDENCE)
	private static class MockInitA implements ApplicationListener<ContextRefreshedEvent> {
		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) event
					.getApplicationContext();
			applicationContext.getBeanFactory().registerSingleton("a", "a");
		}
	}

	@Order(Ordered.LOWEST_PRECEDENCE)
	private static class MockInitB implements ApplicationListener<ContextRefreshedEvent> {

		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) event
					.getApplicationContext();
			assertThat(applicationContext.getBeanFactory().getSingleton("a"),
					equalTo((Object) "a"));
			applicationContext.getBeanFactory().registerSingleton("b", "b");
		}
	}

}
