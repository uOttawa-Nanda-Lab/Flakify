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
package ch.qos.logback.core;

import static ch.qos.logback.core.BasicStatusManager.MAX_HEADER_COUNT;
import static ch.qos.logback.core.BasicStatusManager.TAIL_SIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.Status;


public class BasicStatusManagerTest {

  
  BasicStatusManager bsm = new BasicStatusManager();
  
  @Test
  public void smoke() {
    bsm.add(new ErrorStatus("hello", this));
    assertEquals(Status.ERROR, bsm.getLevel());
    
    List<Status> statusList = bsm.getCopyOfStatusList();
    assertNotNull(statusList);
    assertEquals(1, statusList.size());
    assertEquals("hello", statusList.get(0).getMessage());
  }
}
