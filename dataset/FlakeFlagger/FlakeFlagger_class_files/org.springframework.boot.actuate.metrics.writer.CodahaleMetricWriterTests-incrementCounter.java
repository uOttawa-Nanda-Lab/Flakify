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

package org.springframework.boot.actuate.metrics.writer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.actuate.metrics.Metric;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Dave Syer
 */
public class CodahaleMetricWriterTests {

	private final MetricRegistry registry = new MetricRegistry();
	private final CodahaleMetricWriter writer = new CodahaleMetricWriter(this.registry);

	@Test public void incrementCounter(){this.writer.increment(new Delta<Number>("foo",2));this.writer.increment(new Delta<Number>("foo",1));assertEquals(3,this.registry.counter("foo").getCount());}

	public static class WriterThread extends Thread {
		private int index;
		private boolean failed;
		private CodahaleMetricWriter writer;

		public WriterThread(ThreadGroup group, int index, CodahaleMetricWriter writer) {
			super(group, "Writer-" + index);

			this.index = index;
			this.writer = writer;
		}

		public boolean isFailed() {
			return this.failed;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				try {
					Metric<Integer> metric1 = new Metric<Integer>("timer.test.service",
							this.index);
					this.writer.set(metric1);

					Metric<Integer> metric2 = new Metric<Integer>(
							"histogram.test.service", this.index);
					this.writer.set(metric2);

					Metric<Integer> metric3 = new Metric<Integer>("gauge.test.service",
							this.index);
					this.writer.set(metric3);
				}
				catch (IllegalArgumentException iae) {
					this.failed = true;
					throw iae;
				}
			}
		}
	}
}
