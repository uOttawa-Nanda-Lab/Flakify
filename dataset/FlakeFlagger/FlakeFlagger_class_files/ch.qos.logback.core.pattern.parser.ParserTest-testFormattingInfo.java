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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.status.StatusChecker;
import org.junit.Test;

import ch.qos.logback.core.pattern.FormatInfo;

public class ParserTest {

  String BARE = Token.BARE_COMPOSITE_KEYWORD_TOKEN.getValue().toString();
  Context context = new ContextBase();


  @Test public void testFormattingInfo() throws Exception{{Parser p=new Parser("%45x");Node t=p.parse();FormattingNode witness=new SimpleKeywordNode("x");witness.setFormatInfo(new FormatInfo(45,Integer.MAX_VALUE));assertEquals(witness,t);}{Parser p=new Parser("%4.5x");Node t=p.parse();FormattingNode witness=new SimpleKeywordNode("x");witness.setFormatInfo(new FormatInfo(4,5));assertEquals(witness,t);}{Parser p=new Parser("%-4.5x");Node t=p.parse();FormattingNode witness=new SimpleKeywordNode("x");witness.setFormatInfo(new FormatInfo(4,5,false,true));assertEquals(witness,t);}{Parser p=new Parser("%-4.-5x");Node t=p.parse();FormattingNode witness=new SimpleKeywordNode("x");witness.setFormatInfo(new FormatInfo(4,5,false,false));assertEquals(witness,t);}{Parser p=new Parser("%-4.5x %12y");Node t=p.parse();FormattingNode witness=new SimpleKeywordNode("x");witness.setFormatInfo(new FormatInfo(4,5,false,true));Node n=witness.next=new Node(Node.LITERAL," ");n=n.next=new SimpleKeywordNode("y");((FormattingNode)n).setFormatInfo(new FormatInfo(12,Integer.MAX_VALUE));assertEquals(witness,t);}}

}