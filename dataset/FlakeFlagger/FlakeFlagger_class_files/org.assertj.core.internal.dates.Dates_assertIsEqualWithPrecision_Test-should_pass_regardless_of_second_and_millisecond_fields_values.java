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

  @Test public void should_pass_regardless_of_second_and_millisecond_fields_values(){AssertionInfo info=someInfo();Date other=parseDatetimeWithMs("2011-09-27T12:23:36.999");dates.assertIsEqualWithPrecision(info,actual,other,TimeUnit.SECONDS);}

}
