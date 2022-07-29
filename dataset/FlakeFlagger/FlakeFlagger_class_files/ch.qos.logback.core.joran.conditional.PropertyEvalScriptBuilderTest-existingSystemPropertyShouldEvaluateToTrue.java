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
package ch.qos.logback.core.joran.conditional;

import static org.junit.Assert.*;

import ch.qos.logback.core.joran.spi.InterpretationContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.testUtil.RandomUtil;

public class PropertyEvalScriptBuilderTest {


  Context context = new ContextBase();
  InterpretationContext localPropContainer = new InterpretationContext(context, null);
  PropertyEvalScriptBuilder pesb = new PropertyEvalScriptBuilder(localPropContainer);
  int diff = RandomUtil.getPositiveInt();

  String k = "ka" + diff;
  String v = "va";
  String containsScript = "p(\"" + k + "\").contains(\"" + v + "\")";

  String isNullScriptStr = "isNull(\"" + k + "\")";
  String isDefiedScriptStr = "isDefined(\"" + k + "\")";

  @Before
  public void setUp() {
    context.setName("c" + diff);
    pesb.setContext(context);
  }

  void buildAndAssertTrue(String scriptStr) throws Exception {
    Condition condition = pesb.build(scriptStr);
    assertNotNull(condition);
    assertTrue(condition.evaluate());
  }

  void buildAndAssertFalse(String scriptStr) throws Exception {
    Condition condition = pesb.build(scriptStr);
    assertNotNull(condition);
    assertFalse(condition.evaluate());
  }

  @Test public void existingSystemPropertyShouldEvaluateToTrue() throws Exception{System.setProperty(k,v);buildAndAssertTrue(containsScript);}


}
