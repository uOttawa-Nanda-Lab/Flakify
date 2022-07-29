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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.ClassicTestConstants;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.boolex.JaninoEventEvaluator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.joran.spi.JoranException;


public class EvaluatorJoranTest  {

  @Test public void testMultipleConditionsInExpression() throws NullPointerException,EvaluationException{LoggerContext loggerContext=new LoggerContext();Logger logger=loggerContext.getLogger("xx");JaninoEventEvaluator ee=new JaninoEventEvaluator();ee.setName("testEval");ee.setContext(loggerContext);ee.setExpression("message.contains(\"stacktrace\") && message.contains(\"logging\")");ee.start();String message="stacktrace bla bla logging";ILoggingEvent event=new LoggingEvent(this.getClass().getName(),logger,Level.DEBUG,message,null,null);assertTrue(ee.evaluate(event));}
}
