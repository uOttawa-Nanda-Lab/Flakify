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
package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.testUtil.RandomUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Relatively straightforward unit tests for AppenderTracker.
 */
public class AppenderTrackerTest {

  Context context = new ContextBase();
  ListAppenderFactory listAppenderFactory = new ListAppenderFactory();
  int diff = RandomUtil.getPositiveInt();
  AppenderTracker<Object> appenderTracker = new AppenderTracker<Object>(context, listAppenderFactory);
  String key = "k-" + diff;
  long now = 3000;

  @Before
  public void setUp() {
  }

  @Test public void trackerShouldHonorTimeoutParameter(){List<Appender<Object>> appenderList=new ArrayList<Appender<Object>>();int timeout=2;appenderTracker.setTimeout(timeout);for (int i=0;i <= timeout;i++){Appender a=appenderTracker.getOrCreate(key + "-" + i,now++);appenderList.add(a);}long numComponentsCreated=timeout + 1;assertEquals(numComponentsCreated,appenderTracker.allKeys().size());appenderTracker.removeStaleComponents(now++);assertEquals(numComponentsCreated - 1,appenderTracker.allKeys().size());assertNull(appenderTracker.find(key + "-" + 0));assertFalse(appenderList.get(0).isStarted());for (int i=1;i <= timeout;i++){assertNotNull(appenderTracker.find(key + "-" + i));assertTrue(appenderList.get(i).isStarted());}}

  // ======================================================================
  static class ListAppenderFactory implements AppenderFactory<Object> {

    public Appender<Object> buildAppender(Context context, String discriminatingValue) throws JoranException {
      ListAppender<Object> la = new ListAppender<Object>();
      la.setContext(context);
      la.setName(discriminatingValue);
      la.start();
      return la;
    }
  }
}
