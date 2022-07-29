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
package ch.qos.logback.core.joran.spi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test pattern manipulation code.
 * 
 * @author Ceki Gulcu
 */
public class ElementSelectorTest {

  @Test public void testPrefixMatch(){{ElementPath p=new ElementPath("/a/b");ElementSelector ruleElementSelector=new ElementSelector("/x/*");assertEquals(0,ruleElementSelector.getPrefixMatchLength(p));}{ElementPath p=new ElementPath("/a");ElementSelector ruleElementSelector=new ElementSelector("/x/*");assertEquals(0,ruleElementSelector.getPrefixMatchLength(p));}{ElementPath p=new ElementPath("/a/b");ElementSelector ruleElementSelector=new ElementSelector("/a/*");assertEquals(1,ruleElementSelector.getPrefixMatchLength(p));}{ElementPath p=new ElementPath("/a/b");ElementSelector ruleElementSelector=new ElementSelector("/A/*");assertEquals(1,ruleElementSelector.getPrefixMatchLength(p));}{ElementPath p=new ElementPath("/A/b");ElementSelector ruleElementSelector=new ElementSelector("/a/*");assertEquals(1,ruleElementSelector.getPrefixMatchLength(p));}{ElementPath p=new ElementPath("/a/b");ElementSelector ruleElementSelector=new ElementSelector("/a/b/*");assertEquals(2,ruleElementSelector.getPrefixMatchLength(p));}{ElementPath p=new ElementPath("/a/b");ElementSelector ruleElementSelector=new ElementSelector("/*");assertEquals(0,ruleElementSelector.getPrefixMatchLength(p));}}

}
