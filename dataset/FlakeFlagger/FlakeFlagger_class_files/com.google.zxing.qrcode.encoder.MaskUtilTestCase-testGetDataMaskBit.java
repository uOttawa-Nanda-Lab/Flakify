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

  @Test public void testGetDataMaskBit(){int[][] mask0={{1,0,1,0,1,0},{0,1,0,1,0,1},{1,0,1,0,1,0},{0,1,0,1,0,1},{1,0,1,0,1,0},{0,1,0,1,0,1}};assertTrue(TestGetDataMaskBitInternal(0,mask0));int[][] mask1={{1,1,1,1,1,1},{0,0,0,0,0,0},{1,1,1,1,1,1},{0,0,0,0,0,0},{1,1,1,1,1,1},{0,0,0,0,0,0}};assertTrue(TestGetDataMaskBitInternal(1,mask1));int[][] mask2={{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0}};assertTrue(TestGetDataMaskBitInternal(2,mask2));int[][] mask3={{1,0,0,1,0,0},{0,0,1,0,0,1},{0,1,0,0,1,0},{1,0,0,1,0,0},{0,0,1,0,0,1},{0,1,0,0,1,0}};assertTrue(TestGetDataMaskBitInternal(3,mask3));int[][] mask4={{1,1,1,0,0,0},{1,1,1,0,0,0},{0,0,0,1,1,1},{0,0,0,1,1,1},{1,1,1,0,0,0},{1,1,1,0,0,0}};assertTrue(TestGetDataMaskBitInternal(4,mask4));int[][] mask5={{1,1,1,1,1,1},{1,0,0,0,0,0},{1,0,0,1,0,0},{1,0,1,0,1,0},{1,0,0,1,0,0},{1,0,0,0,0,0}};assertTrue(TestGetDataMaskBitInternal(5,mask5));int[][] mask6={{1,1,1,1,1,1},{1,1,1,0,0,0},{1,1,0,1,1,0},{1,0,1,0,1,0},{1,0,1,1,0,1},{1,0,0,0,1,1}};assertTrue(TestGetDataMaskBitInternal(6,mask6));int[][] mask7={{1,0,1,0,1,0},{0,0,0,1,1,1},{1,0,0,0,1,1},{0,1,0,1,0,1},{1,1,1,0,0,0},{0,1,1,1,0,0}};assertTrue(TestGetDataMaskBitInternal(7,mask7));}
}
