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
package ch.qos.logback.core.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

public class TimeUtilTest  {

  @Test public void testMonth(){long now=1164045917522L;now=correctBasedOnTimeZone(now);long expected=1164931200000L;expected=correctBasedOnTimeZone(expected);long computed=TimeUtil.computeStartOfNextMonth(now);assertEquals(expected - now,1000 * (3600 * (5 + 24 * 10) + 60 * 54 + 42) + 478);assertEquals(expected,computed);}
  
  private long correctBasedOnTimeZone(long gmtLong) {
    int offset = TimeZone.getDefault().getRawOffset();
    return gmtLong - offset;
  }

}
