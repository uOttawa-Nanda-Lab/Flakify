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

package org.springframework.boot.autoconfigure.jersey;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfigurationDefaultServletPathTests.Application;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link JerseyAutoConfiguration} when using default servlet paths.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port=0")
@WebAppConfiguration
public class JerseyAutoConfigurationDefaultServletPathTests {

	@Value("${local.server.port}")
	private int port;

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void contextLoads() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/hello", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@MinimalWebConfiguration
	@Path("/hello")
	public static class Application extends ResourceConfig {

		@Value("${message:World}")
		private String msg;

		public Application() {
			register(Application.class);
		}

		@GET
		public String message() {
			return "Hello " + this.msg;
		}

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}

	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Import({ EmbeddedServletContainerAutoConfiguration.class,
			ServerPropertiesAutoConfiguration.class, JerseyAutoConfiguration.class,
			PropertyPlaceholderAutoConfiguration.class })
	protected static @interface MinimalWebConfiguration {
	}

}
