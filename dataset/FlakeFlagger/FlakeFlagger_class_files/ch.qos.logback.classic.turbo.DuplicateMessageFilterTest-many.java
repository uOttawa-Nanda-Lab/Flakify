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

import static org.junit.Assert.*;

import org.junit.Test;

import ch.qos.logback.core.spi.FilterReply;

public class DuplicateMessageFilterTest {

  @Test public void many(){DuplicateMessageFilter dmf=new DuplicateMessageFilter();dmf.setAllowedRepetitions(0);int cacheSize=10;int margin=2;dmf.setCacheSize(cacheSize);dmf.start();for (int i=0;i < cacheSize + margin;i++){assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"a" + i,null,null));}for (int i=cacheSize - 1;i >= margin;i--){assertEquals(FilterReply.DENY,dmf.decide(null,null,null,"a" + i,null,null));}for (int i=margin - 1;i >= 0;i--){assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"a" + i,null,null));}}

}
