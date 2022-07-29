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

package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sean Owen
 */
public final class DataMaskTestCase extends Assert {

  @Test public void testMask1(){testMaskAcrossDimensions(1,new MaskCondition(){@Override public boolean isMasked(int i,int j){return i % 2 == 0;}});}

  private interface MaskCondition {
    boolean isMasked(int i, int j);
  }

}
