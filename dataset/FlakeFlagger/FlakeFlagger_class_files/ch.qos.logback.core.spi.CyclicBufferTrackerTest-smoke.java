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
package ch.qos.logback.core.spi;

import ch.qos.logback.core.helpers.CyclicBuffer;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Ceki G&uuml;c&uuml;
 */
public class CyclicBufferTrackerTest {


  CyclicBufferTracker<Object> tracker = new CyclicBufferTracker<Object>();
  String key = "a";

  @Test
  public void smoke() {
    long now = 3000;
    CyclicBuffer<Object> cb = tracker.getOrCreate(key, now);
    assertEquals(cb, tracker.getOrCreate(key, now++));
    now += CyclicBufferTracker.DEFAULT_TIMEOUT + 1000;
    tracker.removeStaleComponents(now);
    assertEquals(0, tracker.liveKeysAsOrderedList().size());
    assertEquals(0, tracker.getComponentCount());
  }




}
