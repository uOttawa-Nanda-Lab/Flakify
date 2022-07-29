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

  @Test public void use_custom_date_formats_first_then_defaults_to_parse_a_date(){final Date date=Dates.parse("2003-04-26");assertThat(date).isEqualTo("2003-04-26");try {assertThat(date).isEqualTo("2003/04/26");} catch (AssertionError e){assertThat(e).hasMessage("Failed to parse 2003/04/26 with any of these date formats: [" + "yyyy-MM-dd'T'HH:mm:ss.SSS, " + "yyyy-MM-dd HH:mm:ss.SSS, " + "yyyy-MM-dd'T'HH:mm:ss, " + "yyyy-MM-dd]");}registerCustomDateFormat("yyyy/MM/dd");assertThat(date).isEqualTo("2003/04/26");assertThat(date).isEqualTo("2003-04-26");assertThat(date).isEqualTo("2003-04-26T00:00:00");try {assertThat(date).isEqualTo("2003 04 26");} catch (AssertionError e){assertThat(e).hasMessage("Failed to parse 2003 04 26 with any of these date formats: [" + "yyyy/MM/dd, " + "yyyy-MM-dd'T'HH:mm:ss.SSS, " + "yyyy-MM-dd HH:mm:ss.SSS, " + "yyyy-MM-dd'T'HH:mm:ss, " + "yyyy-MM-dd]");}registerCustomDateFormat("yyyy MM dd");assertThat(date).isEqualTo("2003 04 26");}

}
