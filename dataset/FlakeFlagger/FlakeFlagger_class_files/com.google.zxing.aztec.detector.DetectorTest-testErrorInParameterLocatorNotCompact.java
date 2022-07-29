/*
 * Copyright 2013 ZXing authors
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

package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector.Point;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Tests for the Detector
 *
 * @author Frank Yellin
 */
public final class DetectorTest extends Assert {

  private static final Charset LATIN_1 = Charset.forName("ISO-8859-1");

  @Test public void testErrorInParameterLocatorNotCompact() throws Exception{String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghijklmnopqrstuvwxyz";testErrorInParameterLocator(alphabet + alphabet + alphabet);}

  // Zooms a bit matrix so that each bit is factor x factor
  private static BitMatrix makeLarger(BitMatrix input, int factor) {
    int width = input.getWidth();
    BitMatrix output = new BitMatrix(width * factor);
    for (int inputY = 0; inputY < width; inputY++) {
      for (int inputX = 0; inputX < width; inputX++) {
        if (input.get(inputX, inputY)) {
          output.setRegion(inputX * factor, inputY * factor, factor, factor);
        }
      }
    }
    return output;
  }

  // Returns a list of the four rotations of the BitMatrix.
  private static Iterable<BitMatrix> getRotations(BitMatrix matrix0) {
    BitMatrix matrix90 = rotateRight(matrix0);
    BitMatrix matrix180 = rotateRight(matrix90);
    BitMatrix matrix270 = rotateRight(matrix180);
    return Arrays.asList(matrix0, matrix90, matrix180, matrix270);
  }

  // Rotates a square BitMatrix to the right by 90 degrees
  private static BitMatrix rotateRight(BitMatrix input) {
    int width = input.getWidth();
    BitMatrix result = new BitMatrix(width);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < width; y++) {
        if (input.get(x,y)) {
          result.set(y, width - x - 1);
        }
      }
    }
    return result;
  }

  // Returns the transpose of a bit matrix, which is equivalent to rotating the
  // matrix to the right, and then flipping it left-to-right
  private static BitMatrix transpose(BitMatrix input) {
    int width = input.getWidth();
    BitMatrix result = new BitMatrix(width);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < width; y++) {
        if (input.get(x, y)) {
          result.set(y, x);
        }
      }
    }
    return result;
  }

  private static BitMatrix clone(BitMatrix input)  {
    int width = input.getWidth();
    BitMatrix result = new BitMatrix(width);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < width; y++) {
        if (input.get(x,y)) {
          result.set(x,y);
        }
      }
    }
    return result;
  }

  private static List<Point> getOrientationPoints(AztecCode code) {
    int center = code.getMatrix().getWidth() / 2;
    int offset = code.isCompact() ? 5 : 7;
    List<Point> result = new ArrayList<Point>();
    for (int xSign = -1; xSign <= 1; xSign += 2) {
      for (int ySign = -1; ySign <= 1; ySign += 2) {
        result.add(new Point(center + xSign * offset, center + ySign * offset));
        result.add(new Point(center + xSign * (offset - 1), center + ySign * offset));
        result.add(new Point(center + xSign * offset, center + ySign * (offset - 1)));
      }
    }
    return result;
  }

}
