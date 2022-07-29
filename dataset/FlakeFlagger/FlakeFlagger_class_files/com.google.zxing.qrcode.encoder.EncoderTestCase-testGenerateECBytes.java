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

  @Test public void testGenerateECBytes(){byte[] dataBytes={32,65,(byte)205,69,41,(byte)220,46,(byte)128,(byte)236};byte[] ecBytes=Encoder.generateECBytes(dataBytes,17);int[] expected={42,159,74,221,244,169,239,150,138,70,237,85,224,96,74,219,61};assertEquals(expected.length,ecBytes.length);for (int x=0;x < expected.length;x++){assertEquals(expected[x],ecBytes[x] & 0xFF);}dataBytes=new byte[]{67,70,22,38,54,70,86,102,118,(byte)134,(byte)150,(byte)166,(byte)182,(byte)198,(byte)214};ecBytes=Encoder.generateECBytes(dataBytes,18);expected=new int[]{175,80,155,64,178,45,214,233,65,209,12,155,117,31,140,214,27,187};assertEquals(expected.length,ecBytes.length);for (int x=0;x < expected.length;x++){assertEquals(expected[x],ecBytes[x] & 0xFF);}dataBytes=new byte[]{32,49,(byte)205,69,42,20,0,(byte)236,17};ecBytes=Encoder.generateECBytes(dataBytes,17);expected=new int[]{0,3,130,179,194,0,55,211,110,79,98,72,170,96,211,137,213};assertEquals(expected.length,ecBytes.length);for (int x=0;x < expected.length;x++){assertEquals(expected[x],ecBytes[x] & 0xFF);}}

  private static String shiftJISString(byte[] bytes) throws WriterException {
    try {
      return new String(bytes, "Shift_JIS");
    } catch (UnsupportedEncodingException uee) {
      throw new WriterException(uee.toString());
    }
  }

}
