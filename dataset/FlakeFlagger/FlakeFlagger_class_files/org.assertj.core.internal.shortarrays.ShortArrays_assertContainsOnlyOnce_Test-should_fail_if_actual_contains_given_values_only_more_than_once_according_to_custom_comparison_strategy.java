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
package org.assertj.core.internal.shortarrays;

import static org.assertj.core.error.ShouldContainsOnlyOnce.shouldContainsOnlyOnce;
import static org.assertj.core.test.ErrorMessages.valuesToLookForIsNull;
import static org.assertj.core.test.ShortArrays.arrayOf;
import static org.assertj.core.test.ShortArrays.emptyArray;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.ShortArrays;
import org.assertj.core.internal.ShortArraysBaseTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ShortArrays#assertContainsOnlyOnce(AssertionInfo, short[], short[])}</code>.
 * 
 * @author William Delanoue
 */
public class ShortArrays_assertContainsOnlyOnce_Test extends ShortArraysBaseTest {

  @Test
  public void should_fail_if_actual_contains_given_values_only_more_than_once_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    actual = arrayOf(6, -8, 10, -6, -8, 10, -8);
    short[] expected = { 6, -8, 20 };
    try {
      arraysWithCustomComparisonStrategy.assertContainsOnlyOnce(info, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(
          info,
          shouldContainsOnlyOnce(actual, expected, newLinkedHashSet((short) 20),
              newLinkedHashSet((short) 6, (short) -8), absValueComparisonStrategy));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
