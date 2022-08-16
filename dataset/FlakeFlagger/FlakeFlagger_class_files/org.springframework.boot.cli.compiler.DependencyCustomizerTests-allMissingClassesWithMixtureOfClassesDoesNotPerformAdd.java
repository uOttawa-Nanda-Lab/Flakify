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

package org.springframework.boot.cli.compiler;

import groovy.lang.Grab;
import groovy.lang.GroovyClassLoader;

import java.util.List;

import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.control.SourceUnit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.cli.compiler.dependencies.ArtifactCoordinatesResolver;
import org.springframework.boot.cli.compiler.grape.DependencyResolutionContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

/**
 * Tests for {@link DependencyCustomizer}
 *
 * @author Andy Wilkinson
 */
public class DependencyCustomizerTests {

	private final ModuleNode moduleNode = new ModuleNode((SourceUnit) null);

	private final ClassNode classNode = new ClassNode(DependencyCustomizerTests.class);

	@Mock
	private ArtifactCoordinatesResolver resolver;

	private DependencyCustomizer dependencyCustomizer;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		given(this.resolver.getGroupId("spring-boot-starter-logging")).willReturn(
				"org.springframework.boot");
		given(this.resolver.getArtifactId("spring-boot-starter-logging")).willReturn(
				"spring-boot-starter-logging");
		given(this.resolver.getVersion("spring-boot-starter-logging"))
				.willReturn("1.2.3");
		this.moduleNode.addClass(this.classNode);
		this.dependencyCustomizer = new DependencyCustomizer(new GroovyClassLoader(
				getClass().getClassLoader()), this.moduleNode,
				new DependencyResolutionContext(this.resolver));
	}

	@Test public void allMissingClassesWithMixtureOfClassesDoesNotPerformAdd(){this.dependencyCustomizer.ifAllMissingClasses(getClass().getName(),"does.not.Exist").add("spring-boot-starter-logging");assertEquals(0,this.classNode.getAnnotations(new ClassNode(Grab.class)).size());}

	private void assertGrabAnnotation(AnnotationNode annotationNode, String group,
			String module, String version, String classifier, String type,
			boolean transitive) {
		assertEquals(group, getMemberValue(annotationNode, "group"));
		assertEquals(module, getMemberValue(annotationNode, "module"));
		assertEquals(version, getMemberValue(annotationNode, "version"));
		if (type == null) {
			assertNull(annotationNode.getMember("type"));
		}
		else {
			assertEquals(type, getMemberValue(annotationNode, "type"));
		}
		if (classifier == null) {
			assertNull(annotationNode.getMember("classifier"));
		}
		else {
			assertEquals(classifier, getMemberValue(annotationNode, "classifier"));
		}
		assertEquals(transitive, getMemberValue(annotationNode, "transitive"));
	}

	private Object getMemberValue(AnnotationNode annotationNode, String member) {
		return ((ConstantExpression) annotationNode.getMember(member)).getValue();
	}

}
