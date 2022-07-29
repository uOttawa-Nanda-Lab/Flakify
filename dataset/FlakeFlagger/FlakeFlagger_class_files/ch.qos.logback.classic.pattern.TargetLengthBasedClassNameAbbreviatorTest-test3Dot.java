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
package ch.qos.logback.classic.pattern;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator;

public class TargetLengthBasedClassNameAbbreviatorTest  {


  @Test public void test3Dot(){{TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);String name="com.logback.xyz.Foobar";assertEquals("c.l.x.Foobar",abbreviator.abbreviate(name));}{TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(13);String name="com.logback.xyz.Foobar";assertEquals("c.l.x.Foobar",abbreviator.abbreviate(name));}{TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(14);String name="com.logback.xyz.Foobar";assertEquals("c.l.xyz.Foobar",abbreviator.abbreviate(name));}{TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(15);String name="com.logback.alligator.Foobar";assertEquals("c.l.a.Foobar",abbreviator.abbreviate(name));}}
}
