/*
 * Copyright 2007 ZXing authors
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

package com.google.zxing.common;

import org.junit.Assert;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Sean Owen
 */
public final class BitArrayTestCase extends Assert {

  @Test public void testGetNextSet5(){Random r=new SecureRandom(new byte[]{(byte)0xDE,(byte)0xAD,(byte)0xBE,(byte)0xEF});for (int i=0;i < 10;i++){BitArray array=new BitArray(1 + r.nextInt(100));int numSet=r.nextInt(20);for (int j=0;j < numSet;j++){array.set(r.nextInt(array.getSize()));}int numQueries=r.nextInt(20);for (int j=0;j < numQueries;j++){int query=r.nextInt(array.getSize());int expected=query;while (expected < array.getSize() && !array.get(expected)){expected++;}int actual=array.getNextSet(query);if (actual != expected){array.getNextSet(query);}assertEquals(expected,actual);}}}


  private static int[] reverseOriginal(int[] oldBits, int size) {
    int[] newBits = new int[oldBits.length];
    for (int i = 0; i < size; i++) {
      if (bitSet(oldBits, size - i - 1)) {
        newBits[i / 32] |= 1 << (i & 0x1F);
      }
    }
    return newBits;
  }

  private static boolean bitSet(int[] bits, int i) {
    return (bits[i / 32] & (1 << (i & 0x1F))) != 0;
  }

  private static boolean arraysAreEqual(int[] left, int[] right, int size) {
    for (int i = 0; i < size; i++) {
      if (left[i] != right[i]) {
        return false;
      }
    }
    return true;
  }

}