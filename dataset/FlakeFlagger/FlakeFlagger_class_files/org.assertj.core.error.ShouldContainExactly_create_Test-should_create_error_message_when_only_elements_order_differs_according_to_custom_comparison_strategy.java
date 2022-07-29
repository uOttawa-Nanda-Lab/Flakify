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
package org.assertj.core.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.ShouldContainExactly.elementsDifferAtIndex;
import static org.assertj.core.error.ShouldContainExactly.shouldContainExactly;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.core.util.Sets.newLinkedHashSet;

import java.util.Collections;

import org.assertj.core.description.TextDescription;
import org.assertj.core.internal.ComparatorBasedComparisonStrategy;
import org.assertj.core.presentation.StandardRepresentation;
import org.assertj.core.util.CaseInsensitiveStringComparator;
import org.junit.Test;

/**
 * Tests for
 * <code>{@link ShouldContainExactly#create(org.assertj.core.description.Description, org.assertj.core.presentation.Representation)}</code>
 * .
 * 
 * @author Joel Costigliola
 */
public class ShouldContainExactly_create_Test {

  private static final ComparatorBasedComparisonStrategy CASE_INSENSITIVE_COMPARISON_STRATEGY =
      new ComparatorBasedComparisonStrategy(CaseInsensitiveStringComparator.instance);

  @Test public void should_create_error_message_when_only_elements_order_differs_according_to_custom_comparison_strategy(){ErrorMessageFactory factory=elementsDifferAtIndex("Luke","Han",1,CASE_INSENSITIVE_COMPARISON_STRATEGY);String message=factory.create(new TextDescription("Test"),new StandardRepresentation());assertThat(message).isEqualTo("[Test] \n" + "Actual and expected have the same elements but not in the same order, at index 1 actual element was:\n" + "  <\"Luke\">\nwhereas expected element was:\n" + "  <\"Han\">\n" + "when comparing values using 'CaseInsensitiveStringComparator'");}
}
