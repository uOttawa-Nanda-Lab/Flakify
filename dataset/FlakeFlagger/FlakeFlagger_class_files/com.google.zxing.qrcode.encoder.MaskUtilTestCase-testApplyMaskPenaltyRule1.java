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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author satorux@google.com (Satoru Takabayashi) - creator
 * @author mysen@google.com (Chris Mysen) - ported from C++
 */
public final class MaskUtilTestCase extends Assert {

  @Test public void testApplyMaskPenaltyRule1(){ByteMatrix matrix=new ByteMatrix(4,1);matrix.set(0,0,0);matrix.set(1,0,0);matrix.set(2,0,0);matrix.set(3,0,0);assertEquals(0,MaskUtil.applyMaskPenaltyRule1(matrix));matrix=new ByteMatrix(6,1);matrix.set(0,0,0);matrix.set(1,0,0);matrix.set(2,0,0);matrix.set(3,0,0);matrix.set(4,0,0);matrix.set(5,0,1);assertEquals(3,MaskUtil.applyMaskPenaltyRule1(matrix));matrix.set(5,0,0);assertEquals(4,MaskUtil.applyMaskPenaltyRule1(matrix));matrix=new ByteMatrix(1,6);matrix.set(0,0,0);matrix.set(0,1,0);matrix.set(0,2,0);matrix.set(0,3,0);matrix.set(0,4,0);matrix.set(0,5,1);assertEquals(3,MaskUtil.applyMaskPenaltyRule1(matrix));matrix.set(0,5,0);assertEquals(4,MaskUtil.applyMaskPenaltyRule1(matrix));}

  private static boolean TestGetDataMaskBitInternal(int maskPattern, int[][] expected) {
    for (int x = 0; x < 6; ++x) {
      for (int y = 0; y < 6; ++y) {
        if ((expected[y][x] == 1) != MaskUtil.getDataMaskBit(maskPattern, x, y)) {
          return false;
        }
      }
    }
    return true;
  }
}
