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
package ch.qos.logback.core.pattern.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.status.StatusChecker;
import org.junit.Test;

import ch.qos.logback.core.pattern.FormatInfo;

public class ParserTest {

  String BARE = Token.BARE_COMPOSITE_KEYWORD_TOKEN.getValue().toString();
  Context context = new ContextBase();


  @Test
  public void lbcore193() throws Exception {
    try {
      Parser p = new Parser("hello%(abc");
      p.setContext(context);
      Node t = p.parse();
      fail("where the is exception?");
    } catch (ScanException ise) {
      assertEquals("Expecting RIGHT_PARENTHESIS token but got null", ise.getMessage());
    }
    StatusChecker sc = new StatusChecker(context);
    sc.assertContainsMatch("Expecting RIGHT_PARENTHESIS");
    sc.assertContainsMatch("See also " + Parser.MISSING_RIGHT_PARENTHESIS);
  }

}