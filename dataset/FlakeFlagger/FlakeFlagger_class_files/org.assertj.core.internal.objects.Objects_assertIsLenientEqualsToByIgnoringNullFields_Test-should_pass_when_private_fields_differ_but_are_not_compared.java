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
package org.assertj.core.internal.objects;

import static org.assertj.core.error.ShouldBeInstance.shouldBeInstance;
import static org.assertj.core.error.ShouldBeEqualToIgnoringFields.shouldBeEqualToIgnoringGivenFields;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.ObjectsBaseTest;
import org.assertj.core.test.Employee;
import org.assertj.core.test.Jedi;
import org.assertj.core.test.TestClassWithRandomId;
import org.assertj.core.util.introspection.FieldSupport;
import org.junit.Test;

/**
 * Tests for <code>{@link Objects#assertIsLenientEqualsToByIgnoringNull(AssertionInfo, Object, Object)</code>.
 *
 * @author Nicolas François
 * @author Joel Costigliola
 */
public class Objects_assertIsLenientEqualsToByIgnoringNullFields_Test extends ObjectsBaseTest {

  @Test public void should_pass_when_private_fields_differ_but_are_not_compared(){boolean allowedToUsePrivateFields=FieldSupport.comparison().isAllowedToUsePrivateFields();Assertions.setAllowComparingPrivateFields(false);TestClassWithRandomId actual=new TestClassWithRandomId("1",1);TestClassWithRandomId other=new TestClassWithRandomId(null,1);objects.assertIsLenientEqualsToIgnoringNullFields(someInfo(),actual,other);Assertions.setAllowComparingPrivateFields(allowedToUsePrivateFields);}

}
