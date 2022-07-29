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

  @Test public void test4(){ElementSelector p=new ElementSelector("/a123/b1234/cvvsdf");assertEquals(3,p.size());assertEquals("a123",p.get(0));assertEquals("b1234",p.get(1));assertEquals("cvvsdf",p.get(2));}

}
