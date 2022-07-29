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


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.helpers.NOPAppender;

/**
 * This test case verifies all the methods of AppenderAttableImpl work properly.
 *
 * @author Ralph Goers
 */
public class AppenderAttachableImplTest {

  
  private AppenderAttachableImpl<TestEvent> aai;

  @Before
  public void setUp() throws Exception {
    aai = new AppenderAttachableImpl<TestEvent>();
  }

  @Test public void testIsAttached() throws Exception{NOPAppender<TestEvent> ta=new NOPAppender<TestEvent>();ta.start();aai.addAppender(ta);NOPAppender<TestEvent> tab=new NOPAppender<TestEvent>();tab.setName("test");tab.start();aai.addAppender(tab);assertTrue("Appender is not attached",aai.isAttached(ta));assertTrue("Appender is not attached",aai.isAttached(tab));}

  private static class TestEvent {

  }

}

