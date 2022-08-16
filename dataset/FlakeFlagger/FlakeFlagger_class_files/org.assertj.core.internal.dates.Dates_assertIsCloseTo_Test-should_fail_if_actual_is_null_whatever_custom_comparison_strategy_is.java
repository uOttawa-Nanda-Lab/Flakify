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
package org.assertj.core.internal.dates;

import static org.assertj.core.error.ShouldBeCloseTo.shouldBeCloseTo;
import static org.assertj.core.test.ErrorMessages.dateToCompareActualWithIsNull;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;


import static org.mockito.Mockito.verify;

import java.util.Date;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Dates;
import org.assertj.core.internal.DatesBaseTest;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for <code>{@link Dates#assertIsCloseTo(AssertionInfo, Date, Date, long)}</code>.
 * 
 * @author Joel Costigliola
 */
public class Dates_assertIsCloseTo_Test extends DatesBaseTest {

  private Date other;
  private int delta;

  @Override
  @Before
  public void setUp() {
    super.setUp();
    actual = parseDatetime("2011-01-01T03:15:05");
    delta = 100;
    other = new Date(actual.getTime() + delta + 1);
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    datesWithCustomComparisonStrategy.assertIsCloseTo(someInfo(), null, parseDate("2010-01-01"), 10);
  }

}
