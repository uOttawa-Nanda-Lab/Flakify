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
package ch.qos.logback.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;


public class OptionHelperTest  {

  String text = "Testing ${v1} variable substitution ${v2}";
  String expected = "Testing if variable substitution works";
  Context context = new ContextBase();
  Map<String, String> secondaryMap;
  
  
  
  @Before
  public void setUp() throws Exception {
    secondaryMap = new HashMap<String, String>();
  }

  @Test public void testSubstVarsWithDefault(){context.putProperty("v1","if");String textWithDefault="Testing ${v1} variable substitution ${v2:-toto}";String resultWithDefault="Testing if variable substitution toto";String result=OptionHelper.substVars(textWithDefault,context);assertEquals(resultWithDefault,result);}

  
}
