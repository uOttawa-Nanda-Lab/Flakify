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

import static org.assertj.core.error.ShouldBeEqualByComparingOnlyGivenFields.shouldBeEqualComparingOnlyGivenFields;
import static org.assertj.core.error.ShouldBeInstance.shouldBeInstance;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.ObjectsBaseTest;
import org.assertj.core.test.Employee;
import org.assertj.core.test.Jedi;
import org.assertj.core.test.Name;
import org.assertj.core.test.Player;
import org.assertj.core.util.introspection.FieldSupport;
import org.assertj.core.util.introspection.IntrospectionError;
import org.junit.Test;

public class Objects_assertIsEqualToComparingOnlyGivenFields_Test extends ObjectsBaseTest {

  @Test public void should_pass_when_selected_fields_and_nested_public_fields_are_equal(){Player rose=new Player(new Name("Derrick","Rose"),"Chicago Bulls");rose.nickname=new Name("Crazy","Duncks");Player jalen=new Player(new Name("Derrick","Coleman"),"Chicago Bulls");jalen.nickname=new Name("Crazy","Defense");objects.assertIsEqualToComparingOnlyGivenFields(someInfo(),rose,jalen,"team","nickname.first");}
  
}
