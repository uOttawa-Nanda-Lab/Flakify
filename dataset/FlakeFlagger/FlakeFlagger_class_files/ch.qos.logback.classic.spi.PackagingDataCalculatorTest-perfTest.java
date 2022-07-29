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
package ch.qos.logback.classic.spi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

import ch.qos.logback.classic.util.TeztHelper;
import ch.qos.logback.core.util.SystemInfo;

public class PackagingDataCalculatorTest {

  double loop(int len, boolean withClassPackagingCalculation) {
    long start = System.nanoTime();
    for (int i = 0; i < len; i++) {
      doCalculateClassPackagingData(withClassPackagingCalculation);
    }
    return (1.0 * System.nanoTime() - start) / len / 1000;
  }

  @Test public void perfTest(){int len=1000;loop(len,false);loop(len,true);double d0=loop(len,false);System.out.println("without packaging info " + d0 + " microseconds");double d1=loop(len,true);System.out.println("with    packaging info " + d1 + " microseconds");int slackFactor=8;if (!SystemInfo.getJavaVendor().contains("Sun")){slackFactor=10;}assertTrue("computing class packaging data (" + d1 + ") should have been less than " + slackFactor + " times the time it takes to process an exception " + (d0 * slackFactor),d0 * slackFactor > d1);}

  private ClassLoader makeBogusClassLoader() throws MalformedURLException {
    ClassLoader currentClassLoader = this.getClass().getClassLoader();
    return new BogusClassLoader(new URL[] {},
        currentClassLoader);
  }

}
