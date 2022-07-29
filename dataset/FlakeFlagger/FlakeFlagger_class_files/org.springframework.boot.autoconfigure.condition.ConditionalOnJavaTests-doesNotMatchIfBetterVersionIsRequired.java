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

package org.springframework.boot.autoconfigure.condition;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava.JavaVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava.Range;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ConditionalOnJava}.
 *
 * @author Oliver Gierke
 * @author Phillip Webb
 */
public class ConditionalOnJavaTests {

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

	private final OnJavaCondition condition = new OnJavaCondition();

	@Test public void doesNotMatchIfBetterVersionIsRequired(){registerAndRefresh(Java9Required.class);assertPresent(false);}

	private void registerAndRefresh(Class<?> annotatedClasses) {
		this.context.register(annotatedClasses);
		this.context.refresh();
	}

	private void assertPresent(boolean expected) {
		int expectedNumber = expected ? 1 : 0;
		Matcher<Iterable<String>> matcher = iterableWithSize(expectedNumber);
		assertThat(this.context.getBeansOfType(String.class).values(), is(matcher));
	}

	@Configuration
	@ConditionalOnJava(JavaVersion.NINE)
	static class Java9Required {
		@Bean
		String foo() {
			return "foo";
		}
	}

	@Configuration
	@ConditionalOnJava(range = Range.OLDER_THAN, value = JavaVersion.SIX)
	static class Java5Required {
		@Bean
		String foo() {
			return "foo";
		}
	}

	@Configuration
	@ConditionalOnJava(JavaVersion.SIX)
	static class Java6Required {
		@Bean
		String foo() {
			return "foo";
		}
	}

}
