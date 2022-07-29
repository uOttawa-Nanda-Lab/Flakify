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
 * Tests {@link URIParsedResult}.
 *
 * @author Sean Owen
 */
public final class URIParsedResultTestCase extends Assert {

  @Test public void testURLTO(){doTest("urlto::bar.com","http://bar.com",null);doTest("urlto::http://bar.com","http://bar.com",null);doTest("urlto:foo:bar.com","http://bar.com","foo");}

  private static void doTest(String contents, String uri, String title) {
    Result fakeResult = new Result(contents, null, null, BarcodeFormat.QR_CODE);
    ParsedResult result = ResultParser.parseResult(fakeResult);
    assertSame(ParsedResultType.URI, result.getType());
    URIParsedResult uriResult = (URIParsedResult) result;
    assertEquals(uri, uriResult.getURI());
    assertEquals(title, uriResult.getTitle());
  }
  
  private static void doTestNotUri(String text) {
    Result fakeResult = new Result(text, null, null, BarcodeFormat.QR_CODE);
    ParsedResult result = ResultParser.parseResult(fakeResult);
    assertSame(ParsedResultType.TEXT, result.getType());
    assertEquals(text, result.getDisplayResult());
  }

  private static void doTestIsPossiblyMalicious(String uri, boolean expected) {
    URIParsedResult result = new URIParsedResult(uri, null);
    assertEquals(expected, result.isPossiblyMaliciousURI());
  }

}