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

package org.springframework.boot.maven;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.shared.artifact.filter.collection.ArtifactFilterException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link ExcludeFilter}.
 *
 * @author Stephane Nicoll
 */
@SuppressWarnings("rawtypes")
public class ExcludeFilterTests {

	@Test public void excludeClassifierNoTargetClassifier() throws ArtifactFilterException{ExcludeFilter filter=new ExcludeFilter(Arrays.asList(createExclude("com.foo","bar","jdk5")));Artifact artifact=createArtifact("com.foo","bar");Set result=filter.filter(Collections.singleton(artifact));assertEquals("Should not have been filtered",1,result.size());assertSame(artifact,result.iterator().next());}

	private Exclude createExclude(String groupId, String artifactId, String classifier) {
		Exclude e = new Exclude();
		e.setGroupId(groupId);
		e.setArtifactId(artifactId);
		if (classifier != null) {
			e.setClassifier(classifier);
		}
		return e;
	}

	private Exclude createExclude(String groupId, String artifactId) {
		return createExclude(groupId, artifactId, null);
	}

	private Artifact createArtifact(String groupId, String artifactId, String classifier) {
		Artifact a = mock(Artifact.class);
		given(a.getGroupId()).willReturn(groupId);
		given(a.getArtifactId()).willReturn(artifactId);
		given(a.getClassifier()).willReturn(classifier);
		return a;
	}

	private Artifact createArtifact(String groupId, String artifactId) {
		return createArtifact(groupId, artifactId, null);
	}
}
