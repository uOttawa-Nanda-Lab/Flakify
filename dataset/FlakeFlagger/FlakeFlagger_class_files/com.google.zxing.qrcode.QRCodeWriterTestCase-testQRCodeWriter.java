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

package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author satorux@google.com (Satoru Takabayashi) - creator
 * @author dswitkin@google.com (Daniel Switkin) - ported and expanded from C++
 */
public final class QRCodeWriterTestCase extends Assert {

  private static final String BASE_IMAGE_PATH = "test/data/golden/qrcode/";

  private static BufferedImage loadImage(String fileName) throws IOException {
    File file = new File(BASE_IMAGE_PATH + fileName);
    if (!file.exists()) {
      // try starting with 'core' since the test base is often given as the project root
      file = new File("core/" + BASE_IMAGE_PATH + fileName);
    }
    assertTrue("Please download and install test images, and run from the 'core' directory", file.exists());
    return ImageIO.read(file);
  }

  // In case the golden images are not monochromatic, convert the RGB values to greyscale.
  private static BitMatrix createMatrixFromImage(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[] pixels = new int[width * height];
    image.getRGB(0, 0, width, height, pixels, 0, width);

    BitMatrix matrix = new BitMatrix(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = pixels[y * width + x];
        int luminance = (306 * ((pixel >> 16) & 0xFF) +
            601 * ((pixel >> 8) & 0xFF) +
            117 * (pixel & 0xFF)) >> 10;
        if (luminance <= 0x7F) {
          matrix.set(x, y);
        }
      }
    }
    return matrix;
  }

  @Test public void testQRCodeWriter() throws WriterException{int bigEnough=256;QRCodeWriter writer=new QRCodeWriter();BitMatrix matrix=writer.encode("http://www.google.com/",BarcodeFormat.QR_CODE,bigEnough,bigEnough,null);assertNotNull(matrix);assertEquals(bigEnough,matrix.getWidth());assertEquals(bigEnough,matrix.getHeight());int tooSmall=20;matrix=writer.encode("http://www.google.com/",BarcodeFormat.QR_CODE,tooSmall,tooSmall,null);assertNotNull(matrix);assertTrue(tooSmall < matrix.getWidth());assertTrue(tooSmall < matrix.getHeight());int strangeWidth=500;int strangeHeight=100;matrix=writer.encode("http://www.google.com/",BarcodeFormat.QR_CODE,strangeWidth,strangeHeight,null);assertNotNull(matrix);assertEquals(strangeWidth,matrix.getWidth());assertEquals(strangeHeight,matrix.getHeight());}

  private static void compareToGoldenFile(String contents,
                                          ErrorCorrectionLevel ecLevel,
                                          int resolution,
                                          String fileName) throws WriterException, IOException {

    BufferedImage image = loadImage(fileName);
    assertNotNull(image);
    BitMatrix goldenResult = createMatrixFromImage(image);
    assertNotNull(goldenResult);

    Map<EncodeHintType,Object> hints = new EnumMap<EncodeHintType,Object>(EncodeHintType.class);
    hints.put(EncodeHintType.ERROR_CORRECTION, ecLevel);
    QRCodeWriter writer = new QRCodeWriter();
    BitMatrix generatedResult = writer.encode(contents, BarcodeFormat.QR_CODE, resolution,
        resolution, hints);

    assertEquals(resolution, generatedResult.getWidth());
    assertEquals(resolution, generatedResult.getHeight());
    assertEquals(goldenResult, generatedResult);
  }

}
