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
package org.assertj.core.internal.bytearrays;

import static org.assertj.core.error.ShouldNotContain.shouldNotContain;
import static org.assertj.core.test.ByteArrays.*;
import static org.assertj.core.test.ErrorMessages.*;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Sets.newLinkedHashSet;


import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.ByteArrays;
import org.assertj.core.internal.ByteArraysBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link ByteArrays#assertDoesNotContain(AssertionInfo, byte[], byte[])}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class ByteArrays_assertDoesNotContain_Test extends ByteArraysBaseTest {

  @Test public void should_fail_if_actual_contains_given_values_according_to_custom_comparison_strategy(){AssertionInfo info=someInfo();byte[] expected={6,-8,20};try {arraysWithCustomComparisonStrategy.assertDoesNotContain(info,actual,expected);} catch (AssertionError e){verify(failures).failure(info,shouldNotContain(actual,expected,newLinkedHashSet((byte)6,(byte)-8),absValueComparisonStrategy));return;}failBecauseExpectedAssertionErrorWasNotThrown();}
}
