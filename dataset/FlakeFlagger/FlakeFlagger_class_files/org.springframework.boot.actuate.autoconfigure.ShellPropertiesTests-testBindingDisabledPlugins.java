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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.crsh.plugin.PluginLifeCycle;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.actuate.autoconfigure.ShellProperties.CrshShellProperties;
import org.springframework.boot.actuate.autoconfigure.ShellProperties.JaasAuthenticationProperties;
import org.springframework.boot.actuate.autoconfigure.ShellProperties.KeyAuthenticationProperties;
import org.springframework.boot.actuate.autoconfigure.ShellProperties.SimpleAuthenticationProperties;
import org.springframework.boot.actuate.autoconfigure.ShellProperties.SpringAuthenticationProperties;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ShellProperties}.
 *
 * @author Christian Dupuis
 */
public class ShellPropertiesTests {

	@Test public void testBindingDisabledPlugins(){ShellProperties props=new ShellProperties();RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");binder.setConversionService(new DefaultConversionService());binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.disabled_plugins","pattern1, pattern2")));assertFalse(binder.getBindingResult().hasErrors());assertEquals(2,props.getDisabledPlugins().length);assertArrayEquals(new String[]{"pattern1","pattern2"},props.getDisabledPlugins());}

	@Configuration
	public static class TestShellConfiguration {

		public static String uuid = UUID.randomUUID().toString();
	}
}
