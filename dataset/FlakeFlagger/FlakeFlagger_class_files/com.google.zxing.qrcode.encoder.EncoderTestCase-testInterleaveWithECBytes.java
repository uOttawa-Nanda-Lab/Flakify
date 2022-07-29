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

  @Test public void testInterleaveWithECBytes() throws WriterException{byte[] dataBytes={32,65,(byte)205,69,41,(byte)220,46,(byte)128,(byte)236};BitArray in=new BitArray();for (byte dataByte:dataBytes){in.appendBits(dataByte,8);}BitArray out=Encoder.interleaveWithECBytes(in,26,9,1);byte[] expected={32,65,(byte)205,69,41,(byte)220,46,(byte)128,(byte)236,42,(byte)159,74,(byte)221,(byte)244,(byte)169,(byte)239,(byte)150,(byte)138,70,(byte)237,85,(byte)224,96,74,(byte)219,61};assertEquals(expected.length,out.getSizeInBytes());byte[] outArray=new byte[expected.length];out.toBytes(0,outArray,0,expected.length);for (int x=0;x < expected.length;x++){assertEquals(expected[x],outArray[x]);}dataBytes=new byte[]{67,70,22,38,54,70,86,102,118,(byte)134,(byte)150,(byte)166,(byte)182,(byte)198,(byte)214,(byte)230,(byte)247,7,23,39,55,71,87,103,119,(byte)135,(byte)151,(byte)166,22,38,54,70,86,102,118,(byte)134,(byte)150,(byte)166,(byte)182,(byte)198,(byte)214,(byte)230,(byte)247,7,23,39,55,71,87,103,119,(byte)135,(byte)151,(byte)160,(byte)236,17,(byte)236,17,(byte)236,17,(byte)236,17};in=new BitArray();for (byte dataByte:dataBytes){in.appendBits(dataByte,8);}out=Encoder.interleaveWithECBytes(in,134,62,4);expected=new byte[]{67,(byte)230,54,55,70,(byte)247,70,71,22,7,86,87,38,23,102,103,54,39,118,119,70,55,(byte)134,(byte)135,86,71,(byte)150,(byte)151,102,87,(byte)166,(byte)160,118,103,(byte)182,(byte)236,(byte)134,119,(byte)198,17,(byte)150,(byte)135,(byte)214,(byte)236,(byte)166,(byte)151,(byte)230,17,(byte)182,(byte)166,(byte)247,(byte)236,(byte)198,22,7,17,(byte)214,38,23,(byte)236,39,17,(byte)175,(byte)155,(byte)245,(byte)236,80,(byte)146,56,74,(byte)155,(byte)165,(byte)133,(byte)142,64,(byte)183,(byte)132,13,(byte)178,54,(byte)132,108,45,113,53,50,(byte)214,98,(byte)193,(byte)152,(byte)233,(byte)147,50,71,65,(byte)190,82,51,(byte)209,(byte)199,(byte)171,54,12,112,57,113,(byte)155,117,(byte)211,(byte)164,117,30,(byte)158,(byte)225,31,(byte)190,(byte)242,38,(byte)140,61,(byte)179,(byte)154,(byte)214,(byte)138,(byte)147,87,27,96,77,47,(byte)187,49,(byte)156,(byte)214};assertEquals(expected.length,out.getSizeInBytes());outArray=new byte[expected.length];out.toBytes(0,outArray,0,expected.length);for (int x=0;x < expected.length;x++){assertEquals(expected[x],outArray[x]);}}

  private static String shiftJISString(byte[] bytes) throws WriterException {
    try {
      return new String(bytes, "Shift_JIS");
    } catch (UnsupportedEncodingException uee) {
      throw new WriterException(uee.toString());
    }
  }

}
