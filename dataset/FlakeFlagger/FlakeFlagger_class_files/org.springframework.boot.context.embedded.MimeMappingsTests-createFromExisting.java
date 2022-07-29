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

package org.springframework.boot.context.embedded;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link MimeMappings}.
 *
 * @author Phillip Webb
 */
public class MimeMappingsTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test public void createFromExisting() throws Exception{MimeMappings mappings=new MimeMappings();mappings.add("foo","bar");MimeMappings clone=new MimeMappings(mappings);mappings.add("baz","bar");assertThat(clone.get("foo"),equalTo("bar"));assertThat(clone.get("baz"),nullValue());}

	private MimeMappings getTomatDefaults() {
		final MimeMappings mappings = new MimeMappings();
		Context ctx = mock(Context.class);
		Wrapper wrapper = mock(Wrapper.class);
		given(ctx.createWrapper()).willReturn(wrapper);
		willAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				mappings.add((String) args[0], (String) args[1]);
				return null;
			}
		}).given(ctx).addMimeMapping(anyString(), anyString());
		Tomcat.initWebappDefaults(ctx);
		return mappings;
	}

}
