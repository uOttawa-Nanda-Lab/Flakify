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

package org.springframework.boot.cli.compiler.grape;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.repository.Authentication;
import org.eclipse.aether.repository.RemoteRepository;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link AetherGrapeEngine}.
 *
 * @author Andy Wilkinson
 */
public class AetherGrapeEngineTests {

	private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

	private final AetherGrapeEngine grapeEngine = createGrapeEngine();

	private AetherGrapeEngine createGrapeEngine() {
		return AetherGrapeEngineFactory.create(this.groovyClassLoader, Arrays
				.asList(new RepositoryConfiguration("central", URI
						.create("http://repo1.maven.org/maven2"), false)),
				new DependencyResolutionContext());
	}

	@Test public void resolutionWithCustomResolver(){Map<String, Object> args=new HashMap<String, Object>();this.grapeEngine.addResolver(createResolver("restlet.org","http://maven.restlet.org"));this.grapeEngine.grab(args,createDependency("org.restlet","org.restlet","1.1.6"));assertEquals(1,this.groovyClassLoader.getURLs().length);}

	private Map<String, Object> createDependency(String group, String module,
			String version) {
		Map<String, Object> dependency = new HashMap<String, Object>();
		dependency.put("group", group);
		dependency.put("module", module);
		dependency.put("version", version);
		return dependency;
	}

	private Map<String, Object> createDependency(String group, String module,
			String version, boolean transitive) {
		Map<String, Object> dependency = createDependency(group, module, version);
		dependency.put("transitive", transitive);
		return dependency;
	}

	private Map<String, Object> createResolver(String name, String url) {
		Map<String, Object> resolver = new HashMap<String, Object>();
		resolver.put("name", name);
		resolver.put("root", url);
		return resolver;
	}

	private Map<String, Object> createExclusion(String group, String module) {
		Map<String, Object> exclusion = new HashMap<String, Object>();
		exclusion.put("group", group);
		exclusion.put("module", module);
		return exclusion;
	}

	private void doWithCustomUserHome(Runnable action) {
		doWithSystemProperty("user.home",
				new File("src/test/resources").getAbsolutePath(), action);
	}

	private void doWithSystemProperty(String key, String value, Runnable action) {
		String previousValue = setOrClearSystemProperty(key, value);
		try {
			action.run();
		}
		finally {
			setOrClearSystemProperty(key, previousValue);
		}
	}

	private String setOrClearSystemProperty(String key, String value) {
		if (value != null) {
			return System.setProperty(key, value);
		}
		return System.clearProperty(key);
	}
}
