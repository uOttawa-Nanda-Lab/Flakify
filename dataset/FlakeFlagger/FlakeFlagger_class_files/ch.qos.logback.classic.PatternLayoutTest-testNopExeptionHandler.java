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
package ch.qos.logback.classic;

import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.pattern.ConverterTest;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.testUtil.SampleConverter;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.pattern.parser.AbstractPatternLayoutBaseTest;
import ch.qos.logback.core.testUtil.StringListAppender;
import ch.qos.logback.core.util.OptionHelper;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import static ch.qos.logback.classic.ClassicTestConstants.ISO_REGEX;
import static ch.qos.logback.classic.ClassicTestConstants.MAIN_REGEX;
import static org.junit.Assert.*;

public class PatternLayoutTest extends AbstractPatternLayoutBaseTest<ILoggingEvent> {

  private PatternLayout pl = new PatternLayout();
  private LoggerContext lc = new LoggerContext();
  Logger logger = lc.getLogger(ConverterTest.class);
  Logger root = lc.getLogger(Logger.ROOT_LOGGER_NAME);

  ILoggingEvent le;

	@Before
  public void setUp() {
    pl.setContext(lc);
  }

  ILoggingEvent makeLoggingEvent(Exception ex) {
    return new LoggingEvent(
            ch.qos.logback.core.pattern.FormattingConverter.class.getName(),
            logger, Level.INFO, "Some message", ex, null);
  }

  @Test public void testNopExeptionHandler(){pl.setPattern("%nopex %m%n");pl.start();String val=pl.doLayout(le);assertTrue(!val.contains("java.lang.Exception: Bogus exception"));}

  void configure(String file) throws JoranException {
    JoranConfigurator jc = new JoranConfigurator();
    jc.setContext(lc);
    jc.doConfigure(file);
  }


}
