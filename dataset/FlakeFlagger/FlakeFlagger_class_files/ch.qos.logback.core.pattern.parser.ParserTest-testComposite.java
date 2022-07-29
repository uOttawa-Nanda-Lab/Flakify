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


  @Test
  public void testComposite() throws Exception {
    {
      Parser p = new Parser("hello%(%child)");
      Node t = p.parse();

      Node witness = new Node(Node.LITERAL, "hello");
      CompositeNode composite = new CompositeNode(BARE);
      Node child = new SimpleKeywordNode("child");
      composite.setChildNode(child);
      witness.next = composite;

      // System.out.println("w:" + witness);
      // System.out.println(t);

      assertEquals(witness, t);
    }

    // System.out.println("testRecursive part 2");
    {
      Parser p = new Parser("hello%(%child )");
      Node t = p.parse();

      Node witness = new Node(Node.LITERAL, "hello");
      CompositeNode composite = new CompositeNode(BARE);
      Node child = new SimpleKeywordNode("child");
      composite.setChildNode(child);
      witness.next = composite;
      child.next = new Node(Node.LITERAL, " ");
      assertEquals(witness, t);
    }

    {
      Parser p = new Parser("hello%(%child %h)");
      Node t = p.parse();
      Node witness = new Node(Node.LITERAL, "hello");
      CompositeNode composite = new CompositeNode(BARE);
      Node child = new SimpleKeywordNode("child");
      composite.setChildNode(child);
      child.next = new Node(Node.LITERAL, " ");
      child.next.next = new SimpleKeywordNode("h");
      witness.next = composite;
      assertEquals(witness, t);
    }

    {
      Parser p = new Parser("hello%(%child %h) %m");
      Node t = p.parse();
      Node witness = new Node(Node.LITERAL, "hello");
      CompositeNode composite = new CompositeNode(BARE);
      Node child = new SimpleKeywordNode("child");
      composite.setChildNode(child);
      child.next = new Node(Node.LITERAL, " ");
      child.next.next = new SimpleKeywordNode("h");
      witness.next = composite;
      composite.next = new Node(Node.LITERAL, " ");
      composite.next.next = new SimpleKeywordNode("m");
      assertEquals(witness, t);
    }

    {
      Parser p = new Parser("hello%( %child \\(%h\\) ) %m");
      Node t = p.parse();
      Node witness = new Node(Node.LITERAL, "hello");
      CompositeNode composite = new CompositeNode(BARE);
      Node child = new Node(Node.LITERAL, " ");
      composite.setChildNode(child);
      Node c = child;
      c = c.next = new SimpleKeywordNode("child");
      c = c.next = new Node(Node.LITERAL, " (");
      c = c.next = new SimpleKeywordNode("h");
      c = c.next = new Node(Node.LITERAL, ") ");
      witness.next = composite;
      composite.next = new Node(Node.LITERAL, " ");
      composite.next.next = new SimpleKeywordNode("m");
      assertEquals(witness, t);
    }


  }

}