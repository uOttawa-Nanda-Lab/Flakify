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

  @Test public void testApplyMaskPenaltyRule3(){ByteMatrix matrix=new ByteMatrix(11,1);matrix.set(0,0,0);matrix.set(1,0,0);matrix.set(2,0,0);matrix.set(3,0,0);matrix.set(4,0,1);matrix.set(5,0,0);matrix.set(6,0,1);matrix.set(7,0,1);matrix.set(8,0,1);matrix.set(9,0,0);matrix.set(10,0,1);assertEquals(40,MaskUtil.applyMaskPenaltyRule3(matrix));matrix=new ByteMatrix(11,1);matrix.set(0,0,1);matrix.set(1,0,0);matrix.set(2,0,1);matrix.set(3,0,1);matrix.set(4,0,1);matrix.set(5,0,0);matrix.set(6,0,1);matrix.set(7,0,0);matrix.set(8,0,0);matrix.set(9,0,0);matrix.set(10,0,0);assertEquals(40,MaskUtil.applyMaskPenaltyRule3(matrix));matrix=new ByteMatrix(1,11);matrix.set(0,0,0);matrix.set(0,1,0);matrix.set(0,2,0);matrix.set(0,3,0);matrix.set(0,4,1);matrix.set(0,5,0);matrix.set(0,6,1);matrix.set(0,7,1);matrix.set(0,8,1);matrix.set(0,9,0);matrix.set(0,10,1);assertEquals(40,MaskUtil.applyMaskPenaltyRule3(matrix));matrix=new ByteMatrix(1,11);matrix.set(0,0,1);matrix.set(0,1,0);matrix.set(0,2,1);matrix.set(0,3,1);matrix.set(0,4,1);matrix.set(0,5,0);matrix.set(0,6,1);matrix.set(0,7,0);matrix.set(0,8,0);matrix.set(0,9,0);matrix.set(0,10,0);assertEquals(40,MaskUtil.applyMaskPenaltyRule3(matrix));}

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
