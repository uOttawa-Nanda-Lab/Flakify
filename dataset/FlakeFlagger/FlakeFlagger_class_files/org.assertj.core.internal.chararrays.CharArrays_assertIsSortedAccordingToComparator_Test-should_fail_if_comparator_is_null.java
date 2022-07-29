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
package org.assertj.core.internal.chararrays;

import static org.assertj.core.error.ShouldBeSorted.shouldBeSortedAccordingToGivenComparator;
import static org.assertj.core.test.CharArrays.emptyArray;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;


import static org.mockito.Mockito.verify;

import java.util.Comparator;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.CharArrays;
import org.assertj.core.internal.CharArraysBaseTest;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for <code>{@link CharArrays#assertIsSortedAccordingToComparator(AssertionInfo, char[], Comparator)}</code>
 * 
 * @author Joel Costigliola
 */
public class CharArrays_assertIsSortedAccordingToComparator_Test extends CharArraysBaseTest {

  private Comparator<Character> charDescendingOrderComparator;
  private Comparator<Character> charAscendingOrderComparator;

  @Override
  @Before
  public void setUp() {
    super.setUp();
    actual = new char[] { 'd', 'c', 'b', 'b', 'a' };
    charDescendingOrderComparator = new Comparator<Character>() {
      @Override
      public int compare(Character char1, Character char2) {
        return -char1.compareTo(char2);
      }
    };
    charAscendingOrderComparator = new Comparator<Character>() {
      @Override
      public int compare(Character char1, Character char2) {
        return -char1.compareTo(char2);
      }
    };
  }

  @Test
  public void should_fail_if_comparator_is_null() {
    thrown.expect(NullPointerException.class);
    arrays.assertIsSortedAccordingToComparator(someInfo(), emptyArray(), null);
  }

}
