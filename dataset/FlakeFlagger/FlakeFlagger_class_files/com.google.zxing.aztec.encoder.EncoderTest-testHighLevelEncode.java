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

  @Test public void testHighLevelEncode() throws Exception{testHighLevelEncodeString("A. b.","...X. ..... ...XX XXX.. ...XX XXXX. XX.X");testHighLevelEncodeString("Lorem ipsum.",".XX.X XXX.. X.... X..XX ..XX. .XXX. ....X .X.X. X...X X.X.. X.XX. .XXX. XXXX. XX.X");testHighLevelEncodeString("Lo. Test 123.",".XX.X XXX.. X.... ..... ...XX XXX.. X.X.X ..XX. X.X.. X.X.X  XXXX. ...X ..XX .X.. .X.X XX.X");testHighLevelEncodeString("Lo...x",".XX.X XXX.. X.... XXXX. XX.X XX.X XX.X XXX. XXX.. XX..X");testHighLevelEncodeString(". x://abc/.","..... ...XX XXX.. XX..X ..... X.X.X ..... X.X.. ..... X.X.. ...X. ...XX ..X.. ..... X.X.. XXXX. XX.X");testHighLevelEncodeString("ABCdEFG","...X. ...XX ..X.. XXXXX ....X .XX..X.. ..XX. ..XXX .X...");testHighLevelEncodeString("09  UAG    ^160MEUCIQC0sYS/HpKxnBELR1uB85R20OoqqwFGa0q2uEi" + "Ygh6utAIgLl1aBVM4EOTQtMQQYH9M2Z3Dp4qnA/fwWuQ+M8L3V8U=",823);}

  

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
