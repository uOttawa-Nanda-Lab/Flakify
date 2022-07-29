/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.util;

import static org.assertj.core.util.Strings.quote;
import static org.junit.Assert.*;

import org.assertj.core.presentation.HexadecimalRepresentation;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link ArrayFormatter#format(org.assertj.core.presentation.Representation, Object)}.
 * 
 * @author Alex Ruiz
 */
public class ArrayFormatter_format_Test {
  private static ArrayFormatter formatter;

  @BeforeClass
  public static void setUpOnce() {
    formatter = new ArrayFormatter();
  }

  @Test public void should_format_longArray(){assertEquals("[6L, 8L]",formatter.format(new StandardRepresentation(),new long[]{6l,8l}));}

  private static class Person {
    private final String name;

    Person(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return quote(name);
    }
  }
}
