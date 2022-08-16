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

package org.springframework.boot.actuate.endpoint;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.repository.InMemoryMetricRepository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link VanillaPublicMetrics}.
 *
 * @author Phillip Webb
 * @author Christian Dupuis
 */
@Deprecated
public class VanillaPublicMetricsTests {

	@Test
	public void testSystemMetrics() throws Exception {
		InMemoryMetricRepository repository = new InMemoryMetricRepository();
		repository.set(new Metric<Double>("a", 0.5, new Date()));
		VanillaPublicMetrics publicMetrics = new VanillaPublicMetrics(repository);
		Map<String, Metric<?>> results = new HashMap<String, Metric<?>>();
		for (Metric<?> metric : publicMetrics.metrics()) {
			results.put(metric.getName(), metric);
		}
		assertTrue(results.containsKey("mem"));
		assertTrue(results.containsKey("mem.free"));
		assertTrue(results.containsKey("processors"));
		assertTrue(results.containsKey("uptime"));

		assertTrue(results.containsKey("heap.committed"));
		assertTrue(results.containsKey("heap.init"));
		assertTrue(results.containsKey("heap.used"));
		assertTrue(results.containsKey("heap"));

		assertTrue(results.containsKey("threads.peak"));
		assertTrue(results.containsKey("threads.daemon"));
		assertTrue(results.containsKey("threads"));

		assertTrue(results.containsKey("classes.loaded"));
		assertTrue(results.containsKey("classes.unloaded"));
		assertTrue(results.containsKey("classes"));
	}
}
