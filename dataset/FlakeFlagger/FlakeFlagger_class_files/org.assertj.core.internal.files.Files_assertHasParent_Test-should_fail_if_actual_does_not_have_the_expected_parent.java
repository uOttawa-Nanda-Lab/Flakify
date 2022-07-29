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

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.FilesBaseTest;
import org.assertj.core.util.FilesException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.error.ShouldHaveParent.shouldHaveParent;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for
 * <code>{@link org.assertj.core.internal.Files#assertHasParent(org.assertj.core.api.AssertionInfo, java.io.File, java.io.File)}</code>
 * .
 * 
 * @author Jean-Christophe Gay
 */
public class Files_assertHasParent_Test extends FilesBaseTest {

  private File actual = new File("./some/test");
  private File expectedParent = new File("./some");

  @Test public void should_fail_if_actual_does_not_have_the_expected_parent() throws Exception{AssertionInfo info=someInfo();File expectedParent=new File("./expected-parent");try {files.assertHasParent(info,actual,expectedParent);} catch (AssertionError e){verify(failures).failure(info,shouldHaveParent(actual,expectedParent));return;}failBecauseExpectedAssertionErrorWasNotThrown();}
}
