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
package ch.qos.logback.classic.rolling;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import ch.qos.logback.core.util.StatusPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.classic.ClassicTestConstants;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.ScaffoldingForRollingTests;
import ch.qos.logback.core.rolling.TimeBasedFileNamingAndTriggeringPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.status.StatusChecker;

public class TimeBasedRollingWithConfigFileTest extends
        ScaffoldingForRollingTests {

  LoggerContext lc = new LoggerContext();
  StatusChecker statusChecker = new StatusChecker(lc);
  Logger logger = lc.getLogger(this.getClass());
  int fileSize = 0;
  int fileIndexCounter = -1;
  int sizeThreshold;

  @Before
  @Override
  public void setUp() {
    lc.setName("test");
    super.setUp();
    lc.putProperty("randomOutputDir", randomOutputDir);
  }

  void loadConfig(String confifFile) throws JoranException {
    JoranConfigurator jc = new JoranConfigurator();
    jc.setContext(lc);
    jc.doConfigure(confifFile);
    currentTime = System.currentTimeMillis();
    recomputeRolloverThreshold(currentTime);
  }

  @Test
  public void basic() throws Exception {
    String testId = "basic";
    lc.putProperty("testId", testId);
    loadConfig(ClassicTestConstants.JORAN_INPUT_PREFIX + "rolling/" + testId
            + ".xml");
    statusChecker.assertIsErrorFree();

    Logger root = lc.getLogger(Logger.ROOT_LOGGER_NAME);

    expectedFilenameList.add(randomOutputDir + "z" + testId);

    RollingFileAppender<ILoggingEvent> rfa = (RollingFileAppender<ILoggingEvent>) root
            .getAppender("ROLLING");

    TimeBasedRollingPolicy tprp = (TimeBasedRollingPolicy<ILoggingEvent>) rfa
            .getTriggeringPolicy();
    TimeBasedFileNamingAndTriggeringPolicy tbnatp = tprp
            .getTimeBasedFileNamingAndTriggeringPolicy();

    String prefix = "Hello---";
    int runLength = 4;
    for (int i = 0; i < runLength; i++) {
      logger.debug(prefix + i);
      addExpectedFileNamedIfItsTime_ByDate(randomOutputDir, testId, false);
      incCurrentTime(500);
      tbnatp.setCurrentTime(currentTime);
    }

    existenceCheck(expectedFilenameList);
    sortedContentCheck(randomOutputDir, runLength, prefix);
  }

  void addExpectedFileNamedIfItsTime(String testId, String msg,
                                     boolean gzExtension) {
    fileSize += msg.getBytes().length;

    if (passThresholdTime(nextRolloverThreshold)) {
      fileIndexCounter = 0;
      fileSize = 0;
      addExpectedFileName(testId, getDateOfPreviousPeriodsStart(),
              fileIndexCounter, gzExtension);
      recomputeRolloverThreshold(currentTime);
      return;
    }

    // windows can delay file size changes, so we only allow for
    // fileIndexCounter 0 and 1
    if ((fileIndexCounter < 1) && fileSize > sizeThreshold) {
      addExpectedFileName(testId, getDateOfPreviousPeriodsStart(),
              ++fileIndexCounter, gzExtension);
      fileSize = -1;
      return;
    }
  }

  void addExpectedFileName(String testId, Date date, int fileIndexCounter,
                           boolean gzExtension) {

    String fn = randomOutputDir + testId + "-" + SDF.format(date) + "."
            + fileIndexCounter;
    System.out.println("Adding " + fn);
    if (gzExtension) {
      fn += ".gz";
    }
    expectedFilenameList.add(fn);
  }
}
