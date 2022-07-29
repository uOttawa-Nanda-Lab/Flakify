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

  @Test public void testApplyMaskPenaltyRule4(){ByteMatrix matrix=new ByteMatrix(1,1);matrix.set(0,0,0);assertEquals(100,MaskUtil.applyMaskPenaltyRule4(matrix));matrix=new ByteMatrix(2,1);matrix.set(0,0,0);matrix.set(0,0,1);assertEquals(0,MaskUtil.applyMaskPenaltyRule4(matrix));matrix=new ByteMatrix(6,1);matrix.set(0,0,0);matrix.set(1,0,1);matrix.set(2,0,1);matrix.set(3,0,1);matrix.set(4,0,1);matrix.set(5,0,0);assertEquals(30,MaskUtil.applyMaskPenaltyRule4(matrix));}

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
