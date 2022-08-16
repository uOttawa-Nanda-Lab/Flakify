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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.DefaultResourceLoader;

import static org.junit.Assert.assertThat;

/**
 * Tests for {@link AutoConfigurationSorter}.
 *
 * @author Phillip Webb
 */
public class AutoConfigurationSorterTests {

	private static final String LOWEST = OrderLowest.class.getName();
	private static final String HIGHEST = OrderHighest.class.getName();
	private static final String A = AutoConfigureA.class.getName();
	private static final String B = AutoConfigureB.class.getName();
	private static final String C = AutoConfigureC.class.getName();
	private static final String D = AutoConfigureD.class.getName();
	private static final String E = AutoConfigureE.class.getName();
	private static final String W = AutoConfigureW.class.getName();
	private static final String X = AutoConfigureX.class.getName();
	private static final String Y = AutoConfigureY.class.getName();
	private static final String Z = AutoConfigureZ.class.getName();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private AutoConfigurationSorter sorter;

	@Before
	public void setup() {
		this.sorter = new AutoConfigurationSorter(new DefaultResourceLoader());
	}

	@Test public void byAutoConfigureMixedBeforeAndAfter() throws Exception{List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(A,B,C,W,X));assertThat(actual,nameMatcher(C,W,B,A,X));}

	private Matcher<? super List<String>> nameMatcher(String... names) {

		final List<String> list = Arrays.asList(names);

		return new IsEqual<List<String>>(list) {

			@Override
			public void describeMismatch(Object item, Description description) {
				@SuppressWarnings("unchecked")
				List<String> items = (List<String>) item;
				description.appendText("was ").appendValue(prettify(items));
			}

			@Override
			public void describeTo(Description description) {
				description.appendValue(prettify(list));
			}

			private String prettify(List<String> items) {
				List<String> pretty = new ArrayList<String>();
				for (String item : items) {
					if (item.contains("$AutoConfigure")) {
						item = item.substring(item.indexOf("$AutoConfigure")
								+ "$AutoConfigure".length());
					}
					pretty.add(item);
				}
				return pretty.toString();
			}
		};

	}

	@Order(Ordered.LOWEST_PRECEDENCE)
	public static class OrderLowest {
	}

	@Order(Ordered.HIGHEST_PRECEDENCE)
	public static class OrderHighest {
	}

	@AutoConfigureAfter(AutoConfigureB.class)
	public static class AutoConfigureA {
	}

	@AutoConfigureAfter({ AutoConfigureC.class, AutoConfigureD.class,
			AutoConfigureE.class })
	public static class AutoConfigureB {
	}

	public static class AutoConfigureC {
	}

	@AutoConfigureAfter(AutoConfigureA.class)
	public static class AutoConfigureD {
	}

	public static class AutoConfigureE {
	}

	@AutoConfigureBefore(AutoConfigureB.class)
	public static class AutoConfigureW {
	}

	public static class AutoConfigureX {
	}

	@AutoConfigureBefore(AutoConfigureX.class)
	public static class AutoConfigureY {
	}

	@AutoConfigureBefore(AutoConfigureY.class)
	public static class AutoConfigureZ {
	}

}
