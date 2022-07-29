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

import com.google.zxing.common.BitArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author satorux@google.com (Satoru Takabayashi) - creator
 * @author dswitkin@google.com (Daniel Switkin) - ported from C++
 */
public final class BitVectorTestCase extends Assert {

  private static long getUnsignedInt(BitArray v, int index) {
    long result = 0L;
    for (int i = 0, offset = index << 3; i < 32; i++) {
      if (v.get(offset + i)) {
        result |= 1L << (31 - i);
      }
    }
    return result;
  }

  @Test public void testAppendBit(){BitArray v=new BitArray();assertEquals(0,v.getSizeInBytes());v.appendBit(true);assertEquals(1,v.getSize());assertEquals(0x80000000L,getUnsignedInt(v,0));v.appendBit(false);assertEquals(2,v.getSize());assertEquals(0x80000000L,getUnsignedInt(v,0));v.appendBit(true);assertEquals(3,v.getSize());assertEquals(0xa0000000L,getUnsignedInt(v,0));v.appendBit(false);assertEquals(4,v.getSize());assertEquals(0xa0000000L,getUnsignedInt(v,0));v.appendBit(true);assertEquals(5,v.getSize());assertEquals(0xa8000000L,getUnsignedInt(v,0));v.appendBit(false);assertEquals(6,v.getSize());assertEquals(0xa8000000L,getUnsignedInt(v,0));v.appendBit(true);assertEquals(7,v.getSize());assertEquals(0xaa000000L,getUnsignedInt(v,0));v.appendBit(false);assertEquals(8,v.getSize());assertEquals(0xaa000000L,getUnsignedInt(v,0));v.appendBit(true);assertEquals(9,v.getSize());assertEquals(0xaa800000L,getUnsignedInt(v,0));v.appendBit(false);assertEquals(10,v.getSize());assertEquals(0xaa800000L,getUnsignedInt(v,0));}

}
