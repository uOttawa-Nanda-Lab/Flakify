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
  
  @Test public void testCanAggregateComponent(){assertEquals(AggregationType.AS_COMPLEX_PROPERTY,setter.computeAggregationType("door"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("count"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("Count"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("name"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("Name"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("Duration"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("fs"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("open"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("Open"));assertEquals(AggregationType.AS_COMPLEX_PROPERTY_COLLECTION,setter.computeAggregationType("Window"));assertEquals(AggregationType.AS_BASIC_PROPERTY_COLLECTION,setter.computeAggregationType("adjective"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("filterReply"));assertEquals(AggregationType.AS_BASIC_PROPERTY,setter.computeAggregationType("houseColor"));System.out.println();}
}

