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
package ch.qos.logback.core.recovery;

import static org.junit.Assert.*;

import org.junit.Test;

public class RecoveryCoordinatorTest {

  RecoveryCoordinator rc = new RecoveryCoordinator();
  long now = System.currentTimeMillis();

  @Test public void longTermFailure(){long offset=RecoveryCoordinator.BACKOFF_COEFFICIENT_MIN;int tooSoonCount=0;for (int i=0;i < 16;i++){rc.setCurrentTime(now + offset);if (rc.isTooSoon()){System.out.println("isTooSoon successful at " + (offset));tooSoonCount++;} else {}offset*=2;}assertEquals(8,tooSoonCount);}
}
