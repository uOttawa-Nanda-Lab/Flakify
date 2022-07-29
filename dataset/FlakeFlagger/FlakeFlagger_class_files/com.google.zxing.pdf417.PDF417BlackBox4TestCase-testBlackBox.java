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

package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.BufferedImageLuminanceSource;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.AbstractBlackBoxTestCase;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.common.SummaryResults;
import com.google.zxing.common.TestResult;

import org.junit.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * This class tests Macro PDF417 barcode specific functionality. It ensures that information, which is split into
 * several barcodes can be properly combined again to yield the original data content.
 * 
 * @author Guenther Grau
 *
 */
public final class PDF417BlackBox4TestCase extends AbstractBlackBoxTestCase {
  private static final Logger log = Logger.getLogger(AbstractBlackBoxTestCase.class.getSimpleName());

  private static final Charset UTF8 = Charset.forName("UTF-8");
  private static final Charset ISO88591 = Charset.forName("ISO-8859-1");
  private static final String TEST_BASE_PATH_SUFFIX = "test/data/blackbox/pdf417-4";
  private final PDF417Reader barcodeReader = new PDF417Reader();

  private final List<TestResult> testResults = new ArrayList<TestResult>();
  private File testBase;

  @Test
  @Override
  public void testBlackBox() throws IOException {
    testPDF417BlackBoxCountingResults(true);
  }

  private static PDF417ResultMetadata getMeta(Result result) {
    return result.getResultMetadata() == null ? null : (PDF417ResultMetadata) result.getResultMetadata().get(
        ResultMetadataType.PDF417_EXTRA_METADATA);
  }

  private Result[] decode(BinaryBitmap source, boolean tryHarder) throws ReaderException {
    Map<DecodeHintType,Object> hints = new EnumMap<DecodeHintType,Object>(DecodeHintType.class);
    if (tryHarder) {
      hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
    }

    return barcodeReader.decodeMultiple(source, hints);
  }

  private Map<String,List<File>> getImageFileLists() {
    Map<String,List<File>> result = new HashMap<String,List<File>>();
    for (File file : getImageFiles()) {
      String testImageFileName = file.getName();
      String fileBaseName = testImageFileName.substring(0, testImageFileName.indexOf('-'));
      List<File> files = result.get(fileBaseName);
      if (files == null) {
        files = new ArrayList<File>();
        result.put(fileBaseName, files);
      }
      files.add(file);
    }
    return result;
  }

}
