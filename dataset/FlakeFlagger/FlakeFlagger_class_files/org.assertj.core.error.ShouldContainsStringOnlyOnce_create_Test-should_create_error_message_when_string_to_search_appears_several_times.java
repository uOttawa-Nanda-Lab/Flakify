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

import static junit.framework.Assert.assertEquals;

import static org.assertj.core.error.ShouldContainCharSequenceOnlyOnce.shouldContainOnlyOnce;

import org.assertj.core.description.TextDescription;
import org.assertj.core.internal.*;
import org.assertj.core.presentation.StandardRepresentation;
import org.assertj.core.util.CaseInsensitiveStringComparator;
import org.junit.Before;
import org.junit.Test;


public class ShouldContainsStringOnlyOnce_create_Test {

  private ErrorMessageFactory factoryWithSeveralOccurences;
  private ErrorMessageFactory factoryWithNoOccurence;

  @Before
  public void setUp() {
    factoryWithSeveralOccurences = shouldContainOnlyOnce("aaamotifmotifaabbbmotifaaa", "motif", 3);
    factoryWithNoOccurence = shouldContainOnlyOnce("aaamodifmoifaabbbmotfaaa", "motif", 0);
  }

  @Test public void should_create_error_message_when_string_to_search_appears_several_times(){String message=factoryWithSeveralOccurences.create(new TestDescription("Test"),new StandardRepresentation());assertEquals("[Test] \nExpecting:\n <\"motif\">\nto appear only once in:\n <\"aaamotifmotifaabbbmotifaaa\">\nbut it appeared 3 times ",message);}

}
