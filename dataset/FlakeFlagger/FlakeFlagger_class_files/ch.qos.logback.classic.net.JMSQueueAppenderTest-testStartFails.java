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
package ch.qos.logback.classic.net;

import java.io.Serializable;

import javax.jms.ObjectMessage;

import junit.framework.TestCase;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.net.mock.MockQueue;
import ch.qos.logback.classic.net.mock.MockQueueConnectionFactory;
import ch.qos.logback.classic.net.mock.MockQueueSender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.util.MockInitialContext;
import ch.qos.logback.classic.util.MockInitialContextFactory;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.spi.PreSerializationTransformer;

public class JMSQueueAppenderTest extends TestCase {

  ch.qos.logback.core.Context context;
  JMSQueueAppender appender;
  PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();
  
  public void testStartFails() {
	appender.queueBindingName = null;
	appender.start();
	assertFalse(appender.isStarted());
}

  private ILoggingEvent createLoggingEvent() {
    LoggingEvent le = new LoggingEvent();
    le.setLevel(Level.DEBUG);
    le.setMessage("test message");
    le.setTimeStamp(System.currentTimeMillis());
    le.setThreadName(Thread.currentThread().getName());
    return le;
  }
}
