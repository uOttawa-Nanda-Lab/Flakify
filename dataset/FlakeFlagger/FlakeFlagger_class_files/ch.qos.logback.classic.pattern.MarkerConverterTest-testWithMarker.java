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
package ch.qos.logback.classic.pattern;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;

public class MarkerConverterTest {
  
  LoggerContext lc;
  MarkerConverter converter;
  // use a different facotry for each test so that they are independent
  IMarkerFactory markerFactory = new BasicMarkerFactory();
  
  @Before
  public void setUp() throws Exception {
    lc = new LoggerContext();
    converter = new MarkerConverter();
    converter.start();
  }
  
  @Test public void testWithMarker(){String name="test";Marker marker=markerFactory.getMarker(name);String result=converter.convert(createLoggingEvent(marker));assertEquals(name,result);}
  
  private ILoggingEvent createLoggingEvent(Marker marker) {
    LoggingEvent le = new LoggingEvent(this.getClass().getName(), lc.getLogger(Logger.ROOT_LOGGER_NAME),
        Level.DEBUG, "test message", null, null);
    le.setMarker(marker);
    return le;
  }
}
