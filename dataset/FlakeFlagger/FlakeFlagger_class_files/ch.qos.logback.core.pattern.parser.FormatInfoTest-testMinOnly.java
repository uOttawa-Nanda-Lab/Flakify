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
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.qos.logback.core.pattern.FormatInfo;


public class FormatInfoTest  {

  @Test public void testMinOnly(){{FormatInfo fi=FormatInfo.valueOf("49");FormatInfo witness=new FormatInfo();witness.setMin(49);assertEquals(witness,fi);}{FormatInfo fi=FormatInfo.valueOf("-587");FormatInfo witness=new FormatInfo();witness.setMin(587);witness.setLeftPad(false);assertEquals(witness,fi);}}
}