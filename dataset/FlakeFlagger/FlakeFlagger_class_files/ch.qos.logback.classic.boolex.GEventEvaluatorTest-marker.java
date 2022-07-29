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


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.status.StatusChecker;
import ch.qos.logback.core.util.ContextUtil;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.helpers.BogoPerf;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Ceki G&uuml;c&uuml;
 */

public class GEventEvaluatorTest {

  LoggerContext context = new LoggerContext();
  StatusChecker statusChecker = new StatusChecker(context);
  int LEN = 100 * 1000;

  Logger logger = context.getLogger(this.getClass());
  Marker markerA = MarkerFactory.getMarker("A");

  GEventEvaluator gee = new GEventEvaluator();

  @Before
  public void setUp() {
    gee.setContext(context);
  }

  LoggingEvent makeEvent(String msg) {
    return makeEvent(Level.DEBUG, msg, null, null);
  }

  LoggingEvent makeEvent(Level level, String msg, Throwable t, Object[] argArray) {
    return new LoggingEvent(this.getClass().getName(), logger, level, msg, t, argArray);
  }

  void doEvaluateAndCheck(String expression, ILoggingEvent event, boolean expected) throws EvaluationException {
    gee.setExpression(expression);
    gee.start();

    StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    assertTrue(statusChecker.isErrorFree(0));

    ContextUtil contextUtil = new ContextUtil(context);
    contextUtil.addGroovyPackages(context.getFrameworkPackages());
    contextUtil.addFrameworkPackage(context.getFrameworkPackages(), "ch.qos.logback.classic.boolex");

    boolean result = gee.evaluate(event);
    assertEquals(expected, result);
  }

  @Test public void marker() throws EvaluationException{LoggingEvent event=makeEvent("x");event.setMarker(markerA);doEvaluateAndCheck("e.marker?.name == 'A'",event,true);}

  double loop(GEventEvaluator gee) throws EvaluationException {
    long start = System.nanoTime();
    ILoggingEvent event = makeEvent("x");
    for (int i = 0; i < LEN; i++) {
      gee.evaluate(event);
    }
    long end = System.nanoTime();
    return (end - start) / LEN;
  }

}