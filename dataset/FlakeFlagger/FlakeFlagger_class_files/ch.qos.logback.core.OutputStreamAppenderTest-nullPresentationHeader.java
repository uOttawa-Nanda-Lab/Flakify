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
package ch.qos.logback.core;


import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.pattern.parser.SamplePatternLayout;

public class OutputStreamAppenderTest {

  Context context = new ContextBase();
  
  @Before
  public void setUp() throws Exception {
  }

  @Test public void nullPresentationHeader(){String FILE_HEADER="FILE_HEADER ";String PRESENTATION_HEADER=null;String PRESENTATION_FOOTER="PRESENTATION_FOOTER ";String FILE_FOOTER="FILE_FOOTER";headerFooterCheck(FILE_HEADER,PRESENTATION_HEADER,PRESENTATION_FOOTER,FILE_FOOTER);}

  String emtptyIfNull(String s) {
    return s == null ? "" : s;
  }
}
