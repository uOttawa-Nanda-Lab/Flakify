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

  @SuppressWarnings("unchecked") @Test public void layoutWithException() throws Exception{layout.setPattern("%level %thread %msg %ex");LoggingEvent le=createLoggingEvent();le.setThrowableProxy(new ThrowableProxy(new Exception("test Exception")));String result=layout.doLayout(le);String stringToParse=layout.getFileHeader();stringToParse=stringToParse + layout.getPresentationHeader();stringToParse+=result;stringToParse+="</table></body></html>";Document doc=parseOutput(stringToParse);Element rootElement=doc.getRootElement();Element bodyElement=rootElement.element("body");Element tableElement=bodyElement.element("table");List<Element> trElementList=tableElement.elements();Element exceptionRowElement=trElementList.get(2);Element exceptionElement=exceptionRowElement.element("td");assertEquals(3,tableElement.elements().size());assertTrue(exceptionElement.getText().contains("java.lang.Exception: test Exception"));}

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
