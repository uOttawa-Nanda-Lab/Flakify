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

  @Test public void testRectangularMatrix(){BitMatrix matrix=new BitMatrix(75,20);assertEquals(75,matrix.getWidth());assertEquals(20,matrix.getHeight());matrix.set(10,0);matrix.set(11,1);matrix.set(50,2);matrix.set(51,3);matrix.flip(74,4);matrix.flip(0,5);assertTrue(matrix.get(10,0));assertTrue(matrix.get(11,1));assertTrue(matrix.get(50,2));assertTrue(matrix.get(51,3));assertTrue(matrix.get(74,4));assertTrue(matrix.get(0,5));matrix.flip(50,2);matrix.flip(51,3);assertFalse(matrix.get(50,2));assertFalse(matrix.get(51,3));}

}
