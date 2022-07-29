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

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author satorux@google.com (Satoru Takabayashi) - creator
 * @author mysen@google.com (Chris Mysen) - ported from C++
 */
public final class MatrixUtilTestCase extends Assert {

  @Test public void testCalculateBCHCode(){assertEquals(0xdc,MatrixUtil.calculateBCHCode(5,0x537));assertEquals(0x1c2,MatrixUtil.calculateBCHCode(0x13,0x537));assertEquals(0x214,MatrixUtil.calculateBCHCode(0x1b,0x537));assertEquals(0xc94,MatrixUtil.calculateBCHCode(7,0x1f25));assertEquals(0x5bc,MatrixUtil.calculateBCHCode(8,0x1f25));assertEquals(0xa99,MatrixUtil.calculateBCHCode(9,0x1f25));assertEquals(0x4d3,MatrixUtil.calculateBCHCode(10,0x1f25));assertEquals(0x9a6,MatrixUtil.calculateBCHCode(20,0x1f25));assertEquals(0xd75,MatrixUtil.calculateBCHCode(30,0x1f25));assertEquals(0xc69,MatrixUtil.calculateBCHCode(40,0x1f25));}
}
