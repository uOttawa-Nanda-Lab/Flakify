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
package ch.qos.logback.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.status.Status;

public class LoggerTest {

  LoggerContext lc = new LoggerContext();
  Logger root = lc.getLogger(Logger.ROOT_LOGGER_NAME);
  Logger loggerTest = lc.getLogger(LoggerTest.class);

  ListAppender<ILoggingEvent> listAppender = new ListAppender<ILoggingEvent>();

  void checkLevelThreshold(Logger logger, Level threshold) {

    if (Level.ERROR_INT >= threshold.levelInt) {
      assertTrue(logger.isErrorEnabled());
      assertTrue(logger.isEnabledFor(Level.ERROR));
    } else {
      assertFalse(logger.isErrorEnabled());
      assertFalse(logger.isEnabledFor(Level.ERROR));
    }

    if (Level.WARN_INT >= threshold.levelInt) {
      assertTrue(logger.isWarnEnabled());
      assertTrue(logger.isEnabledFor(Level.WARN));
    } else {
      assertFalse(logger.isWarnEnabled());
      assertFalse(logger.isEnabledFor(Level.WARN));
    }
    if (Level.INFO_INT >= threshold.levelInt) {
      assertTrue(logger.isInfoEnabled());
      assertTrue(logger.isEnabledFor(Level.INFO));
    } else {
      assertFalse(logger.isInfoEnabled());
      assertFalse(logger.isEnabledFor(Level.INFO));
    }
    if (Level.DEBUG_INT >= threshold.levelInt) {
      assertTrue(logger.isDebugEnabled());
      assertTrue(logger.isEnabledFor(Level.DEBUG));
    } else {
      assertFalse(logger.isDebugEnabled());
      assertFalse(logger.isEnabledFor(Level.DEBUG));
    }
    if (Level.TRACE_INT >= threshold.levelInt) {
      assertTrue(logger.isTraceEnabled());
      assertTrue(logger.isEnabledFor(Level.TRACE));
    } else {
      assertFalse(logger.isTraceEnabled());
      assertFalse(logger.isEnabledFor(Level.TRACE));
    }
  }

  @Test public void innerClass_II(){root.setLevel(Level.DEBUG);Logger a=lc.getLogger(this.getClass());a.setLevel(Level.INFO);Logger a_b=lc.getLogger(new Inner().getClass());assertEquals(Level.INFO,a_b.getEffectiveLevel());}

  
  class Inner {
  }

}
