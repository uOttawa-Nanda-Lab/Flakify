/*
 * Copyright 2012-2013 the original author or authors.
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

package org.springframework.boot.actuate.endpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link MetricsEndpoint}.
 *
 * @author Phillip Webb
 */
public class MetricsEndpointTests extends AbstractEndpointTests<MetricsEndpoint> {

	private Metric<Number> metric1 = new Metric<Number>("a", 1);

	private Metric<Number> metric2 = new Metric<Number>("b", 2);;

	private Metric<Number> metric3 = new Metric<Number>("c", 3);;

	@Test
	public void invoke() throws Exception {
		assertThat(getEndpointBean().invoke().get("a"), equalTo((Object) 0.5f));
	}

	private static class TestPublicMetrics implements PublicMetrics, Ordered {

		private final int order;

		private final List<Metric<?>> metrics;

		public TestPublicMetrics(int order, Metric<?>... metrics) {
			this.order = order;
			this.metrics = Arrays.asList(metrics);
		}

		@Override
		public int getOrder() {
			return this.order;
		}

		@Override
		public Collection<Metric<?>> metrics() {
			return this.metrics;
		}

	}

	@Configuration
	@EnableConfigurationProperties
	public static class Config {

		@Bean
		public MetricsEndpoint endpoint() {
			final Metric<Float> metric = new Metric<Float>("a", 0.5f);
			PublicMetrics metrics = new PublicMetrics() {
				@Override
				public Collection<Metric<?>> metrics() {
					return Collections.<Metric<?>> singleton(metric);
				}
			};
			return new MetricsEndpoint(metrics);
		}

	}
}
