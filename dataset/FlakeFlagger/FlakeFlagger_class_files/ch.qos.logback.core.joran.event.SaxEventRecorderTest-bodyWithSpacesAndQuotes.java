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
package ch.qos.logback.core.joran.event;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ch.qos.logback.core.status.StatusChecker;
import org.junit.Test;
import org.xml.sax.Attributes;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.CoreTestConstants;

/**
 * Test whether SaxEventRecorder does a good job.
 * 
 * @author Ceki Gulcu
 */
public class SaxEventRecorderTest {

  Context context =  new ContextBase();
  StatusChecker statusChecker = new StatusChecker(context);

  SAXParser createParser() throws Exception {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    return spf.newSAXParser();
  }

  @Test public void bodyWithSpacesAndQuotes() throws Exception{List<SaxEvent> seList=doTest("spacesAndQuotes.xml");assertEquals(3,seList.size());BodyEvent be=(BodyEvent)seList.get(1);assertEquals("[x][x] \"xyz\"%n",be.getText());}
  
  

}
