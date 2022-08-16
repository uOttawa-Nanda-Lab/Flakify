/**
 * Copyright (c) 2012 Edgar Espina
 *
 * This file is part of Handlebars.java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.handlebars;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

/**
 * Demostrate error reporting. It isn't a test.
 *
 * @author edgar.espina
 * @since 0.2.1
 */
public class ParsingErrorTest extends AbstractTest {

  Hash source =
      $("inbox/inbox", "{{value",
          "block", "{{#block}}{{/nan}}",
          "iblock", "{{#block}}invalid block",
          "delim", "{{=<% %>=}} <%Hello",
          "default", "{{> missingPartial}}",
          "partial", "{{#value}}",
          "invalidChar", "\n{{tag message.from \\\"user\\\"}}\n",
          "root", "{{> p1}}",
          "p1", "{{value",
          "deep", "{{> deep1}}",
          "deep1", " {{> deep2",
          "unbalancedDelim", "{{=<%%>=}}",
          "partialName", "{{> /user}}",
          "partialName2", "{{> /layout/base}}",
          "paramOrder", "{{f param hashx=1 param}}",
          "idx1", "{{list[0]}}",
          "idx2", "{{list.[0}}",
          "idx3", "{{list.[]}}",
          "idx4", "{{list.[}}",
          "home", "{{>stackoverflow}}",
          "stackoverflow", "{{>home}}");

  @Test(expected=HandlebarsException.class) public void idx2() throws IOException{parse("idx2");}

  private void parse(final String candidate) throws IOException {
    try {
      String input = (String) source.get(candidate);
      Template compiled = compile(input == null ? candidate : input, $(), source);
      System.out.println(compiled);
      fail("An error is expected");
    } catch (HandlebarsException ex) {
      Handlebars.log(ex.getMessage());
      throw ex;
    }
  }
}
