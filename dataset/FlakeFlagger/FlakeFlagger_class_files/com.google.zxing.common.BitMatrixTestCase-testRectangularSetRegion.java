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

  @Test public void testRectangularSetRegion(){BitMatrix matrix=new BitMatrix(320,240);assertEquals(320,matrix.getWidth());assertEquals(240,matrix.getHeight());matrix.setRegion(105,22,80,12);for (int y=0;y < 240;y++){for (int x=0;x < 320;x++){assertEquals(y >= 22 && y < 34 && x >= 105 && x < 185,matrix.get(x,y));}}}

}
