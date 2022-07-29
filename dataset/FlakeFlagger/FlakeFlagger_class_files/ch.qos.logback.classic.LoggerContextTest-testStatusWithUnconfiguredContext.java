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

  @Test public void testStatusWithUnconfiguredContext(){Logger logger=lc.getLogger(LoggerContextTest.class);for (int i=0;i < 3;i++){logger.debug("test");}logger=lc.getLogger("x.y.z");for (int i=0;i < 3;i++){logger.debug("test");}StatusManager sm=lc.getStatusManager();assertTrue("StatusManager has recieved too many messages",sm.getCount() == 1);}

}