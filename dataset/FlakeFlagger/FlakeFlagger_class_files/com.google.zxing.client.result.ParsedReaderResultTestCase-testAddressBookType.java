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

  @Test public void testAddressBookType(){doTestResult("MECARD:N:Sean Owen;;","Sean Owen",ParsedResultType.ADDRESSBOOK);doTestResult("MECARD:TEL:+12125551212;N:Sean Owen;;","Sean Owen\n+12125551212",ParsedResultType.ADDRESSBOOK);doTestResult("MECARD:TEL:+12125551212;N:Sean Owen;URL:google.com;;","Sean Owen\n+12125551212\ngoogle.com",ParsedResultType.ADDRESSBOOK);doTestResult("MECARD:TEL:+12125551212;N:Sean Owen;URL:google.com;EMAIL:srowen@example.org;","Sean Owen\n+12125551212\nsrowen@example.org\ngoogle.com",ParsedResultType.ADDRESSBOOK);doTestResult("MECARD:ADR:76 9th Ave;N:Sean Owen;URL:google.com;EMAIL:srowen@example.org;","Sean Owen\n76 9th Ave\nsrowen@example.org\ngoogle.com",ParsedResultType.ADDRESSBOOK);doTestResult("MECARD:BDAY:19760520;N:Sean Owen;URL:google.com;EMAIL:srowen@example.org;","Sean Owen\nsrowen@example.org\ngoogle.com\n19760520",ParsedResultType.ADDRESSBOOK);doTestResult("MECARD:ORG:Google;N:Sean Owen;URL:google.com;EMAIL:srowen@example.org;","Sean Owen\nGoogle\nsrowen@example.org\ngoogle.com",ParsedResultType.ADDRESSBOOK);doTestResult("MECARD:NOTE:ZXing Team;N:Sean Owen;URL:google.com;EMAIL:srowen@example.org;","Sean Owen\nsrowen@example.org\ngoogle.com\nZXing Team",ParsedResultType.ADDRESSBOOK);doTestResult("N:Sean Owen;TEL:+12125551212;;","N:Sean Owen;TEL:+12125551212;;",ParsedResultType.TEXT);}

  

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