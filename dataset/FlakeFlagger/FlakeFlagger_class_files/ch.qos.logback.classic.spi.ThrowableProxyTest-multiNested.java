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
package ch.qos.logback.classic.spi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThrowableProxyTest {

  StringWriter sw = new StringWriter();
  PrintWriter pw = new PrintWriter(sw);

  private static final Method ADD_SUPPRESSED_METHOD;

  static {
    Method method = null;
    try {
      method = Throwable.class.getMethod("addSuppressed", Throwable.class);
    } catch (NoSuchMethodException e) {
      // ignore, will get thrown in Java < 7
    }
    ADD_SUPPRESSED_METHOD = method;
  }

  private static void addSuppressed(Throwable outer, Throwable suppressed) throws InvocationTargetException, IllegalAccessException
  {
    if(ADD_SUPPRESSED_METHOD != null) {
      ADD_SUPPRESSED_METHOD.invoke(outer, suppressed);
    }
  }

  @Before
  public void setUp() throws Exception {
  }

  @Test public void multiNested(){Exception w=null;try {someOtherMethod();} catch (Exception e){w=new Exception("wrapping",e);}verify(w);}

  void someMethod() throws Exception {
    throw new Exception("someMethod");
  }

  void someMethodWithNullException() throws Exception {
    throw new Exception("someMethodWithNullException") {
      @Override
      public StackTraceElement[] getStackTrace() {
        return null;
      }
    };
  }

  void someOtherMethod() throws Exception {
    try {
      someMethod();
    } catch (Exception e) {
      throw new Exception("someOtherMethod", e);
    }
  }
}
