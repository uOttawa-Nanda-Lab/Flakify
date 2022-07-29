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

package com.google.zxing.common.reedsolomon;

import org.junit.Assert;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * @author Rustam Abdullaev
 */
public final class ReedSolomonTestCase extends Assert {

  private static final int DECODER_RANDOM_TEST_ITERATIONS = 3;
  private static final int DECODER_TEST_ITERATIONS = 10;

  @Test public void testQRCode(){testEncodeDecode(GenericGF.QR_CODE_FIELD_256,new int[]{0x10,0x20,0x0C,0x56,0x61,0x80,0xEC,0x11,0xEC,0x11,0xEC,0x11,0xEC,0x11,0xEC,0x11},new int[]{0xA5,0x24,0xD4,0xC1,0xED,0x36,0xC7,0x87,0x2C,0x55});testEncodeDecode(GenericGF.QR_CODE_FIELD_256,new int[]{0x72,0x67,0x2F,0x77,0x69,0x6B,0x69,0x2F,0x4D,0x61,0x69,0x6E,0x5F,0x50,0x61,0x67,0x65,0x3B,0x3B,0x00,0xEC,0x11,0xEC,0x11,0xEC,0x11,0xEC,0x11,0xEC,0x11,0xEC,0x11},new int[]{0xD8,0xB8,0xEF,0x14,0xEC,0xD0,0xCC,0x85,0x73,0x40,0x0B,0xB5,0x5A,0xB8,0x8B,0x2E,0x08,0x62});testEncodeDecodeRandom(GenericGF.QR_CODE_FIELD_256,10,240);testEncodeDecodeRandom(GenericGF.QR_CODE_FIELD_256,128,127);testEncodeDecodeRandom(GenericGF.QR_CODE_FIELD_256,220,35);}

  private static void corrupt(int[] received, int howMany, Random random, int max) {
    BitSet corrupted = new BitSet(received.length);
    for (int j = 0; j < howMany; j++) {
      int location = random.nextInt(received.length);
      int value = random.nextInt(max);
      if (corrupted.get(location) || received[location] == value) {
        j--;
      } else {
        corrupted.set(location);
        received[location] = value;
      }
    }
  }
  
  private static void testEncodeDecode(GenericGF field, int[] dataWords, int[] ecWords) {
    testEncoder(field, dataWords, ecWords);
    testDecoder(field, dataWords, ecWords);
  }
  
  private static void assertDataEquals(String message, int[] expected, int[] received) {
    for (int i = 0; i < expected.length; i++) {
      if (expected[i] != received[i]) {
        fail(message + ". Mismatch at " + i + ". Expected " + arrayToString(expected) + ", got " + 
             arrayToString(Arrays.copyOf(received, expected.length)));
      }
    }
  }
  
  private static String arrayToString(int[] data) {
    StringBuilder sb = new StringBuilder("{");
    for (int i=0; i<data.length; i++) {
      sb.append(String.format(i > 0 ? ",%X" : "%X", data[i]));
    }
    return sb.append('}').toString();
  }

  private static Random getPseudoRandom() {
    return new SecureRandom(new byte[] {(byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF});
  }

}