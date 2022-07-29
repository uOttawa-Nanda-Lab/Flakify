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

import java.util.Properties;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link RedisHealthIndicator}.
 *
 * @author Christian Dupuis
 */
public class RedisHealthIndicatorTests {

	private AnnotationConfigApplicationContext context;

	@Test public void redisIsDown() throws Exception{RedisConnection redisConnection=mock(RedisConnection.class);RedisConnectionFactory redisConnectionFactory=mock(RedisConnectionFactory.class);given(redisConnectionFactory.getConnection()).willReturn(redisConnection);given(redisConnection.info()).willThrow(new RedisConnectionFailureException("Connection failed"));RedisHealthIndicator healthIndicator=new RedisHealthIndicator(redisConnectionFactory);Health health=healthIndicator.health();assertEquals(Status.DOWN,health.getStatus());assertTrue(((String)health.getDetails().get("error")).contains("Connection failed"));verify(redisConnectionFactory).getConnection();verify(redisConnection).info();}
}
