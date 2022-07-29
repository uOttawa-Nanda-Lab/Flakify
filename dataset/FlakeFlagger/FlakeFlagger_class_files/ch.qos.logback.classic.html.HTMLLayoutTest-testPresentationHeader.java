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

  @SuppressWarnings("unchecked") @Test public void testPresentationHeader() throws Exception{String header=layout.getFileHeader();String presentationHeader=layout.getPresentationHeader();header=header + presentationHeader;Document doc=parseOutput(header + "</table></body></html>");Element rootElement=doc.getRootElement();Element bodyElement=rootElement.element("body");Element tableElement=bodyElement.element("table");Element trElement=tableElement.element("tr");List<Element> elementList=trElement.elements();assertEquals("Level",elementList.get(0).getText());assertEquals("Thread",elementList.get(1).getText());assertEquals("Message",elementList.get(2).getText());}

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
