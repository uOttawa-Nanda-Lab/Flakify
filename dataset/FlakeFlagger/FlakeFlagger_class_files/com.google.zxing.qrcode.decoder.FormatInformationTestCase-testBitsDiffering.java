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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sean Owen
 */
public final class FormatInformationTestCase extends Assert {

  private static final int MASKED_TEST_FORMAT_INFO = 0x2BED;
  private static final int UNMASKED_TEST_FORMAT_INFO = MASKED_TEST_FORMAT_INFO ^ 0x5412;

  @Test public void testBitsDiffering(){assertEquals(0,FormatInformation.numBitsDiffering(1,1));assertEquals(1,FormatInformation.numBitsDiffering(0,2));assertEquals(2,FormatInformation.numBitsDiffering(1,2));assertEquals(32,FormatInformation.numBitsDiffering(-1,0));}

}