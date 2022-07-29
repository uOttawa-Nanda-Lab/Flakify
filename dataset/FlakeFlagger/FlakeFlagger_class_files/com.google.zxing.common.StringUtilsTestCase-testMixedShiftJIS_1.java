/*
 * Copyright 2012 ZXing authors
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

package com.google.zxing.common;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

public final class StringUtilsTestCase extends Assert {

  @Test public void testMixedShiftJIS_1(){doTest(new byte[]{(byte)0x48,(byte)0x65,(byte)0x6c,(byte)0x6c,(byte)0x6f,(byte)0x20,(byte)0x8b,(byte)0xe0,(byte)0x21},"SJIS");}

  private static void doTest(byte[] bytes, String charsetName) {
    Charset charset = Charset.forName(charsetName);
    String guessedName = StringUtils.guessEncoding(bytes, null);
    Charset guessedEncoding = Charset.forName(guessedName);
    assertEquals(charset, guessedEncoding);
  }

}
