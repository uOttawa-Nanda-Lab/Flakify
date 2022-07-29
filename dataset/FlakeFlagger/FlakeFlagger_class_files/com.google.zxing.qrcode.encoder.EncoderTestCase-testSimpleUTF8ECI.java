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

  @Test public void testSimpleUTF8ECI() throws WriterException{Map<EncodeHintType, Object> hints=new EnumMap<EncodeHintType, Object>(EncodeHintType.class);hints.put(EncodeHintType.CHARACTER_SET,"UTF8");QRCode qrCode=Encoder.encode("hello",ErrorCorrectionLevel.H,hints);String expected="<<\n" + " mode: BYTE\n" + " ecLevel: H\n" + " version: 1\n" + " maskPattern: 6\n" + " matrix:\n" + " 1 1 1 1 1 1 1 0 0 0 1 1 0 0 1 1 1 1 1 1 1\n" + " 1 0 0 0 0 0 1 0 0 0 1 1 0 0 1 0 0 0 0 0 1\n" + " 1 0 1 1 1 0 1 0 1 0 0 1 1 0 1 0 1 1 1 0 1\n" + " 1 0 1 1 1 0 1 0 1 0 0 0 1 0 1 0 1 1 1 0 1\n" + " 1 0 1 1 1 0 1 0 0 1 1 0 0 0 1 0 1 1 1 0 1\n" + " 1 0 0 0 0 0 1 0 0 0 0 1 0 0 1 0 0 0 0 0 1\n" + " 1 1 1 1 1 1 1 0 1 0 1 0 1 0 1 1 1 1 1 1 1\n" + " 0 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 0 0 0 0\n" + " 0 0 0 1 1 0 1 1 0 0 0 0 1 0 0 0 0 1 1 0 0\n" + " 0 0 0 0 0 0 0 0 1 1 0 1 0 0 1 0 1 1 1 1 1\n" + " 1 1 0 0 0 1 1 1 0 0 0 1 1 0 0 1 0 1 0 1 1\n" + " 0 0 0 0 1 1 0 0 1 0 0 0 0 0 1 0 1 1 0 0 0\n" + " 0 1 1 0 0 1 1 0 0 1 1 1 0 1 1 1 1 1 1 1 1\n" + " 0 0 0 0 0 0 0 0 1 1 1 0 1 1 1 1 1 1 1 1 1\n" + " 1 1 1 1 1 1 1 0 1 0 1 0 0 0 1 0 0 0 0 0 0\n" + " 1 0 0 0 0 0 1 0 0 1 0 0 0 1 0 0 0 1 1 0 0\n" + " 1 0 1 1 1 0 1 0 1 0 0 0 1 0 1 0 0 0 1 0 0\n" + " 1 0 1 1 1 0 1 0 1 1 1 1 0 1 0 0 1 0 1 1 0\n" + " 1 0 1 1 1 0 1 0 0 1 1 1 0 0 1 0 0 1 0 1 1\n" + " 1 0 0 0 0 0 1 0 0 0 0 0 0 1 1 0 1 1 0 0 0\n" + " 1 1 1 1 1 1 1 0 0 0 0 1 0 1 0 0 1 0 1 0 0\n" + ">>\n";assertEquals(expected,qrCode.toString());}

  private static String shiftJISString(byte[] bytes) throws WriterException {
    try {
      return new String(bytes, "Shift_JIS");
    } catch (UnsupportedEncodingException uee) {
      throw new WriterException(uee.toString());
    }
  }

}
