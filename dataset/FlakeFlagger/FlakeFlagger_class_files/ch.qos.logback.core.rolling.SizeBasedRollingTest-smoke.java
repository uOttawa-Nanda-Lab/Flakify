/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.core.rolling;

import ch.qos.logback.core.encoder.EchoEncoder;
import ch.qos.logback.core.util.CoreTestConstants;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SizeBasedRollingTest extends ScaffoldingForRollingTests {

  RollingFileAppender<Object> rfa = new RollingFileAppender<Object>();
  FixedWindowRollingPolicy fwrp = new FixedWindowRollingPolicy();
  SizeBasedTriggeringPolicy<Object> sizeBasedTriggeringPolicy = new SizeBasedTriggeringPolicy<Object>();
  EchoEncoder<Object> encoder = new EchoEncoder<Object>();


  @Before
  public void setUp() {
    super.setUp();
    fwrp.setContext(context);
    fwrp.setParent(rfa);
    rfa.setContext(context);
    sizeBasedTriggeringPolicy.setContext(context);
  }

  private void initRFA(String filename) {
    rfa.setEncoder(encoder);
    if (filename != null) {
      rfa.setFile(filename);
    }
  }

  void generic(String testName, String fileName, String filenamePattern, List<String> expectedFilenameList) throws InterruptedException, IOException {
    rfa.setName("ROLLING");
    initRFA(randomOutputDir + fileName);

    sizeBasedTriggeringPolicy.setMaxFileSize("100");
    fwrp.setMinIndex(0);
    fwrp.setFileNamePattern(randomOutputDir + filenamePattern);

    rfa.triggeringPolicy = sizeBasedTriggeringPolicy;
    rfa.rollingPolicy = fwrp;

    fwrp.start();
    sizeBasedTriggeringPolicy.start();
    rfa.start();

    int runLength = 40;
    String prefix = "hello";
    for (int i = 0; i < runLength; i++){
      Thread.sleep(10);
      rfa.doAppend(prefix + i);
    }
    rfa.stop();

    existenceCheck(expectedFilenameList);
    reverseSortedContentCheck(randomOutputDir, runLength, prefix);
  }

  @Test
  public void smoke() throws IOException, InterruptedException {
    expectedFilenameList.add(randomOutputDir + "a-sizeBased-smoke.log");
    expectedFilenameList.add(randomOutputDir + "sizeBased-smoke.0");
    expectedFilenameList.add(randomOutputDir + "sizeBased-smoke.1");
    generic("zipped", "a-sizeBased-smoke.log", "sizeBased-smoke.%i", expectedFilenameList);

  }
}
