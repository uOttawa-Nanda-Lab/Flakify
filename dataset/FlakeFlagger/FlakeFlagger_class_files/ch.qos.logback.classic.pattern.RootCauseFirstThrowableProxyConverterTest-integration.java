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

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ch.qos.logback.classic.util.TeztHelper.makeNestedException;
import static ch.qos.logback.classic.util.TeztHelper.positionOf;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-08-15, 18:34:21
 */
public class RootCauseFirstThrowableProxyConverterTest {

  private LoggerContext context = new LoggerContext();
  private ThrowableProxyConverter converter = new RootCauseFirstThrowableProxyConverter();
  private StringWriter stringWriter = new StringWriter();
  private PrintWriter printWriter = new PrintWriter(stringWriter);

  @Before
  public void setUp() throws Exception {
    converter.setContext(context);
    converter.start();
  }

  private ILoggingEvent createLoggingEvent(Throwable t) {
    return new LoggingEvent(this.getClass().getName(), context
        .getLogger(Logger.ROOT_LOGGER_NAME), Level.DEBUG, "test message", t,
        null);
  }

  @Test
  public void integration() {
    //given
    PatternLayout pl = new PatternLayout();
    pl.setContext(context);
    pl.setPattern("%m%rEx%n");
    pl.start();

    //when
    ILoggingEvent e = createLoggingEvent(new Exception("x"));
    String result = pl.doLayout(e);

    //then
    // make sure that at least some package data was output
    Pattern p = Pattern.compile(" \\[junit.*\\]");
    Matcher m = p.matcher(result);
    int i = 0;
    while(m.find()) {
      i++;
    }
    assertThat(i).isGreaterThan(5);
  }

}
