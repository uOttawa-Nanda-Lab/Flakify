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

package org.springframework.boot.actuate.endpoint.mvc;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link HealthMvcEndpoint}.
 *
 * @author Christian Dupuis
 * @author Dave Syer
 */
public class HealthMvcEndpointTests {

	private HealthEndpoint endpoint = null;

	private HealthMvcEndpoint mvc = null;

	private UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
			"user", "password",
			AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

	@Before
	public void init() {
		this.endpoint = mock(HealthEndpoint.class);
		given(this.endpoint.isEnabled()).willReturn(true);
		this.mvc = new HealthMvcEndpoint(this.endpoint);
	}

	@SuppressWarnings("unchecked") @Test public void customMapping(){given(this.endpoint.invoke()).willReturn(new Health.Builder().status("OK").build());this.mvc.setStatusMapping(Collections.singletonMap("OK",HttpStatus.INTERNAL_SERVER_ERROR));Object result=this.mvc.invoke(null);assertTrue(result instanceof ResponseEntity);ResponseEntity<Health> response=(ResponseEntity<Health>)result;assertTrue(response.getBody().getStatus().equals(new Status("OK")));assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());}

}
