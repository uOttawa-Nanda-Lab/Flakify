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
package ch.qos.logback.core.rolling.helper;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;

/**
 * @author Ceki
 * 
 */
public class FileNamePatternTest {

  Context context = new ContextBase();

  @Test public void convertMultipleDates(){Calendar cal=Calendar.getInstance();cal.set(2003,4,20,17,55);FileNamePattern fnp=new FileNamePattern("foo-%d{yyyy.MM, aux}/%d{yyyy.MM.dd}.txt",context);assertEquals("foo-2003.05/2003.05.20.txt",fnp.convert(cal.getTime()));}
}
