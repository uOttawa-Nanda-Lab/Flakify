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

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.WarnStatus;

public class StatusPrinterTest {

  ByteArrayOutputStream outputStream;
  PrintStream ps;
  
  @Before
  public void setUp() throws Exception {
    outputStream = new ByteArrayOutputStream();
    ps = new PrintStream(outputStream);
    StatusPrinter.setPrintStream(ps);
  }

  @Test
  public void testBasic() {
    Context context = new ContextBase();
    context.getStatusManager().add(new InfoStatus("test", this));
    StatusPrinter.print(context);
    String result = outputStream.toString();
    assertTrue(result.contains("|-INFO in "+this.getClass().getName()));
  }
  
}
