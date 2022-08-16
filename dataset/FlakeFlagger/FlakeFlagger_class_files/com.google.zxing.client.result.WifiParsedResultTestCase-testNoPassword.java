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

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link WifiParsedResult}.
 *
 * @author Vikram Aggarwal
 */
public final class WifiParsedResultTestCase extends Assert {

  @Test public void testNoPassword(){doTest("WIFI:S:NoPassword;P:;T:;;","NoPassword",null,"nopass");doTest("WIFI:S:No Password;P:;T:;;","No Password",null,"nopass");}

  /**
   * Given the string contents for the barcode, check that it matches our expectations
   */
  private static void doTest(String contents,
                             String ssid,
                             String password,
                             String type) {
    Result fakeResult = new Result(contents, null, null, BarcodeFormat.QR_CODE);
    ParsedResult result = ResultParser.parseResult(fakeResult);

    // Ensure it is a wifi code
    assertSame(ParsedResultType.WIFI, result.getType());
    WifiParsedResult wifiResult = (WifiParsedResult) result;

    assertEquals(ssid, wifiResult.getSsid());
    assertEquals(password, wifiResult.getPassword());
    assertEquals(type, wifiResult.getNetworkEncryption());
  }
}