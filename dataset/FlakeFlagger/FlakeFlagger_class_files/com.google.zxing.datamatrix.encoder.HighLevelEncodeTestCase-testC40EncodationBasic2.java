/*
 * Copyright 2006 Jeremias Maerki.
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

package com.google.zxing.datamatrix.encoder;

import junit.framework.ComparisonFailure;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link HighLevelEncoder}.
 */
public final class HighLevelEncodeTestCase extends Assert {

  private static final SymbolInfo[] TEST_SYMBOLS = {
    new SymbolInfo(false, 3, 5, 8, 8, 1),
    new SymbolInfo(false, 5, 7, 10, 10, 1),
      /*rect*/new SymbolInfo(true, 5, 7, 16, 6, 1),
    new SymbolInfo(false, 8, 10, 12, 12, 1),
      /*rect*/new SymbolInfo(true, 10, 11, 14, 6, 2),
    new SymbolInfo(false, 13, 0, 0, 0, 1),
    new SymbolInfo(false, 77, 0, 0, 0, 1)
    //The last entries are fake entries to test special conditions with C40 encoding
  };

  private static void useTestSymbols() {
    SymbolInfo.overrideSymbolSet(TEST_SYMBOLS);
  }

  private static void resetSymbols() {
    SymbolInfo.overrideSymbolSet(SymbolInfo.PROD_SYMBOLS);
  }

  @Test public void testC40EncodationBasic2(){String visualized=encodeHighLevel("AIMAIAB");assertEquals("230 91 11 90 255 254 67 129",visualized);visualized=encodeHighLevel("AIMAIAb");assertEquals("66 74 78 66 74 66 99 129",visualized);visualized=encodeHighLevel("AIMAIMAIMË");assertEquals("230 91 11 91 11 91 11 254 235 76",visualized);visualized=encodeHighLevel("AIMAIMAIMë");assertEquals("230 91 11 91 11 91 11 254 235 108",visualized);}

  private static String createBinaryMessage(int len) {
    StringBuilder sb = new StringBuilder();
    sb.append("\u00ABäöüéàá-");
    for (int i = 0; i < len - 9; i++) {
      sb.append('\u00B7');
    }
    sb.append('\u00BB');
    return sb.toString();
  }

  private static void assertStartsWith(String expected, String actual) {
    if (!actual.startsWith(expected)) {
      throw new ComparisonFailure(null, expected, actual.substring(0, expected.length()));
    }
  }

  private static void assertEndsWith(String expected, String actual) {
    if (!actual.endsWith(expected)) {
      throw new ComparisonFailure(null, expected, actual.substring(actual.length() - expected.length()));
    }
  }

  

  // Not passing?
  /*
  @Test  
  public void testDataURL() {

    byte[] data = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A,
        0x7E, 0x7F, (byte) 0x80, (byte) 0x81, (byte) 0x82};
    String expected = encodeHighLevel(new String(data, Charset.forName("ISO-8859-1")));
    String visualized = encodeHighLevel("url(data:text/plain;charset=iso-8859-1,"
                                            + "%00%01%02%03%04%05%06%07%08%09%0A%7E%7F%80%81%82)");
    assertEquals(expected, visualized);
    assertEquals("1 2 3 4 5 6 7 8 9 10 11 231 153 173 67 218 112 7", visualized);

    visualized = encodeHighLevel("url(data:;base64,flRlc3R+)");
    assertEquals("127 85 102 116 117 127 129 56", visualized);
  }
   */

  private static String encodeHighLevel(String msg) {
    CharSequence encoded = HighLevelEncoder.encodeHighLevel(msg);
    //DecodeHighLevel.decode(encoded);
    return visualize(encoded);
  }
  
  /**
   * Convert a string of char codewords into a different string which lists each character
   * using its decimal value.
   *
   * @param codewords the codewords
   * @return the visualized codewords
   */
  static String visualize(CharSequence codewords) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < codewords.length(); i++) {
      if (i > 0) {
        sb.append(' ');
      }
      sb.append((int) codewords.charAt(i));
    }
    return sb.toString();
  }

}
