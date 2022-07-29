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
package ch.qos.logback.classic.joran;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ch.qos.logback.classic.jul.JULHelper;
import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.testUtil.RandomUtil;
import ch.qos.logback.core.util.CachingDateFormatter;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.MDC;

import ch.qos.logback.classic.ClassicTestConstants;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.turbo.DebugUsersTurboFilter;
import ch.qos.logback.classic.turbo.NOPTurboFilter;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.status.StatusChecker;
import ch.qos.logback.core.testUtil.StringListAppender;


import static org.junit.Assert.*;

public class JoranConfiguratorTest {

  LoggerContext loggerContext = new LoggerContext();
  Logger logger = loggerContext.getLogger(this.getClass().getName());
  Logger root = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
  StatusChecker checker = new StatusChecker(loggerContext);
  int diff = RandomUtil.getPositiveInt();

  void configure(String file) throws JoranException {
    JoranConfigurator jc = new JoranConfigurator();
    jc.setContext(loggerContext);
    loggerContext.putProperty("diff", "" + diff);
    jc.doConfigure(file);
  }

  @Test
  public void level() throws JoranException {
    configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "simpleLevel.xml");
    ListAppender listAppender = (ListAppender) root.getAppender("LIST");
    assertEquals(0, listAppender.list.size());
    String msg = "hello world";
    logger.debug(msg);
    assertEquals(0, listAppender.list.size());
  }

  void verifyJULLevel(String loggerName, Level expectedLevel) {
    java.util.logging.Logger julLogger = JULHelper.asJULLogger(loggerName);
    java.util.logging.Level julLevel = julLogger.getLevel();

    if (expectedLevel == null) {
      assertNull(julLevel);
    } else {
      assertEquals(JULHelper.asJULLevel(expectedLevel), julLevel);
    }


  }
}
