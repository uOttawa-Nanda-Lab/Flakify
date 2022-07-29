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

  @Test public void testEmbedTypeInfo() throws WriterException{ByteMatrix matrix=new ByteMatrix(21,21);MatrixUtil.clearMatrix(matrix);MatrixUtil.embedTypeInfo(ErrorCorrectionLevel.M,5,matrix);String expected="                 0                        \n" + "                 1                        \n" + "                 1                        \n" + "                 1                        \n" + "                 0                        \n" + "                 0                        \n" + "                                          \n" + "                 1                        \n" + " 1 0 0 0 0 0   0 1         1 1 0 0 1 1 1 0\n" + "                                          \n" + "                                          \n" + "                                          \n" + "                                          \n" + "                                          \n" + "                 0                        \n" + "                 0                        \n" + "                 0                        \n" + "                 0                        \n" + "                 0                        \n" + "                 0                        \n" + "                 1                        \n";assertEquals(expected,matrix.toString());}
}
