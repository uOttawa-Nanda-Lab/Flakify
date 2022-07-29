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

  public void testAddStatus() {
	{
		InfoStatus status = new InfoStatus("testing", this);
		status.add(new ErrorStatus("error", this));
		Iterator it = status.iterator();
		assertTrue("No status was added", it.hasNext());
		assertTrue("hasChilden method reported wrong result", status.hasChildren());
	}
	{
		InfoStatus status = new InfoStatus("testing", this);
		try {
			status.add(null);
			fail("method should have thrown an Exception");
		} catch (NullPointerException ex) {
		}
	}
}

}
