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

import org.assertj.core.internal.StandardComparisonStrategy;
import org.assertj.core.util.Objects;
import org.junit.Test;

/**
 * Tests for {@link StandardComparisonStrategy#areEqual(Object, Object)}.<br>
 * Conceptually the same as {@link Objects#areEqual(Object, Object)} but I don't know how to verify/test that
 * {@link StandardComparisonStrategy#areEqual(Object, Object)} simply calls {@link Objects#areEqual(Object, Object)}
 * 
 * @author Joel Costigliola
 */
public class StandardComparisonStrategy_areEqual_Test {

  private static StandardComparisonStrategy standardComparisonStrategy = StandardComparisonStrategy.instance();

  @Test public void should_return_true_if_both_Objects_are_null_with_verify(){assertTrue(standardComparisonStrategy.areEqual(null,null));}

}
