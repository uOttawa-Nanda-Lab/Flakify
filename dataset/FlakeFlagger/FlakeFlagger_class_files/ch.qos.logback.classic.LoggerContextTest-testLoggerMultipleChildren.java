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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.classic.turbo.NOPTurboFilter;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.status.StatusManager;

public class LoggerContextTest {
  LoggerContext lc;

  @Before
  public void setUp() throws Exception {
    lc = new LoggerContext();
    lc.setName("x");
  }

  int instanceCount() {
    return lc.getLoggerList().size();
  }

  @Test public void testLoggerMultipleChildren(){assertEquals(1,instanceCount());Logger xy0=lc.getLogger("x.y0");LoggerTestHelper.assertNameEquals(xy0,"x.y0");Logger xy1=lc.getLogger("x.y1");LoggerTestHelper.assertNameEquals(xy1,"x.y1");LoggerTestHelper.assertLevels(null,xy0,Level.DEBUG);LoggerTestHelper.assertLevels(null,xy1,Level.DEBUG);assertEquals(4,instanceCount());for (int i=0;i < 100;i++){Logger xy_i=lc.getLogger("x.y" + i);LoggerTestHelper.assertNameEquals(xy_i,"x.y" + i);LoggerTestHelper.assertLevels(null,xy_i,Level.DEBUG);}assertEquals(102,instanceCount());}

}