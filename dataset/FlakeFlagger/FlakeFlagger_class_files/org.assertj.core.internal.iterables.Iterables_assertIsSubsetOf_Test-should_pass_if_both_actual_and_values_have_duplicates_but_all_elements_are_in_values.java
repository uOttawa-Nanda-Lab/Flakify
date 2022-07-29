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
package org.assertj.core.internal.iterables;

import static org.assertj.core.error.ShouldBeSubsetOf.shouldBeSubsetOf;
import static org.assertj.core.test.ErrorMessages.iterableToLookForIsNull;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;


import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Iterables;
import org.assertj.core.internal.IterablesBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link Iterables#assertIsSubsetOf(AssertionInfo, Collection, Object[])}</code>.
 * 
 * @author Maciej Jaskowski
 */
public class Iterables_assertIsSubsetOf_Test extends IterablesBaseTest {

  @Test public void should_pass_if_both_actual_and_values_have_duplicates_but_all_elements_are_in_values(){actual=newArrayList("Yoda","Yoda","Yoda","C-3PO","Obi-Wan");iterables.assertIsSubsetOf(someInfo(),actual,newArrayList("Yoda","Yoda","C-3PO","C-3PO","Obi-Wan"));}

  // ------------------------------------------------------------------------------------------------------------------
  // tests using a custom comparison strategy
  // ------------------------------------------------------------------------------------------------------------------


}
