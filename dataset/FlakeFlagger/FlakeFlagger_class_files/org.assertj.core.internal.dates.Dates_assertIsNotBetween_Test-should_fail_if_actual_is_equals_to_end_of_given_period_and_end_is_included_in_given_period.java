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

import static org.assertj.core.error.ShouldNotBeBetween.shouldNotBeBetween;
import static org.assertj.core.test.ErrorMessages.*;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;


import static org.mockito.Mockito.verify;

import java.util.Date;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Dates;
import org.assertj.core.internal.DatesBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link Dates#assertIsNotBetween(AssertionInfo, Date, Date, Date, boolean, boolean)}</code>.
 * 
 * @author Joel Costigliola
 */
public class Dates_assertIsNotBetween_Test extends DatesBaseTest {

  @Test public void should_fail_if_actual_is_equals_to_end_of_given_period_and_end_is_included_in_given_period(){AssertionInfo info=someInfo();actual=parseDate("2011-09-30");Date start=parseDate("2011-09-01");Date end=parseDate("2011-09-30");boolean inclusiveStart=false;boolean inclusiveEnd=true;try {dates.assertIsNotBetween(info,actual,start,end,inclusiveStart,inclusiveEnd);} catch (AssertionError e){verify(failures).failure(info,shouldNotBeBetween(actual,start,end,inclusiveStart,inclusiveEnd));return;}failBecauseExpectedAssertionErrorWasNotThrown();}

}
