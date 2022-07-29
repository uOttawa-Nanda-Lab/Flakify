/*
 * Copyright 2007 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Tests {@link CalendarParsedResult}.
 *
 * @author Sean Owen
 */
public final class CalendarParsedResultTestCase extends Assert {

  private static final double EPSILON = 0.0000000001;

  private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.ENGLISH);
  static {
    DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  @Before
  public void setUp() {
    Locale.setDefault(Locale.ENGLISH);
    TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
  }

  @Test public void testOrganizer(){doTest("BEGIN:VCALENDAR\r\nBEGIN:VEVENT\r\n" + "DTSTART:20080504T123456Z\r\n" + "ORGANIZER:mailto:bob@example.org\r\n" + "END:VEVENT\r\nEND:VCALENDAR",null,null,null,"20080504T123456Z",null,"bob@example.org",null,Double.NaN,Double.NaN);}

  private static void doTest(String contents,
                             String description,
                             String summary,
                             String location,
                             String startString,
                             String endString) {
    doTest(contents, description, summary, location, startString, endString, null, null, Double.NaN, Double.NaN);
  }

  private static void doTest(String contents,
                             String description,
                             String summary,
                             String location,
                             String startString,
                             String endString,
                             String organizer,
                             String[] attendees,
                             double latitude,
                             double longitude) {
    Result fakeResult = new Result(contents, null, null, BarcodeFormat.QR_CODE);
    ParsedResult result = ResultParser.parseResult(fakeResult);
    assertSame(ParsedResultType.CALENDAR, result.getType());
    CalendarParsedResult calResult = (CalendarParsedResult) result;
    assertEquals(description, calResult.getDescription());
    assertEquals(summary, calResult.getSummary());
    assertEquals(location, calResult.getLocation());
    assertEquals(startString, DATE_TIME_FORMAT.format(calResult.getStart()));
    assertEquals(endString, calResult.getEnd() == null ? null : DATE_TIME_FORMAT.format(calResult.getEnd()));
    assertEquals(organizer, calResult.getOrganizer());
    assertArrayEquals(attendees, calResult.getAttendees());
    assertEqualOrNaN(latitude, calResult.getLatitude());
    assertEqualOrNaN(longitude, calResult.getLongitude());
  }

  private static void assertEqualOrNaN(double expected, double actual) {
    if (Double.isNaN(expected)) {
      assertTrue(Double.isNaN(actual));
    } else {
      assertEquals(expected, actual, EPSILON);
    }
  }

}