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
package ch.qos.logback.classic.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoggerNameUtilTest {


  @Test public void supportNestedClasses(){List<String> witnessList=new ArrayList<String>();witnessList.add("com");witnessList.add("foo");witnessList.add("Bar");witnessList.add("Nested");List<String> partList=LoggerNameUtil.computeNameParts("com.foo.Bar$Nested");assertEquals(witnessList,partList);}


}
