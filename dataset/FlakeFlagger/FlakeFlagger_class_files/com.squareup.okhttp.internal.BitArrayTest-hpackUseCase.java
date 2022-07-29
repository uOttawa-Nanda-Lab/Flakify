/*
 * Copyright 2014 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.okhttp.internal;

import java.math.BigInteger;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BitArrayTest {

  /**
 * Lazy grow into a variable capacity bit set. 
 */@Test public void hpackUseCase(){BitArray b=new BitArray.FixedCapacity();for (int i=0;i < 64;i++){b.set(i);}assertTrue(b.get(0));assertTrue(b.get(1));assertTrue(b.get(63));try {b.get(64);fail();} catch (IllegalArgumentException expected){}b=((BitArray.FixedCapacity)b).toVariableCapacity();assertTrue(b.get(0));assertTrue(b.get(1));assertTrue(b.get(63));assertFalse(b.get(64));b.set(64);assertTrue(b.get(64));}

  private static String bigIntegerToString(BigInteger b) {
    StringBuilder builder = new StringBuilder("{");
    for (int i = 0, count = b.bitLength(); i < count; i++) {
      if (b.testBit(i)) {
        builder.append(i).append(',');
      }
    }
    builder.setCharAt(builder.length() - 1, '}');
    return builder.toString();
  }
}
