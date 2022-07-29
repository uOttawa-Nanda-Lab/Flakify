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
package ch.qos.logback.classic.jul;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.testUtil.RandomUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelChangePropagatorTest {
  int rand = RandomUtil.getPositiveInt();
  LoggerContext loggerContext = new LoggerContext();
  LevelChangePropagator levelChangePropagator = new LevelChangePropagator();

  @Before
  public void setUp() {
    levelChangePropagator.setContext(loggerContext);
    loggerContext.addListener(levelChangePropagator);
  }

  void checkLevelChange(String loggerName, Level level) {
    Logger logger = loggerContext.getLogger(loggerName);
    logger.setLevel(level);
    java.util.logging.Logger julLogger = JULHelper.asJULLogger(logger);
    java.util.logging.Level julLevel = JULHelper.asJULLevel(level);

    assertEquals(julLevel, julLogger.getLevel());
  }

  @Test public void root(){checkLevelChange(Logger.ROOT_LOGGER_NAME,Level.TRACE);}

}
