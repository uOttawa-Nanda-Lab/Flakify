/*
 * Copyright 2013 ZXing authors
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

package com.google.zxing.aztec.encoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Aztec 2D generator unit tests.
 *
 * @author Rustam Abdullaev
 * @author Frank Yellin
 */
public final class EncoderTest extends Assert {

  private static final Charset LATIN_1 = Charset.forName("ISO-8859-1");
  private static final Pattern DOTX = Pattern.compile("[^.X]");
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];

  // real life tests

  
  
  // synthetic tests (encode-decode round-trip)

  @Test public void testHighLevelEncodeBinary() throws Exception{testHighLevelEncodeString("N\0N",".XXXX XXXXX ....X ........ .XXXX");testHighLevelEncodeString("N\0n",".XXXX XXXXX ...X. ........ .XX.XXX.");testHighLevelEncodeString("N\0\u0080 A",".XXXX XXXXX ...X. ........ X....... ....X ...X.");testHighLevelEncodeString("\0a\u00FF\u0080 A","XXXXX ..X.. ........ .XX....X XXXXXXXX X....... ....X ...X.");testHighLevelEncodeString("1234\0","XXXX. ..XX .X.. .X.X .XX. XXX. XXXXX ....X ........");StringBuilder sb=new StringBuilder();for (int i=0;i <= 3000;i++){sb.append((char)(128 + (i % 30)));}for (int i:new int[]{1,2,3,10,29,30,31,32,33,60,61,62,63,64,2076,2077,2078,2079,2080,2100}){int expectedLength=(8 * i) + ((i <= 31)?10:(i <= 62)?20:(i <= 2078)?21:31);testHighLevelEncodeString(sb.substring(0,i),expectedLength);if (i != 1 && i != 32 && i != 2079){testHighLevelEncodeString('a' + sb.substring(0,i - 1),expectedLength);testHighLevelEncodeString(sb.substring(0,i - 1) + 'a',expectedLength);}testHighLevelEncodeString('a' + sb.substring(0,i) + 'b',expectedLength + 15);}}
  
  

  // Helper routines

  private static Random getPseudoRandom() {
    return new SecureRandom(new byte[] {(byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF});
  }

  private static BitArray toBitArray(CharSequence bits) {
    BitArray in = new BitArray();
    char[] str = DOTX.matcher(bits).replaceAll("").toCharArray();
    for (char aStr : str) {
      in.appendBit(aStr == 'X');
    }
    return in;
  }

  private static boolean[] toBooleanArray(BitArray bitArray) {
    boolean[] result = new boolean[bitArray.getSize()];
    for (int i = 0; i < result.length; i++) {
      result[i] = bitArray.get(i);
    }
    return result;
  }

  private static void testHighLevelEncodeString(String s, int expectedReceivedBits) {
    BitArray bits = new HighLevelEncoder(s.getBytes(LATIN_1)).encode();
    int receivedBitCount = bits.toString().replace(" ", "").length();
    assertEquals("highLevelEncode() failed for input string: " + s, 
                 expectedReceivedBits, receivedBitCount);
    assertEquals(s, Decoder.highLevelDecode(toBooleanArray(bits)));
  }
}
