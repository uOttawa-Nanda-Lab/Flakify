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
package ch.qos.logback.classic.turbo;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.qos.logback.core.spi.FilterReply;

public class DuplicateMessageFilterTest {

  @Test
  public void smoke() {
    DuplicateMessageFilter dmf = new DuplicateMessageFilter();
    dmf.setAllowedRepetitions(0);
    dmf.start();
    assertEquals(FilterReply.NEUTRAL, dmf.decide(null, null, null, "x", null,
        null));
    assertEquals(FilterReply.NEUTRAL, dmf.decide(null, null, null, "y", null,
        null));
    assertEquals(FilterReply.DENY, dmf
        .decide(null, null, null, "x", null, null));
    assertEquals(FilterReply.DENY, dmf
        .decide(null, null, null, "y", null, null));
  }

}
