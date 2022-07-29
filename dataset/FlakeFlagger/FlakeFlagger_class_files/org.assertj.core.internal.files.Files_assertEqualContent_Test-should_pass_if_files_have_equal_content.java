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
package org.assertj.core.internal.files;

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

import static org.assertj.core.error.ShouldBeFile.shouldBeFile;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.error.ShouldHaveSameContent;
import org.assertj.core.internal.Files;
import org.assertj.core.internal.FilesBaseTest;
import org.assertj.core.util.FilesException;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Tests for <code>{@link Files#assertSameContentAs(AssertionInfo, File, File)}</code>.
 * 
 * @author Yvonne Wang
 * @author Joel Costigliola
 */
public class Files_assertEqualContent_Test extends FilesBaseTest {

  private static File actual;
  private static File expected;

  @BeforeClass
  public static void setUpOnce() {
    actual = new File("src/test/resources/actual_file.txt");
    expected = new File("src/test/resources/expected_file.txt");
  }

  @Test public void should_pass_if_files_have_equal_content() throws IOException{when(diff.diff(actual,expected)).thenReturn(new ArrayList<String>());files.assertSameContentAs(someInfo(),actual,expected);}
}
