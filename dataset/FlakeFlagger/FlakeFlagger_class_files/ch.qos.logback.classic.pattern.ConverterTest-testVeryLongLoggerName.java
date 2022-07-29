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

  @Test public void testVeryLongLoggerName(){ClassicConverter converter=new LoggerConverter();this.optionList.add("5");converter.setOptionList(this.optionList);converter.start();StringBuilder buf=new StringBuilder();char c='a';int extraParts=3;int totalParts=ClassicConstants.MAX_DOTS + extraParts;StringBuilder loggerNameBuf=new StringBuilder();StringBuilder witness=new StringBuilder();for (int i=0;i < totalParts;i++){loggerNameBuf.append(c).append(c).append(c);if (i < ClassicConstants.MAX_DOTS){witness.append(c);} else {witness.append(c).append(c).append(c);}loggerNameBuf.append('.');witness.append('.');}loggerNameBuf.append("zzzzzz");witness.append("zzzzzz");le.setLoggerName(loggerNameBuf.toString());converter.write(buf,le);assertEquals(witness.toString(),buf.toString());}
}
