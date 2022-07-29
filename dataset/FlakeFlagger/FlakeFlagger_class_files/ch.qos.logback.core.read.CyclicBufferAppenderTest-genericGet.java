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
package ch.qos.logback.core.read;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CyclicBufferAppenderTest {

  private CyclicBufferAppender<String> cyclicBufferAppender;

  @Before
  public void before() {
    cyclicBufferAppender = new CyclicBufferAppender<String>();
    cyclicBufferAppender.start();
  }

  @Test public void genericGet(){cyclicBufferAppender.append("Some string");String foo=cyclicBufferAppender.get(0);assertEquals("Some string",foo);}


}
