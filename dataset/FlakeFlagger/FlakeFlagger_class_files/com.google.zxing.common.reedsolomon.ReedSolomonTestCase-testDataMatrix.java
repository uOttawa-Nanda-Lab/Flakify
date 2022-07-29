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

  @Test public void testDataMatrix(){testEncodeDecode(GenericGF.DATA_MATRIX_FIELD_256,new int[]{142,164,186},new int[]{114,25,5,88,102});testEncodeDecode(GenericGF.DATA_MATRIX_FIELD_256,new int[]{0x69,0x75,0x75,0x71,0x3B,0x30,0x30,0x64,0x70,0x65,0x66,0x2F,0x68,0x70,0x70,0x68,0x6D,0x66,0x2F,0x64,0x70,0x6E,0x30,0x71,0x30,0x7B,0x79,0x6A,0x6F,0x68,0x30,0x81,0xF0,0x88,0x1F,0xB5},new int[]{0x1C,0x64,0xEE,0xEB,0xD0,0x1D,0x00,0x03,0xF0,0x1C,0xF1,0xD0,0x6D,0x00,0x98,0xDA,0x80,0x88,0xBE,0xFF,0xB7,0xFA,0xA9,0x95});testEncodeDecodeRandom(GenericGF.DATA_MATRIX_FIELD_256,10,240);testEncodeDecodeRandom(GenericGF.DATA_MATRIX_FIELD_256,128,127);testEncodeDecodeRandom(GenericGF.DATA_MATRIX_FIELD_256,220,35);}

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