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

import static org.assertj.core.error.ShouldBeEqualWithTimePrecision.shouldBeEqual;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.verify;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.DatesBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link org.assertj.core.internal.Dates#assertIsEqualWithPrecision(org.assertj.core.api.AssertionInfo, java.util.Date, java.util.Date, java.util.concurrent.TimeUnit)}</code>.
 *
 * @author William Delanoue
 */
public class Dates_assertIsEqualWithPrecision_Test extends DatesBaseTest {

  @Test public void should_fail_if_hour_fields_differ_but_are_equal_when_am_pm_not_taken_into_account(){AssertionInfo info=someInfo();final Date now=new Date();Calendar calendar1=Calendar.getInstance();calendar1.setTime(now);calendar1.set(Calendar.HOUR_OF_DAY,18);Calendar calendar2=Calendar.getInstance();calendar2.setTime(now);calendar2.set(Calendar.HOUR_OF_DAY,6);Date date1=calendar1.getTime();Date date2=calendar2.getTime();try {dates.assertIsEqualWithPrecision(info,date1,date2,TimeUnit.MINUTES);} catch (AssertionError e){verify(failures).failure(info,shouldBeEqual(date1,date2,TimeUnit.MINUTES));return;}failBecauseExpectedAssertionErrorWasNotThrown();}

}
