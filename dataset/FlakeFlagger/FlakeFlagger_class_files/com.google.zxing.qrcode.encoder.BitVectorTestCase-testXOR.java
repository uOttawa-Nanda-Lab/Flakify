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

  @Test public void testXOR(){BitArray v1=new BitArray();v1.appendBits(0x5555aaaa,32);BitArray v2=new BitArray();v2.appendBits(0xaaaa5555,32);v1.xor(v2);assertEquals(0xffffffffL,getUnsignedInt(v1,0));}

}
