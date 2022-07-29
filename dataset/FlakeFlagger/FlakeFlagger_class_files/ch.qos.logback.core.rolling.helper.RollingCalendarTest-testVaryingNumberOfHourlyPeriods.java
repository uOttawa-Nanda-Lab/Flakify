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
package ch.qos.logback.core.rolling.helper;

import java.util.Date;

import junit.framework.TestCase;

public class RollingCalendarTest extends TestCase {

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testVaryingNumberOfHourlyPeriods() {
	RollingCalendar rc = new RollingCalendar();
	rc.init("yyyy-MM-dd_HH");
	long MILLIS_IN_HOUR = 3600 * 1000;
	for (int p = 100; p > -100; p--) {
		long now = 1223325293589L;
		Date result = rc.getRelativeDate(new Date(now), p);
		long expected = now - (now % (MILLIS_IN_HOUR)) + p * MILLIS_IN_HOUR;
		assertEquals(expected, result.getTime());
	}
}
}
