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

  @Test public void testGetNumDataBytesAndNumECBytesForBlockID() throws WriterException{int[] numDataBytes=new int[1];int[] numEcBytes=new int[1];Encoder.getNumDataBytesAndNumECBytesForBlockID(26,9,1,0,numDataBytes,numEcBytes);assertEquals(9,numDataBytes[0]);assertEquals(17,numEcBytes[0]);Encoder.getNumDataBytesAndNumECBytesForBlockID(70,26,2,0,numDataBytes,numEcBytes);assertEquals(13,numDataBytes[0]);assertEquals(22,numEcBytes[0]);Encoder.getNumDataBytesAndNumECBytesForBlockID(70,26,2,1,numDataBytes,numEcBytes);assertEquals(13,numDataBytes[0]);assertEquals(22,numEcBytes[0]);Encoder.getNumDataBytesAndNumECBytesForBlockID(196,66,5,0,numDataBytes,numEcBytes);assertEquals(13,numDataBytes[0]);assertEquals(26,numEcBytes[0]);Encoder.getNumDataBytesAndNumECBytesForBlockID(196,66,5,4,numDataBytes,numEcBytes);assertEquals(14,numDataBytes[0]);assertEquals(26,numEcBytes[0]);Encoder.getNumDataBytesAndNumECBytesForBlockID(3706,1276,81,0,numDataBytes,numEcBytes);assertEquals(15,numDataBytes[0]);assertEquals(30,numEcBytes[0]);Encoder.getNumDataBytesAndNumECBytesForBlockID(3706,1276,81,20,numDataBytes,numEcBytes);assertEquals(16,numDataBytes[0]);assertEquals(30,numEcBytes[0]);Encoder.getNumDataBytesAndNumECBytesForBlockID(3706,1276,81,80,numDataBytes,numEcBytes);assertEquals(16,numDataBytes[0]);assertEquals(30,numEcBytes[0]);}

  private static String shiftJISString(byte[] bytes) throws WriterException {
    try {
      return new String(bytes, "Shift_JIS");
    } catch (UnsupportedEncodingException uee) {
      throw new WriterException(uee.toString());
    }
  }

}
