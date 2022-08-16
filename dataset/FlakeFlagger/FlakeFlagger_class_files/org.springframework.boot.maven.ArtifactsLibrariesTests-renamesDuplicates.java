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

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.logging.Log;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.loader.tools.Library;
import org.springframework.boot.loader.tools.LibraryCallback;
import org.springframework.boot.loader.tools.LibraryScope;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link ArtifactsLibraries}.
 *
 * @author Phillip Webb
 */
public class ArtifactsLibrariesTests {

	@Mock
	private Artifact artifact;

	private Set<Artifact> artifacts;

	private File file = new File(".");

	private ArtifactsLibraries libs;

	@Mock
	private LibraryCallback callback;

	@Captor
	private ArgumentCaptor<Library> libraryCaptor;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.artifacts = Collections.singleton(this.artifact);
		this.libs = new ArtifactsLibraries(this.artifacts, null, mock(Log.class));
		given(this.artifact.getFile()).willReturn(this.file);
	}

	@Test public void renamesDuplicates() throws Exception{Artifact artifact1=mock(Artifact.class);Artifact artifact2=mock(Artifact.class);given(artifact1.getType()).willReturn("jar");given(artifact1.getScope()).willReturn("compile");given(artifact1.getGroupId()).willReturn("g1");given(artifact1.getFile()).willReturn(new File("a"));given(artifact2.getType()).willReturn("jar");given(artifact2.getScope()).willReturn("compile");given(artifact2.getGroupId()).willReturn("g2");given(artifact2.getFile()).willReturn(new File("a"));this.artifacts=new LinkedHashSet<Artifact>(Arrays.asList(artifact1,artifact2));this.libs=new ArtifactsLibraries(this.artifacts,null,mock(Log.class));this.libs.doWithLibraries(this.callback);verify(this.callback,times(2)).library(this.libraryCaptor.capture());assertThat(this.libraryCaptor.getAllValues().get(0).getName(),equalTo("g1-a"));assertThat(this.libraryCaptor.getAllValues().get(1).getName(),equalTo("g2-a"));}

}
