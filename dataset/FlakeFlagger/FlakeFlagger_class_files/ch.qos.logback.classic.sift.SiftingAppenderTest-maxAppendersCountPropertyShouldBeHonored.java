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
package ch.qos.logback.classic.sift;

import ch.qos.logback.classic.*;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.helpers.NOPAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.sift.AppenderTracker;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import ch.qos.logback.core.spi.ComponentTracker;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.StatusChecker;
import ch.qos.logback.core.testUtil.RandomUtil;
import ch.qos.logback.core.testUtil.StringListAppender;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.slf4j.MDC;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SiftingAppenderTest {

  static String SIFT_FOLDER_PREFIX = ClassicTestConstants.JORAN_INPUT_PREFIX + "sift/";

  LoggerContext loggerContext = new LoggerContext();
  Logger logger = loggerContext.getLogger(this.getClass().getName());
  Logger root = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
  StatusChecker statusChecker = new StatusChecker(loggerContext);
  int diff = RandomUtil.getPositiveInt();
  int now = 0;

  protected void configure(String file) throws JoranException {
    JoranConfigurator jc = new JoranConfigurator();
    jc.setContext(loggerContext);
    jc.doConfigure(file);
  }

  private AppenderTracker<ILoggingEvent> getAppenderTracker() {
    SiftingAppender ha = (SiftingAppender) root.getAppender("SIFT");
    return ha.getAppenderTracker();
  }

  @Test public void maxAppendersCountPropertyShouldBeHonored() throws JoranException{configure(SIFT_FOLDER_PREFIX + "maxAppenderCount.xml");int max=5;SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");String mdcKey="max";for (int i=0;i <= max;i++){MDC.put(mdcKey,"" + (diff + i));LoggingEvent event=new LoggingEvent("",logger,Level.DEBUG,"max" + i,null,null);event.setTimeStamp(now);sa.doAppend(event);now+=AbstractComponentTracker.WAIT_BETWEEN_SUCCESSIVE_REMOVAL_ITERATIONS;}AppenderTracker<ILoggingEvent> tracker=sa.getAppenderTracker();assertEquals(max,tracker.allKeys().size());assertNull(tracker.find("" + (diff + 0)));for (int i=1;i <= max;i++){assertNotNull(tracker.find("" + (diff + i)));}}

}
