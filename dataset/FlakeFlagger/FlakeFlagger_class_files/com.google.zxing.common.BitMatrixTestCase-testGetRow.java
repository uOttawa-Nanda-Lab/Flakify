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

/**
 * @author Sean Owen
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class BitMatrixTestCase extends Assert {

  @Test public void testGetRow(){BitMatrix matrix=new BitMatrix(102,5);for (int x=0;x < 102;x++){if ((x & 0x03) == 0){matrix.set(x,2);}}BitArray array=matrix.getRow(2,null);assertEquals(102,array.getSize());BitArray array2=new BitArray(60);array2=matrix.getRow(2,array2);assertEquals(102,array2.getSize());BitArray array3=new BitArray(200);array3=matrix.getRow(2,array3);assertEquals(200,array3.getSize());for (int x=0;x < 102;x++){boolean on=(x & 0x03) == 0;assertEquals(on,array.get(x));assertEquals(on,array2.get(x));assertEquals(on,array3.get(x));}}

}
