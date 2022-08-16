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

package samples.websocket.echo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import samples.websocket.client.GreetingService;
import samples.websocket.client.SimpleClientWebSocketHandler;
import samples.websocket.client.SimpleGreetingService;
import samples.websocket.config.SampleWebSocketsApplication;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleWebSocketsApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class SampleWebSocketsApplicationTests {

	private static Log logger = LogFactory.getLog(SampleWebSocketsApplicationTests.class);

	private static String WS_URI;

	@Value("${local.server.port}")
	private int port;

	@Before
	public void init() {
		WS_URI = "ws://localhost:" + this.port + "/echo/websocket";
	}

	@Test
	public void runAndWait() throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(
				ClientConfiguration.class, "--spring.main.web_environment=false");
		long count = context.getBean(ClientConfiguration.class).latch.getCount();
		context.close();
		assertEquals(0, count);
	}

	@Configuration
	static class ClientConfiguration implements CommandLineRunner {

		private final CountDownLatch latch = new CountDownLatch(1);

		@Override
		public void run(String... args) throws Exception {
			logger.info("Waiting for response: latch=" + this.latch.getCount());
			this.latch.await(10, TimeUnit.SECONDS);
			logger.info("Got response: latch=" + this.latch.getCount());
		}

		@Bean
		public WebSocketConnectionManager wsConnectionManager() {

			WebSocketConnectionManager manager = new WebSocketConnectionManager(client(),
					handler(), WS_URI);
			manager.setAutoStartup(true);

			return manager;
		}

		@Bean
		public StandardWebSocketClient client() {
			return new StandardWebSocketClient();
		}

		@Bean
		public SimpleClientWebSocketHandler handler() {
			return new SimpleClientWebSocketHandler(greetingService(), this.latch);
		}

		@Bean
		public GreetingService greetingService() {
			return new SimpleGreetingService();
		}
	}

}
