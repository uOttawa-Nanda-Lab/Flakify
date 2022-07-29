/*
 * Copyright 2008 ZXing authors
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

package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author satorux@google.com (Satoru Takabayashi) - creator
 * @author mysen@google.com (Chris Mysen) - ported from C++
 */
public final class EncoderTestCase extends Assert {

  @Test public void testGetAlphanumericCode(){for (int i=0;i < 10;++i){assertEquals(i,Encoder.getAlphanumericCode('0' + i));}for (int i=10;i < 36;++i){assertEquals(i,Encoder.getAlphanumericCode('A' + i - 10));}assertEquals(36,Encoder.getAlphanumericCode(' '));assertEquals(37,Encoder.getAlphanumericCode('$'));assertEquals(38,Encoder.getAlphanumericCode('%'));assertEquals(39,Encoder.getAlphanumericCode('*'));assertEquals(40,Encoder.getAlphanumericCode('+'));assertEquals(41,Encoder.getAlphanumericCode('-'));assertEquals(42,Encoder.getAlphanumericCode('.'));assertEquals(43,Encoder.getAlphanumericCode('/'));assertEquals(44,Encoder.getAlphanumericCode(':'));assertEquals(-1,Encoder.getAlphanumericCode('a'));assertEquals(-1,Encoder.getAlphanumericCode('#'));assertEquals(-1,Encoder.getAlphanumericCode('\0'));}

  private static String shiftJISString(byte[] bytes) throws WriterException {
    try {
      return new String(bytes, "Shift_JIS");
    } catch (UnsupportedEncodingException uee) {
      throw new WriterException(uee.toString());
    }
  }

}
