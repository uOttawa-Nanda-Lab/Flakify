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

package org.springframework.boot.actuate.autoconfigure;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ManagementSecurityAutoConfiguration}.
 *
 * @author Dave Syer
 */
public class ManagementSecurityAutoConfigurationTests {

	private AnnotationConfigWebApplicationContext context;

	private UserDetails getUser() {
		ProviderManager parent = (ProviderManager) this.context
				.getBean(AuthenticationManager.class);
		DaoAuthenticationProvider provider = (DaoAuthenticationProvider) parent
				.getProviders().get(0);
		UserDetailsService service = (UserDetailsService) ReflectionTestUtils.getField(
				provider, "userDetailsService");
		UserDetails user = service.loadUserByUsername("user");
		return user;
	}

	@Test public void testDisableBasicAuthOnApplicationPaths() throws Exception{this.context=new AnnotationConfigWebApplicationContext();this.context.setServletContext(new MockServletContext());this.context.register(HttpMessageConvertersAutoConfiguration.class,EndpointAutoConfiguration.class,EndpointWebMvcAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,SecurityAutoConfiguration.class,ManagementSecurityAutoConfiguration.class,FallbackWebSecurityAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);EnvironmentTestUtils.addEnvironment(this.context,"security.basic.enabled:false");this.context.refresh();assertEquals(6,this.context.getBean(FilterChainProxy.class).getFilterChains().size());}

	@Configuration
	protected static class TestConfiguration {

		private AuthenticationManager authenticationManager;

		@Bean
		public AuthenticationManager myAuthenticationManager() {
			this.authenticationManager = new AuthenticationManager() {

				@Override
				public Authentication authenticate(Authentication authentication)
						throws AuthenticationException {
					return new TestingAuthenticationToken("foo", "bar");
				}
			};
			return this.authenticationManager;
		}

	}

}
