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

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.CommandResult;
import com.mongodb.MongoException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link MongoHealthIndicator}.
 *
 * @author Christian Dupuis
 */
public class MongoHealthIndicatorTests {

	private AnnotationConfigApplicationContext context;

	@Test public void mongoIsUp() throws Exception{CommandResult commandResult=mock(CommandResult.class);given(commandResult.getString("version")).willReturn("2.6.4");MongoTemplate mongoTemplate=mock(MongoTemplate.class);given(mongoTemplate.executeCommand("{ buildInfo: 1 }")).willReturn(commandResult);MongoHealthIndicator healthIndicator=new MongoHealthIndicator(mongoTemplate);Health health=healthIndicator.health();assertEquals(Status.UP,health.getStatus());assertEquals("2.6.4",health.getDetails().get("version"));verify(commandResult).getString("version");verify(mongoTemplate).executeCommand("{ buildInfo: 1 }");}
}
