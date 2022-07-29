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
package ch.qos.logback.classic.jmx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.management.ManagementFactory;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.testUtil.RandomUtil;

public class JMXConfiguratorTest {

  MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
  LoggerContext lc = new LoggerContext();
  Logger testLogger  = lc.getLogger(this.getClass());

  List<LoggerContextListener> listenerList;
  int diff = RandomUtil.getPositiveInt();

  
  @Before
  public void setUp() throws Exception {
    lc.setName("context-" + diff);
    assertNotNull(mbs);
  }

  @Test public void getLoggerLevel_LBCLASSIC_78(){String objectNameAsStr="ch.qos" + diff + ":Name=" + lc.getName() + ",Type=" + this.getClass().getName();ObjectName on=MBeanUtil.string2ObjectName(lc,this,objectNameAsStr);JMXConfigurator configurator=new JMXConfigurator(lc,mbs,on);assertEquals("",configurator.getLoggerLevel(testLogger.getName()));MBeanUtil.unregister(lc,mbs,on,this);}

}
