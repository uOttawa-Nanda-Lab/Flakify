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
package ch.qos.logback.core.status;

import java.util.Iterator;

import junit.framework.TestCase;

public class StatusBaseTest extends TestCase {

  public void testEffectiveLevel() {
	{
		ErrorStatus status = new ErrorStatus("error", this);
		WarnStatus warn = new WarnStatus("warning", this);
		status.add(warn);
		assertEquals("effective level misevaluated", status.getEffectiveLevel(), Status.ERROR);
	}
	{
		InfoStatus status = new InfoStatus("info", this);
		WarnStatus warn = new WarnStatus("warning", this);
		status.add(warn);
		assertEquals("effective level misevaluated", status.getEffectiveLevel(), Status.WARN);
	}
	{
		InfoStatus status = new InfoStatus("info", this);
		WarnStatus warn = new WarnStatus("warning", this);
		ErrorStatus error = new ErrorStatus("error", this);
		status.add(warn);
		warn.add(error);
		assertEquals("effective level misevaluated", status.getEffectiveLevel(), Status.ERROR);
	}
}

}
