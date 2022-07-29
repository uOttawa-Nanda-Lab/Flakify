/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.internal.paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.failBecauseExceptionWasNotThrown;
import static org.assertj.core.error.ShouldBeReadable.shouldBeReadable;
import static org.assertj.core.error.ShouldExist.shouldExist;
import static org.assertj.core.error.ShouldHaveContent.shouldHaveContent;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Paths;
import org.assertj.core.internal.PathsBaseTest;
import org.assertj.core.util.FilesException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link Paths#assertHasContent(AssertionInfo, Path, String, Charset)}</code>.
 * 
 * @author Olivier Michallat
 * @author Joel Costigliola
 */
public class Paths_assertHasContent_Test extends PathsBaseTest {

  private static Path path;
  private static String expected;
  private static Charset charset;
  private Path mockPath;

  @BeforeClass
  public static void setUpOnce() {
	// Does not matter if the values differ, the actual comparison is mocked in this test
	path = new File("src/test/resources/actual_file.txt").toPath();
	expected = "xyz";
	charset = Charset.defaultCharset();
  }

  @Before
  public void init() {
	mockPath = mock(Path.class);
  }
  
  @Test public void should_fail_if_path_does_not_have_expected_text_content() throws IOException{List<String> diffs=newArrayList("line:1, expected:<line1> but was:<EOF>");when(diff.diff(path.toFile(),expected,charset)).thenReturn(diffs);when(nioFilesWrapper.exists(path)).thenReturn(true);when(nioFilesWrapper.isReadable(path)).thenReturn(true);AssertionInfo info=someInfo();try {paths.assertHasContent(info,path,expected,charset);} catch (AssertionError e){verify(failures).failure(info,shouldHaveContent(path.toFile(),charset,diffs));return;}failBecauseExpectedAssertionErrorWasNotThrown();}
}
