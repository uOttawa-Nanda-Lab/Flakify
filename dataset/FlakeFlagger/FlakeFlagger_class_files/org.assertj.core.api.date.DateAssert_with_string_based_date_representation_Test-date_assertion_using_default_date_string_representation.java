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
package org.assertj.core.api.date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.assertj.core.api.Assertions.useDefaultDateFormatsOnly;
import static org.assertj.core.test.ExpectedException.none;
import static org.assertj.core.util.Dates.parseDatetime;
import static org.assertj.core.util.Dates.parseDatetimeWithMs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.assertj.core.api.DateAssertBaseTest;
import org.assertj.core.test.ExpectedException;
import org.assertj.core.util.Dates;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests the default date format used when using date assertions with date represented as string.
 *
 * @author Joel Costigliola
 */
public class DateAssert_with_string_based_date_representation_Test extends DateAssertBaseTest {

  @Rule
  public ExpectedException thrown = none();

  @Test public void date_assertion_using_default_date_string_representation(){final Date date1timeWithMS=parseDatetimeWithMs("2003-04-26T03:01:02.999");assertThat(date1timeWithMS).isEqualTo("2003-04-26T03:01:02.999");final Date datetime=parseDatetime("2003-04-26T03:01:02");assertThat(datetime).isEqualTo("2003-04-26T03:01:02.000");assertThat(datetime).isEqualTo("2003-04-26T03:01:02");final Date date=Dates.parse("2003-04-26");assertThat(date).isEqualTo("2003-04-26");assertThat(date).isEqualTo("2003-04-26T00:00:00");assertThat(date).isEqualTo("2003-04-26T00:00:00.000");}

}
