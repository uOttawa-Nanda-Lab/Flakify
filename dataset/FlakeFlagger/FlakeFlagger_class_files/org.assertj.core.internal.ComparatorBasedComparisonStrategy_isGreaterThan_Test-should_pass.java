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
package org.assertj.core.internal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Rectangle;

import org.assertj.core.internal.ComparatorBasedComparisonStrategy;
import org.junit.Test;

/**
 * Tests for {@link ComparatorBasedComparisonStrategy#isGreaterThan(Object, Object)}.
 * 
 * @author Joel Costigliola
 */
public class ComparatorBasedComparisonStrategy_isGreaterThan_Test extends AbstractTest_ComparatorBasedComparisonStrategy {

  @Test
  public void should_pass() {
    String string = "stringA";
    String lesserUpperString = "STRING";
    assertTrue(caseInsensitiveComparisonStrategy.isGreaterThan(string, lesserUpperString));
    assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(lesserUpperString, string));
    assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(string, string));
    assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(string, "STRINGA"));
    String lowerLesserString = "string";
    assertTrue(caseInsensitiveComparisonStrategy.isGreaterThan(string, lowerLesserString));
    assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(lowerLesserString, string));
  }

}
