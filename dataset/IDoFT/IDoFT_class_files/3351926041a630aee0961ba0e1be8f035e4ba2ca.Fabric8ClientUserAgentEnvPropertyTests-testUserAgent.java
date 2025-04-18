/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.kubernetes.fabric8;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.kubernetes.example.App;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author wind57
 *
 * test "User-Agent" functionality via system properties
 */
@SpringBootTest(classes = App.class)
class Fabric8ClientUserAgentEnvPropertyTests {

	@Autowired
	private KubernetesClient client;

	@BeforeAll
	static void beforeAll() {
		System.setProperty(Config.KUBERNETES_USER_AGENT, "spring-k8s");
	}

	@AfterAll
	static void afterAll() {
		System.clearProperty(Config.KUBERNETES_USER_AGENT);
	}

	@Test
	void testUserAgent() {
		String userAgent = client.getConfiguration().getUserAgent();
		assertThat(userAgent).isEqualTo("spring-k8s");
	}

}
