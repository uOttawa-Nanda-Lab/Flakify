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
package ch.qos.logback.classic.html;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.EntityResolver;

import ch.qos.logback.classic.ClassicTestConstants;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.DummyThrowableProxy;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.testUtil.StringListAppender;
import ch.qos.logback.core.util.StatusPrinter;

public class HTMLLayoutTest {

  LoggerContext lc;
  Logger root;
  HTMLLayout layout;

  @Before
  public void setUp() throws Exception {
    lc = new LoggerContext();
    lc.setName("default");

    layout = new HTMLLayout();
    layout.setThrowableRenderer(new DefaultThrowableRenderer());
    layout.setContext(lc);
    layout.setPattern("%level%thread%msg");
    layout.start();

    root = lc.getLogger(Logger.ROOT_LOGGER_NAME);

  }

  @Test
  public void testAppendThrowable() throws Exception {
    StringBuilder buf = new StringBuilder();
    DummyThrowableProxy tp = new DummyThrowableProxy();
    tp.setClassName("test1");
    tp.setMessage("msg1");
    
    StackTraceElement ste1 = new StackTraceElement("c1", "m1", "f1", 1);
    StackTraceElement ste2 = new StackTraceElement("c2", "m2", "f2", 2);
    
    StackTraceElementProxy[] stepArray = { new StackTraceElementProxy(ste1),
        new StackTraceElementProxy(ste2) };
    tp.setStackTraceElementProxyArray(stepArray);
    DefaultThrowableRenderer renderer = (DefaultThrowableRenderer) layout
        .getThrowableRenderer();

    renderer.render(buf, tp);
    System.out.println(buf.toString());
    String[] result = buf.toString().split(CoreConstants.LINE_SEPARATOR);
    System.out.println(result[0]);
    assertEquals("test1: msg1", result[0]);
    assertEquals(DefaultThrowableRenderer.TRACE_PREFIX + "at c1.m1(f1:1)", result[1]);
  }

  private LoggingEvent createLoggingEvent() {
    return new LoggingEvent(this.getClass().getName(), root,
        Level.DEBUG, "test message", null, null);
  }

  Document parseOutput(String output) throws Exception {
    EntityResolver resolver = new XHTMLEntityResolver();
    SAXReader reader = new SAXReader();
    reader.setValidation(true);
    reader.setEntityResolver(resolver);
    return reader.read(new ByteArrayInputStream(output.getBytes()));
  }

  void configure(String file) throws JoranException {
    JoranConfigurator jc = new JoranConfigurator();
    jc.setContext(lc);
    jc.doConfigure(file);
  }
}
