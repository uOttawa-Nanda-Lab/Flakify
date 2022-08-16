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

import java.util.Arrays;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.PackageNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.io.ReaderSource;
import org.codehaus.groovy.transform.ASTTransformation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.cli.compiler.dependencies.ArtifactCoordinatesResolver;
import org.springframework.boot.cli.compiler.grape.DependencyResolutionContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link ResolveDependencyCoordinatesTransformation}
 *
 * @author Andy Wilkinson
 */
public final class ResolveDependencyCoordinatesTransformationTests {

	private final SourceUnit sourceUnit = new SourceUnit((String) null,
			(ReaderSource) null, null, null, null);

	private final ModuleNode moduleNode = new ModuleNode(this.sourceUnit);

	private final AnnotationNode grabAnnotation = createGrabAnnotation();

	private final ArtifactCoordinatesResolver coordinatesResolver = mock(ArtifactCoordinatesResolver.class);

	private final DependencyResolutionContext resolutionContext = new DependencyResolutionContext(
			this.coordinatesResolver);

	private final ASTTransformation transformation = new ResolveDependencyCoordinatesTransformation(
			this.resolutionContext);

	@Before
	public void setupExpectations() {
		given(this.coordinatesResolver.getGroupId("spring-core")).willReturn(
				"org.springframework");
		given(this.coordinatesResolver.getVersion("spring-core")).willReturn("4.0.0.RC1");
	}

	@Test public void transformationOfAnnotationOnStaticImport(){this.moduleNode.addStaticImport(null,null,null,Arrays.asList(this.grabAnnotation));assertGrabAnnotationHasBeenTransformation();}

	private AnnotationNode createGrabAnnotation() {
		ClassNode classNode = new ClassNode(Grab.class);
		AnnotationNode annotationNode = new AnnotationNode(classNode);
		annotationNode.addMember("value", new ConstantExpression("spring-core"));
		return annotationNode;
	}

	private void assertGrabAnnotationHasBeenTransformation() {
		this.transformation.visit(new ASTNode[] { this.moduleNode }, this.sourceUnit);

		assertEquals("org.springframework", getGrabAnnotationMemberAsString("group"));
		assertEquals("spring-core", getGrabAnnotationMemberAsString("module"));
		assertEquals("4.0.0.RC1", getGrabAnnotationMemberAsString("version"));
	}

	private Object getGrabAnnotationMemberAsString(String memberName) {
		Expression expression = this.grabAnnotation.getMember(memberName);
		if (expression instanceof ConstantExpression) {
			return ((ConstantExpression) expression).getValue();
		}
		else if (expression == null) {
			return null;
		}
		else {
			throw new IllegalStateException("Member '" + memberName
					+ "' is not a ConstantExpression");
		}
	}

}
