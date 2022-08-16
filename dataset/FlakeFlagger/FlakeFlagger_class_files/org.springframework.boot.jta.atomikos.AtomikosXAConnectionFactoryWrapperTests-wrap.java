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

package org.springframework.boot.jta.atomikos;

import javax.jms.ConnectionFactory;
import javax.jms.XAConnectionFactory;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link AtomikosXAConnectionFactoryWrapper}.
 *
 * @author Phillip Webb
 */
public class AtomikosXAConnectionFactoryWrapperTests {

	@Test public void wrap(){XAConnectionFactory connectionFactory=mock(XAConnectionFactory.class);AtomikosXAConnectionFactoryWrapper wrapper=new AtomikosXAConnectionFactoryWrapper();ConnectionFactory wrapped=wrapper.wrapConnectionFactory(connectionFactory);assertThat(wrapped,instanceOf(AtomikosConnectionFactoryBean.class));assertThat(((AtomikosConnectionFactoryBean)wrapped).getXaConnectionFactory(),sameInstance(connectionFactory));}

}
