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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TokenizerTest {

  List<Token> witnessList = new ArrayList<Token>();

  @Test public void basicDefaultSeparator() throws ScanException{String input="${a:-b}";Tokenizer tokenizer=new Tokenizer(input);List<Token> tokenList=tokenizer.tokenize();witnessList.add(Token.START_TOKEN);witnessList.add(new Token(Token.Type.LITERAL,"a"));witnessList.add(Token.DEFAULT_SEP_TOKEN);witnessList.add(new Token(Token.Type.LITERAL,"b"));witnessList.add(Token.CURLY_RIGHT_TOKEN);assertEquals(witnessList,tokenList);}


  }
