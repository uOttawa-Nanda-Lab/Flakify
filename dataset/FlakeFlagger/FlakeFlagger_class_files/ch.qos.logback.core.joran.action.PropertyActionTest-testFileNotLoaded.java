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
package ch.qos.logback.core.joran.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.util.CoreTestConstants;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * Test {@link PropertyAction}.
 * @author Ceki G&uuml;lc&uuml;
 */
public class PropertyActionTest  {

  Context context;
  InterpretationContext ec;
  PropertyAction propertyAction;
  DummyAttributes atts = new DummyAttributes();
  
  @Before
  public void setUp() throws Exception {
    context = new ContextBase();
    ec = new InterpretationContext(context, null);
    propertyAction = new PropertyAction();
    propertyAction.setContext(context);
  }

  @Test public void testFileNotLoaded(){atts.setValue("file","toto");atts.setValue("value","work");propertyAction.begin(ec,null,atts);assertEquals(1,context.getStatusManager().getCount());assertTrue(checkError());}
  
  private boolean checkError() {
    Iterator it = context.getStatusManager().getCopyOfStatusList().iterator();
    ErrorStatus es = (ErrorStatus)it.next();
    return PropertyAction.INVALID_ATTRIBUTES.equals(es.getMessage());
  }
  
  private boolean checkFileErrors() {
    Iterator it = context.getStatusManager().getCopyOfStatusList().iterator();
    ErrorStatus es1 = (ErrorStatus)it.next();
    return "Could not find properties file [toto].".equals(es1.getMessage());
  }
}
