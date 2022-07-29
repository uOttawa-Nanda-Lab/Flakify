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

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.appender.AbstractAppenderTest;
import ch.qos.logback.core.encoder.DummyEncoder;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusChecker;
import ch.qos.logback.core.testUtil.RandomUtil;
import ch.qos.logback.core.util.CoreTestConstants;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RollingFileAppenderTest extends AbstractAppenderTest<Object> {

  RollingFileAppender<Object> rfa = new RollingFileAppender<Object>();
  Context context = new ContextBase();

  TimeBasedRollingPolicy<Object> tbrp = new TimeBasedRollingPolicy<Object>();
  int diff = RandomUtil.getPositiveInt();
  String randomOutputDir = CoreTestConstants.OUTPUT_DIR_PREFIX + diff + "/";

  @Before
  public void setUp() throws Exception {
    // noStartTest fails if the context is set in setUp
    // rfa.setContext(context);

    rfa.setEncoder(new DummyEncoder<Object>());
    rfa.setName("test");
    tbrp.setContext(context);
    tbrp.setParent(rfa);
  }

  @Test public void testFileNameWithParenthesis(){rfa.setContext(context);tbrp.setFileNamePattern(randomOutputDir + "program(x86)/toto-%d.log");tbrp.start();rfa.setRollingPolicy(tbrp);rfa.start();rfa.doAppend("hello");}

}
