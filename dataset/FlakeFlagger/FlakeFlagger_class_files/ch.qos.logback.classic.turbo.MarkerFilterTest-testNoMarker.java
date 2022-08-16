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
package ch.qos.logback.classic.turbo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.core.spi.FilterReply;

public class MarkerFilterTest {

  static String TOTO = "TOTO";
  static String COMPOSITE = "COMPOSITE";

  Marker totoMarker = MarkerFactory.getMarker(TOTO);


  @Test public void testNoMarker(){MarkerFilter mkt=new MarkerFilter();mkt.start();assertFalse(mkt.isStarted());assertEquals(FilterReply.NEUTRAL,mkt.decide(totoMarker,null,null,null,null,null));assertEquals(FilterReply.NEUTRAL,mkt.decide(null,null,null,null,null,null));}

}
