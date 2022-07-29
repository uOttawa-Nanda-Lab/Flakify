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

import java.util.Locale;
import java.util.TimeZone;

/**
 * Tests {@link ParsedResult}.
 *
 * @author Sean Owen
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ParsedReaderResultTestCase extends Assert {

  @Before
  public void setUp() {
    Locale.setDefault(Locale.ENGLISH);
    TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
  }

  @Test public void testVEvent(){doTestResult("BEGIN:VCALENDAR\r\nBEGIN:VEVENT\r\nSUMMARY:foo\r\nDTSTART:20080504T123456Z\r\n" + "DTEND:20080505T234555Z\r\nEND:VEVENT\r\nEND:VCALENDAR","foo\nMay 4, 2008 12:34:56 PM\nMay 5, 2008 11:45:55 PM",ParsedResultType.CALENDAR);doTestResult("BEGIN:VEVENT\r\nSUMMARY:foo\r\nDTSTART:20080504T123456Z\r\n" + "DTEND:20080505T234555Z\r\nEND:VEVENT","foo\nMay 4, 2008 12:34:56 PM\nMay 5, 2008 11:45:55 PM",ParsedResultType.CALENDAR);doTestResult("BEGIN:VEVENT\r\nSUMMARY:foo\r\nDTSTART:20080504T123456\r\n" + "DTEND:20080505T234555\r\nEND:VEVENT","foo\nMay 4, 2008 12:34:56 PM\nMay 5, 2008 11:45:55 PM",ParsedResultType.CALENDAR);doTestResult("BEGIN:VEVENT\r\nSUMMARY:foo\r\nDTSTART:20080504\r\n" + "DTEND:20080505\r\nEND:VEVENT","foo\nMay 4, 2008\nMay 5, 2008",ParsedResultType.CALENDAR);doTestResult("BEGIN:VEVENT\r\nSUMMARY:foo\r\nDTSTART:20080504T123456Z\r\nEND:VEVENT","foo\nMay 4, 2008 12:34:56 PM",ParsedResultType.CALENDAR);doTestResult("BEGIN:VEVENT\r\nSUMMARY:foo\r\nDTSTART:20080504T123456\r\nEND:VEVENT","foo\nMay 4, 2008 12:34:56 PM",ParsedResultType.CALENDAR);doTestResult("BEGIN:VEVENT\r\nSUMMARY:foo\r\nDTSTART:20080504\r\nEND:VEVENT","foo\nMay 4, 2008",ParsedResultType.CALENDAR);doTestResult("BEGIN:VEVENT\r\nDTEND:20080505T\r\nEND:VEVENT","BEGIN:VEVENT\r\nDTEND:20080505T\r\nEND:VEVENT",ParsedResultType.URI);doTestResult("BEGIN:VEVENTSUMMARY:EventDTSTART:20081030T122030ZDTEND:20081030T132030ZEND:VEVENT","BEGIN:VEVENTSUMMARY:EventDTSTART:20081030T122030ZDTEND:20081030T132030ZEND:VEVENT",ParsedResultType.URI);}

  

  /*
  @Test
  public void testNDEFText() {
    doTestResult(new byte[] {(byte)0xD1,(byte)0x01,(byte)0x05,(byte)0x54,
                             (byte)0x02,(byte)0x65,(byte)0x6E,(byte)0x68,
                             (byte)0x69},
                 ParsedResultType.TEXT);
  }

  @Test
  public void testNDEFURI() {
    doTestResult(new byte[] {(byte)0xD1,(byte)0x01,(byte)0x08,(byte)0x55,
                             (byte)0x01,(byte)0x6E,(byte)0x66,(byte)0x63,
                             (byte)0x2E,(byte)0x63,(byte)0x6F,(byte)0x6D},
                 ParsedResultType.URI);
  }

  @Test
  public void testNDEFSmartPoster() {
    doTestResult(new byte[] {(byte)0xD1,(byte)0x02,(byte)0x2F,(byte)0x53,
                             (byte)0x70,(byte)0x91,(byte)0x01,(byte)0x0E,
                             (byte)0x55,(byte)0x01,(byte)0x6E,(byte)0x66,
                             (byte)0x63,(byte)0x2D,(byte)0x66,(byte)0x6F,
                             (byte)0x72,(byte)0x75,(byte)0x6D,(byte)0x2E,
                             (byte)0x6F,(byte)0x72,(byte)0x67,(byte)0x11,
                             (byte)0x03,(byte)0x01,(byte)0x61,(byte)0x63,
                             (byte)0x74,(byte)0x00,(byte)0x51,(byte)0x01,
                             (byte)0x12,(byte)0x54,(byte)0x05,(byte)0x65,
                             (byte)0x6E,(byte)0x2D,(byte)0x55,(byte)0x53,
                             (byte)0x48,(byte)0x65,(byte)0x6C,(byte)0x6C,
                             (byte)0x6F,(byte)0x2C,(byte)0x20,(byte)0x77,
                             (byte)0x6F,(byte)0x72,(byte)0x6C,(byte)0x64},
                 ParsedResultType.NDEF_SMART_POSTER);
  }
   */

  private static void doTestResult(String contents,
                                   String goldenResult,
                                   ParsedResultType type) {
    doTestResult(contents, goldenResult, type, BarcodeFormat.QR_CODE); // QR code is arbitrary
  }

  private static void doTestResult(String contents,
                                   String goldenResult,
                                   ParsedResultType type,
                                   BarcodeFormat format) {
    Result fakeResult = new Result(contents, null, null, format);
    ParsedResult result = ResultParser.parseResult(fakeResult);
    assertNotNull(result);
    assertSame(type, result.getType());

    String displayResult = result.getDisplayResult();
    assertEquals(goldenResult, displayResult);
  }

}