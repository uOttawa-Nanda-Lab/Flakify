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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.ClassicTestConstants;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.net.SyslogConstants;
import ch.qos.logback.core.pattern.DynamicConverter;
import ch.qos.logback.core.pattern.FormatInfo;

public class ConverterTest {

  LoggerContext lc = new LoggerContext();
  Logger logger = lc.getLogger(ConverterTest.class);
  LoggingEvent le;
  List<String> optionList = new ArrayList<String>();

  // The LoggingEvent is massaged with an FCQN of FormattingConverter. This
  // forces the returned caller information to match the caller stack for this
  // this particular test.
  LoggingEvent makeLoggingEvent(Exception ex) {
    return new LoggingEvent(
        ch.qos.logback.core.pattern.FormattingConverter.class.getName(),
        logger, Level.INFO, "Some message", ex, null);
  }

  Exception getException(String msg, Exception cause) {
    return new Exception(msg, cause);
  }

  @Before
  public void setUp() throws Exception {
    Exception rootEx = getException("Innermost", null);
    Exception nestedEx = getException("Nested", rootEx);

    Exception ex = new Exception("Bogus exception", nestedEx);

    le = makeLoggingEvent(ex);
  }

  @Test
  public void testLogger() {
    {
      ClassicConverter converter = new LoggerConverter();
      StringBuilder buf = new StringBuilder();
      converter.write(buf, le);
      assertEquals(this.getClass().getName(), buf.toString());
    }

    {
      ClassicConverter converter = new LoggerConverter();
      this.optionList.add("20");
      converter.setOptionList(this.optionList);
      converter.start();
      StringBuilder buf = new StringBuilder();
      converter.write(buf, le);
      assertEquals("c.q.l.c.p.ConverterTest", buf.toString());
    }

    {
      DynamicConverter<ILoggingEvent> converter = new LoggerConverter();
      this.optionList.clear();
      this.optionList.add("0");
      converter.setOptionList(this.optionList);
      converter.start();
      StringBuilder buf = new StringBuilder();
      converter.write(buf, le);
      assertEquals("ConverterTest", buf.toString());
    }
  }
}
