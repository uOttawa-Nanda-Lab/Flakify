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

package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitSourceBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link com.google.zxing.qrcode.decoder.DecodedBitStreamParser}.
 *
 * @author Sean Owen
 */
public final class DecodedBitStreamParserTestCase extends Assert {

  @Test public void testHanzi() throws Exception{BitSourceBuilder builder=new BitSourceBuilder();builder.write(0x0D,4);builder.write(0x01,4);builder.write(0x01,8);builder.write(0x03C1,13);String result=DecodedBitStreamParser.decode(builder.toByteArray(),Version.getVersionForNumber(1),null,null).getText();assertEquals("\u963f",result);}

  // TODO definitely need more tests here

}
