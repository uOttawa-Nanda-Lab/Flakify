/**
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

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author satorux@google.com (Satoru Takabayashi) - creator
 * @author mysen@google.com (Chris Mysen) - ported from C++
 */
public final class QRCodeTestCase extends Assert {

  @Test
  public void test() {
    QRCode qrCode = new QRCode();

    // First, test simple setters and getters.
    // We use numbers of version 7-H.
    qrCode.setMode(Mode.BYTE);
    qrCode.setECLevel(ErrorCorrectionLevel.H);
    qrCode.setVersion(Version.getVersionForNumber(7));
    qrCode.setMaskPattern(3);

    assertSame(Mode.BYTE, qrCode.getMode());
    assertSame(ErrorCorrectionLevel.H, qrCode.getECLevel());
    assertEquals(7, qrCode.getVersion().getVersionNumber());
    assertEquals(3, qrCode.getMaskPattern());

    // Prepare the matrix.
    ByteMatrix matrix = new ByteMatrix(45, 45);
    // Just set bogus zero/one values.
    for (int y = 0; y < 45; ++y) {
      for (int x = 0; x < 45; ++x) {
        matrix.set(x, y, (y + x) % 2);
      }
    }

    // Set the matrix.
    qrCode.setMatrix(matrix);
    assertSame(matrix, qrCode.getMatrix());
  }

}
