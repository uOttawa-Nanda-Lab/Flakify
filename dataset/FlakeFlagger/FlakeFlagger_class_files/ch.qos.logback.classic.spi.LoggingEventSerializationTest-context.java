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
package ch.qos.logback.classic.spi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.net.LoggingEventPreSerializationTransformer;
import ch.qos.logback.core.spi.PreSerializationTransformer;

public class LoggingEventSerializationTest {

  LoggerContext lc;
  Logger logger;

  ByteArrayOutputStream bos;
  ObjectOutputStream oos;
  ObjectInputStream inputStream;
  PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();

  @Before
  public void setUp() throws Exception {
    lc = new LoggerContext();
    lc.setName("testContext");
    logger = lc.getLogger(Logger.ROOT_LOGGER_NAME);
    // create the byte output stream
    bos = new ByteArrayOutputStream();
    oos = new ObjectOutputStream(bos);
  }

  @Test public void context() throws Exception{lc.putProperty("testKey","testValue");ILoggingEvent event=createLoggingEvent();ILoggingEvent remoteEvent=writeAndRead(event);checkForEquality(event,remoteEvent);assertNotNull(remoteEvent.getLoggerName());assertEquals(Logger.ROOT_LOGGER_NAME,remoteEvent.getLoggerName());LoggerContextVO loggerContextRemoteView=remoteEvent.getLoggerContextVO();assertNotNull(loggerContextRemoteView);assertEquals("testContext",loggerContextRemoteView.getName());Map<String, String> props=loggerContextRemoteView.getPropertyMap();assertNotNull(props);assertEquals("testValue",props.get("testKey"));}

  private LoggingEvent createLoggingEvent() {
    return new LoggingEvent(this.getClass().getName(), logger,
        Level.DEBUG, "test message", null, null);
  }

  private void checkForEquality(ILoggingEvent original,
      ILoggingEvent afterSerialization) {
    assertEquals(original.getLevel(), afterSerialization.getLevel());
    assertEquals(original.getFormattedMessage(), afterSerialization
        .getFormattedMessage());
    assertEquals(original.getMessage(), afterSerialization.getMessage());

    System.out.println();

    ThrowableProxyVO witness = ThrowableProxyVO.build(original
        .getThrowableProxy());
    assertEquals(witness, afterSerialization.getThrowableProxy());

  }

  private ILoggingEvent writeAndRead(ILoggingEvent event) throws IOException,
      ClassNotFoundException {
    Serializable ser = pst.transform(event);
    oos.writeObject(ser);
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    inputStream = new ObjectInputStream(bis);

    return (ILoggingEvent) inputStream.readObject();
  }

}
