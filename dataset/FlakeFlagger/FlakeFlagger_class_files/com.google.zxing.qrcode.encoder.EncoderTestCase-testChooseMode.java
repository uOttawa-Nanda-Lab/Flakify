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

  @Test public void testChooseMode() throws WriterException{assertSame(Mode.NUMERIC,Encoder.chooseMode("0"));assertSame(Mode.NUMERIC,Encoder.chooseMode("0123456789"));assertSame(Mode.ALPHANUMERIC,Encoder.chooseMode("A"));assertSame(Mode.ALPHANUMERIC,Encoder.chooseMode("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:"));assertSame(Mode.BYTE,Encoder.chooseMode("a"));assertSame(Mode.BYTE,Encoder.chooseMode("#"));assertSame(Mode.BYTE,Encoder.chooseMode(""));assertSame(Mode.BYTE,Encoder.chooseMode(shiftJISString(new byte[]{0x8,0xa,0x8,0xa,0x8,0xa,0x8,(byte)0xa6})));assertSame(Mode.BYTE,Encoder.chooseMode(shiftJISString(new byte[]{0x9,0xf,0x9,0x7b})));assertSame(Mode.BYTE,Encoder.chooseMode(shiftJISString(new byte[]{0xe,0x4,0x9,0x5,0x9,0x61})));}

  private static String shiftJISString(byte[] bytes) throws WriterException {
    try {
      return new String(bytes, "Shift_JIS");
    } catch (UnsupportedEncodingException uee) {
      throw new WriterException(uee.toString());
    }
  }

}
