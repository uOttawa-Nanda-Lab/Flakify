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
package ch.qos.logback.core.joran.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.StatusChecker;
import ch.qos.logback.core.util.AggregationType;

public class PropertySetterTest {

  DefaultNestedComponentRegistry defaultComponentRegistry = new DefaultNestedComponentRegistry();

  Context context = new ContextBase();
  House house = new House();
  PropertySetter setter = new PropertySetter(house);
 
  
  @Before
  public void setUp() {
    setter.setContext(context);
  }
  
  @Test public void testComplexCollection(){Window w1=new Window();w1.handle=10;Window w2=new Window();w2.handle=20;setter.addComplexProperty("window",w1);setter.addComplexProperty("window",w2);assertEquals(2,house.windowList.size());assertEquals(10,house.windowList.get(0).handle);assertEquals(20,house.windowList.get(1).handle);}
}

