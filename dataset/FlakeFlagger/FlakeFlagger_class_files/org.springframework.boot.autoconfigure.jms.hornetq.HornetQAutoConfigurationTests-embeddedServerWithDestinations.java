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

package org.springframework.boot.autoconfigure.jms.hornetq;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.invm.InVMConnectorFactory;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.jms.client.HornetQConnectionFactory;
import org.hornetq.jms.server.config.JMSConfiguration;
import org.hornetq.jms.server.config.JMSQueueConfiguration;
import org.hornetq.jms.server.config.TopicConfiguration;
import org.hornetq.jms.server.config.impl.JMSConfigurationImpl;
import org.hornetq.jms.server.config.impl.JMSQueueConfigurationImpl;
import org.hornetq.jms.server.config.impl.TopicConfigurationImpl;
import org.hornetq.jms.server.embedded.EmbeddedJMS;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link HornetQAutoConfiguration}.
 *
 * @author Stephane Nicoll
 */
public class HornetQAutoConfigurationTests {

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	private AnnotationConfigApplicationContext context;

	@Test public void embeddedServerWithDestinations(){load(EmptyConfiguration.class,"spring.hornetq.embedded.queues=Queue1,Queue2","spring.hornetq.embedded.topics=Topic1");DestinationChecker checker=new DestinationChecker(this.context);checker.checkQueue("Queue1",true);checker.checkQueue("Queue2",true);checker.checkQueue("QueueDoesNotExist",false);checker.checkTopic("Topic1",true);checker.checkTopic("TopicDoesNotExist",false);}

	private TransportConfiguration assertInVmConnectionFactory(
			HornetQConnectionFactory connectionFactory) {
		TransportConfiguration transportConfig = getSingleTransportConfiguration(connectionFactory);
		assertEquals(InVMConnectorFactory.class.getName(),
				transportConfig.getFactoryClassName());
		return transportConfig;
	}

	private TransportConfiguration assertNettyConnectionFactory(
			HornetQConnectionFactory connectionFactory, String host, int port) {
		TransportConfiguration transportConfig = getSingleTransportConfiguration(connectionFactory);
		assertEquals(NettyConnectorFactory.class.getName(),
				transportConfig.getFactoryClassName());
		assertEquals(host, transportConfig.getParams().get("host"));
		assertEquals(port, transportConfig.getParams().get("port"));
		return transportConfig;
	}

	private TransportConfiguration getSingleTransportConfiguration(
			HornetQConnectionFactory connectionFactory) {
		TransportConfiguration[] transportConfigurations = connectionFactory
				.getServerLocator().getStaticTransportConfigurations();
		assertEquals(1, transportConfigurations.length);
		return transportConfigurations[0];
	}

	private void load(Class<?> config, String... environment) {
		this.context = doLoad(config, environment);
	}

	private AnnotationConfigApplicationContext doLoad(Class<?> config,
			String... environment) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(config);
		applicationContext.register(HornetQAutoConfigurationWithoutXA.class,
				JmsAutoConfiguration.class);
		EnvironmentTestUtils.addEnvironment(applicationContext, environment);
		applicationContext.refresh();
		return applicationContext;
	}

	private static class DestinationChecker {

		private final JmsTemplate jmsTemplate;

		private final DestinationResolver destinationResolver;

		private DestinationChecker(ApplicationContext applicationContext) {
			this.jmsTemplate = applicationContext.getBean(JmsTemplate.class);
			this.destinationResolver = new DynamicDestinationResolver();
		}

		public void checkQueue(String name, boolean shouldExist) {
			checkDestination(name, false, shouldExist);
		}

		public void checkTopic(String name, boolean shouldExist) {
			checkDestination(name, true, shouldExist);
		}

		public void checkDestination(final String name, final boolean pubSub,
				final boolean shouldExist) {
			this.jmsTemplate.execute(new SessionCallback<Void>() {
				@Override
				public Void doInJms(Session session) throws JMSException {
					try {
						Destination destination = DestinationChecker.this.destinationResolver
								.resolveDestinationName(session, name, pubSub);
						if (!shouldExist) {
							throw new IllegalStateException("Destination '" + name
									+ "' was not expected but got " + destination);
						}
					}
					catch (JMSException e) {
						if (shouldExist) {
							throw new IllegalStateException("Destination '" + name
									+ "' was expected but got " + e.getMessage());
						}
					}
					return null;
				}
			});
		}
	}

	@Configuration
	protected static class EmptyConfiguration {
	}

	@Configuration
	protected static class DestinationConfiguration {

		@Bean
		JMSQueueConfiguration sampleQueueConfiguration() {
			return new JMSQueueConfigurationImpl("sampleQueue", "foo=bar", false,
					"/queue/1");
		}

		@Bean
		TopicConfiguration sampleTopicConfiguration() {
			return new TopicConfigurationImpl("sampleTopic", "/topic/1");
		}
	}

	@Configuration
	protected static class CustomJmsConfiguration {

		@Bean
		public JMSConfiguration myJmsConfiguration() {
			JMSConfiguration config = new JMSConfigurationImpl();
			config.getQueueConfigurations().add(
					new JMSQueueConfigurationImpl("custom", null, false));
			return config;
		}
	}

	@Configuration
	protected static class CustomHornetQConfiguration {

		@Autowired
		private HornetQProperties properties;

		@Bean
		public HornetQConfigurationCustomizer myHornetQCustomize() {
			return new HornetQConfigurationCustomizer() {
				@Override
				public void customize(org.hornetq.core.config.Configuration configuration) {
					configuration.setClusterPassword("Foobar");
					configuration.setName("customFooBar");
				}
			};
		}
	}

	@Configuration
	@EnableConfigurationProperties(HornetQProperties.class)
	@Import({ HornetQEmbeddedServerConfiguration.class,
			HornetQConnectionFactoryConfiguration.class })
	protected static class HornetQAutoConfigurationWithoutXA {
	}

}
