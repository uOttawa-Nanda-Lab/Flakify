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
package org.assertj.core.internal.objectarrays;

import static org.assertj.core.error.ShouldNotContain.shouldNotContain;
import static org.assertj.core.test.ErrorMessages.iterableValuesToLookForIsEmpty;
import static org.assertj.core.test.ErrorMessages.iterableValuesToLookForIsNull;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.core.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.ObjectArraysBaseTest;
import org.junit.Test;

public class ObjectArrays_assertDoesNotContainAnyElementsOf_Test extends ObjectArraysBaseTest {

  

  // ------------------------------------------------------------------------------------------------------------------
  // tests using a custom comparison strategy
  // ------------------------------------------------------------------------------------------------------------------

  @Test
  public void should_fail_if_actual_contains_one_element_of_given_iterable_according_to_custom_comparison_strategy() {
	AssertionInfo info = someInfo();
	List<String> expected = newArrayList("LuKe", "YODA", "Han");
	try {
	  arraysWithCustomComparisonStrategy.assertDoesNotContainAnyElementsOf(info, actual, expected);
	} catch (AssertionError e) {
	  verify(failures).failure(info, shouldNotContain(actual, expected.toArray(), newLinkedHashSet("LuKe", "YODA"),
		                                              caseInsensitiveStringComparisonStrategy));
	  return;
	}
	failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
