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

package org.springframework.boot.actuate.autoconfigure;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint;
import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.boot.actuate.endpoint.DumpEndpoint;
import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.endpoint.RequestMappingEndpoint;
import org.springframework.boot.actuate.endpoint.ShutdownEndpoint;
import org.springframework.boot.actuate.endpoint.TraceEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link EndpointAutoConfiguration}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Greg Turnquist
 * @author Christian Dupuis
 * @author Stephane Nicoll
 */
public class EndpointAutoConfigurationTests {

	private AnnotationConfigApplicationContext context;

	@Test public void metricEndpointCustomPublicMetrics(){load(CustomPublicMetricsConfig.class,EndpointAutoConfiguration.class);MetricsEndpoint endpoint=this.context.getBean(MetricsEndpoint.class);Map<String, Object> metrics=endpoint.invoke();assertTrue(metrics.containsKey("foo"));assertTrue(metrics.containsKey("mem"));assertTrue(metrics.containsKey("heap.used"));}

	private void load(Class<?>... config) {
		this.context = new AnnotationConfigApplicationContext();
		this.context.register(config);
		this.context.refresh();
	}

	@Configuration
	static class CustomPublicMetricsConfig {

		@Bean
		PublicMetrics customPublicMetrics() {
			return new PublicMetrics() {
				@Override
				public Collection<Metric<?>> metrics() {
					Metric<Integer> metric = new Metric<Integer>("foo", 1);
					return Collections.<Metric<?>> singleton(metric);
				}
			};
		}

	}
}
