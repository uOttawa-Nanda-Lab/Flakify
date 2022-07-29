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

  @Test public void test6(){ElementSelector p=new ElementSelector("//a//b");assertEquals(2,p.size());assertEquals("a",p.get(0));assertEquals("b",p.get(1));}

}
