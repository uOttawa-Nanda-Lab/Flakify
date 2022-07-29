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

import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.spi.ScanException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Ceki G&uuml;c&uuml;
 */
public class NodeToStringTransformerTest {

  ContextBase propertyContainer0 = new ContextBase();


  @Before
  public void setUp() {
    propertyContainer0.putProperty("k0", "v0");
    propertyContainer0.putProperty("zero", "0");
    propertyContainer0.putProperty("v0.jdbc.url", "http://..");
    propertyContainer0.putProperty("host", "local");

  }

  private Node makeNode(String input) throws ScanException {
    Tokenizer tokenizer = new Tokenizer(input);
    Parser parser = new Parser(tokenizer.tokenize());
    return parser.parse();
  }

  @Test public void literal() throws ScanException{String input="abv";Node node=makeNode(input);NodeToStringTransformer nodeToStringTransformer=new NodeToStringTransformer(node,propertyContainer0);assertEquals(input,nodeToStringTransformer.transform());}


  void checkInputEqualsOutput(String input) throws ScanException {
    Node node = makeNode(input);
    NodeToStringTransformer nodeToStringTransformer = new NodeToStringTransformer(node, propertyContainer0);
    assertEquals(input, nodeToStringTransformer.transform());
  }


}
