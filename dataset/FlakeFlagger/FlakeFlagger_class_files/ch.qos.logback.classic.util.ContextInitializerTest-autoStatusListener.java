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
package ch.qos.logback.classic.util;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.ClassicTestConstants;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.TrivialStatusListener;
import sun.security.jca.ProviderList;

public class ContextInitializerTest {

  LoggerContext loggerContext = new LoggerContext();
  Logger root = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);

  @Before
  public void setUp() throws Exception {
  }

  @Test public void autoStatusListener() throws JoranException{System.setProperty(ContextInitializer.STATUS_LISTENER_CLASS,TrivialStatusListener.class.getName());List<StatusListener> statusListenerList=loggerContext.getStatusManager().getCopyOfStatusListenerList();assertEquals(0,statusListenerList.size());doAutoConfigFromSystemProperties(ClassicTestConstants.INPUT_PREFIX + "autoConfig.xml");statusListenerList=loggerContext.getStatusManager().getCopyOfStatusListenerList();assertTrue(statusListenerList.size() + " should be 1",statusListenerList.size() == 1);TrivialStatusListener tsl=(TrivialStatusListener)statusListenerList.get(0);assertTrue("expecting at least one event in list",tsl.list.size() > 0);}
}
