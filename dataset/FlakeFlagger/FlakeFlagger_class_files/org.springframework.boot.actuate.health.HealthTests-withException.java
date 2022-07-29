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

package org.springframework.boot.actuate.health;

import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link Health}.
 *
 * @author Phillip Webb
 */
public class HealthTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void withException() throws Exception {
		RuntimeException ex = new RuntimeException("bang");
		Health health = new Health.Builder(Status.UP, Collections.singletonMap("a", "b"))
				.withException(ex).build();
		assertThat(health.getDetails().get("a"), equalTo((Object) "b"));
		assertThat(health.getDetails().get("error"),
				equalTo((Object) "java.lang.RuntimeException: bang"));
	}

}
