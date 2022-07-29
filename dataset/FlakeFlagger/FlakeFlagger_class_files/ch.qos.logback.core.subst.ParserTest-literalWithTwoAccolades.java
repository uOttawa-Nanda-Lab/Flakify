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
package ch.qos.logback.core.subst;

import ch.qos.logback.core.spi.ScanException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ceki
 * Date: 05.08.12
 * Time: 00:15
 * To change this template use File | Settings | File Templates.
 */
public class ParserTest {


  @Test public void literalWithTwoAccolades() throws ScanException{Tokenizer tokenizer=new Tokenizer("%x{y} %a{b} c");Parser parser=new Parser(tokenizer.tokenize());Node node=parser.parse();Node witness=new Node(Node.Type.LITERAL,"%x");Node t=witness.next=new Node(Node.Type.LITERAL,"{");t.next=new Node(Node.Type.LITERAL,"y");t=t.next;t.next=new Node(Node.Type.LITERAL,"}");t=t.next;t.next=new Node(Node.Type.LITERAL," %a");t=t.next;t.next=new Node(Node.Type.LITERAL,"{");t=t.next;t.next=new Node(Node.Type.LITERAL,"b");t=t.next;t.next=new Node(Node.Type.LITERAL,"}");t=t.next;t.next=new Node(Node.Type.LITERAL," c");node.dump();System.out.println("");assertEquals(witness,node);}

  private void dump(Node node) {
    while (node != null) {
      System.out.println(node.toString());
      node = node.next;
    }
  }


}
