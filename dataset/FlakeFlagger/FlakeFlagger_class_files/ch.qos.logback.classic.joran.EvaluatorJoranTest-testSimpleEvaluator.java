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

  @Test public void testSimpleEvaluator() throws NullPointerException,EvaluationException,JoranException{JoranConfigurator jc=new JoranConfigurator();LoggerContext loggerContext=new LoggerContext();jc.setContext(loggerContext);jc.doConfigure(ClassicTestConstants.JORAN_INPUT_PREFIX + "simpleEvaluator.xml");Map evalMap=(Map)loggerContext.getObject(CoreConstants.EVALUATOR_MAP);assertNotNull(evalMap);JaninoEventEvaluator evaluator=(JaninoEventEvaluator)evalMap.get("msgEval");assertNotNull(evaluator);Logger logger=loggerContext.getLogger("xx");ILoggingEvent event0=new LoggingEvent("foo",logger,Level.DEBUG,"Hello world",null,null);assertTrue(evaluator.evaluate(event0));ILoggingEvent event1=new LoggingEvent("foo",logger,Level.DEBUG,"random blurb",null,null);assertFalse(evaluator.evaluate(event1));}
}
