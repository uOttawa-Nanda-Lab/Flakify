/*
 * Copyright 2013-2014 the original author or authors.
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

package org.springframework.boot.actuate.autoconfigure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.crsh.auth.AuthenticationPlugin;
import org.crsh.auth.JaasAuthenticationPlugin;
import org.crsh.lang.impl.groovy.GroovyRepl;
import org.crsh.plugin.PluginContext;
import org.crsh.plugin.PluginLifeCycle;
import org.crsh.plugin.ResourceKind;
import org.crsh.telnet.term.processor.ProcessorIOHandler;
import org.crsh.vfs.Resource;
import org.junit.After;
import org.junit.Test;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link CrshAutoConfiguration}.
 *
 * @author Christian Dupuis
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CrshAutoConfigurationTests {

	private AnnotationConfigWebApplicationContext context;

	@Test public void testJaasAuthenticationProvider(){MockEnvironment env=new MockEnvironment();env.setProperty("shell.auth","jaas");env.setProperty("shell.auth.jaas.domain","my-test-domain");this.context=new AnnotationConfigWebApplicationContext();this.context.setEnvironment(env);this.context.setServletContext(new MockServletContext());this.context.register(SecurityConfiguration.class);this.context.register(CrshAutoConfiguration.class);this.context.refresh();PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);assertEquals("jaas",lifeCycle.getConfig().get("crash.auth"));assertEquals("my-test-domain",lifeCycle.getConfig().get("crash.auth.jaas.domain"));}

	@Configuration
	public static class SecurityConfiguration {

		public static final String USERNAME = UUID.randomUUID().toString();

		public static final String PASSWORD = UUID.randomUUID().toString();

		@Bean
		public AuthenticationManager authenticationManager() {
			return new AuthenticationManager() {

				@Override
				public Authentication authenticate(Authentication authentication)
						throws AuthenticationException {
					if (authentication.getName().equals(USERNAME)
							&& authentication.getCredentials().equals(PASSWORD)) {
						authentication = new UsernamePasswordAuthenticationToken(
								authentication.getPrincipal(),
								authentication.getCredentials(),
								Collections
										.singleton(new SimpleGrantedAuthority("ADMIN")));
					}
					else {
						throw new BadCredentialsException("Invalid username and password");
					}
					return authentication;
				}
			};
		}

		@Bean
		public AccessDecisionManager shellAccessDecisionManager() {
			List<AccessDecisionVoter> voters = new ArrayList<AccessDecisionVoter>();
			RoleVoter voter = new RoleVoter();
			voter.setRolePrefix("");
			voters.add(voter);
			AccessDecisionManager result = new UnanimousBased(voters);
			return result;
		}

	}

}
