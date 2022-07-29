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
package ch.qos.logback.classic.boolex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.MDC;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.pattern.ConverterTest;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.JaninoEventEvaluatorBase;
import ch.qos.logback.core.boolex.Matcher;
import ch.qos.logback.core.filter.EvaluatorFilter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.testUtil.RandomUtil;
import ch.qos.logback.core.util.StatusPrinter;

public class JaninoEventEvaluatorTest {

  LoggerContext loggerContext = new LoggerContext();
  Logger logger = loggerContext.getLogger(ConverterTest.class);

  Matcher matcherX = new Matcher();

  JaninoEventEvaluator jee = new JaninoEventEvaluator();

  int diff = RandomUtil.getPositiveInt();
  
  LoggingEvent makeLoggingEvent(Exception ex) {
    return new LoggingEvent(
        ch.qos.logback.core.pattern.FormattingConverter.class.getName(),
        logger, Level.INFO, "Some message", ex, null);
  }

  @Test
  public void testBasic() throws Exception {
    jee.setExpression("message.equals(\"Some message\")");
    jee.start();

    StatusPrinter.print(loggerContext);
    ILoggingEvent event = makeLoggingEvent(null);
    assertTrue(jee.evaluate(event));
  }

  static final long LEN = 10 * 1000;

  // with 6 parameters 400 nanos
  // with 7 parameters 460 nanos (all levels + selected fields from
  // LoggingEvent)
  // with 10 parameters 510 nanos (all levels + fields)
  void loop(JaninoEventEvaluator jee, String msg) throws Exception {
    ILoggingEvent event = makeLoggingEvent(null);
    // final long start = System.nanoTime();
    for (int i = 0; i < LEN; i++) {
      jee.evaluate(event);
    }
    // final long end = System.nanoTime();
    // System.out.println(msg + (end - start) / LEN + " nanos");
  }
}
