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

  @Test public void testExotic(){doTest("bitcoin:mySD89iqpmptrK3PhHFW9fa7BXiP7ANy3Y","bitcoin:mySD89iqpmptrK3PhHFW9fa7BXiP7ANy3Y",null);doTest("BTCTX:-TC4TO3$ZYZTC5NC83/SYOV+YGUGK:$BSF0P8/STNTKTKS.V84+JSA$LB+EHCG+8A725.2AZ-NAVX3VBV5K4MH7UL2.2M:" + "F*M9HSL*$2P7T*FX.ZT80GWDRV0QZBPQ+O37WDCNZBRM3EQ0S9SZP+3BPYZG02U/LA*89C2U.V1TS.CT1VF3DIN*HN3W-O-" + "0ZAKOAB32/.8:J501GJJTTWOA+5/6$MIYBERPZ41NJ6-WSG/*Z48ZH*LSAOEM*IXP81L:$F*W08Z60CR*C*P.JEEVI1F02J07L6+" + "W4L1G$/IC*$16GK6A+:I1-:LJ:Z-P3NW6Z6ADFB-F2AKE$2DWN23GYCYEWX9S8L+LF$VXEKH7/R48E32PU+A:9H:8O5","BTCTX:-TC4TO3$ZYZTC5NC83/SYOV+YGUGK:$BSF0P8/STNTKTKS.V84+JSA$LB+EHCG+8A725.2AZ-NAVX3VBV5K4MH7UL2.2M:" + "F*M9HSL*$2P7T*FX.ZT80GWDRV0QZBPQ+O37WDCNZBRM3EQ0S9SZP+3BPYZG02U/LA*89C2U.V1TS.CT1VF3DIN*HN3W-O-" + "0ZAKOAB32/.8:J501GJJTTWOA+5/6$MIYBERPZ41NJ6-WSG/*Z48ZH*LSAOEM*IXP81L:$F*W08Z60CR*C*P.JEEVI1F02J07L6+" + "W4L1G$/IC*$16GK6A+:I1-:LJ:Z-P3NW6Z6ADFB-F2AKE$2DWN23GYCYEWX9S8L+LF$VXEKH7/R48E32PU+A:9H:8O5",null);}

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