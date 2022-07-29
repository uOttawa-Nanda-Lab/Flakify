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
package org.assertj.core.extractor;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.test.ExpectedException.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.iterable.Extractor;
import org.assertj.core.groups.Tuple;
import org.assertj.core.test.Employee;
import org.assertj.core.test.ExpectedException;
import org.assertj.core.test.Name;
import org.assertj.core.util.introspection.IntrospectionError;
import org.junit.Rule;
import org.junit.Test;

public class ByNameMultipleExtractorTest {

  @Rule
  public ExpectedException thrown = none();

  private Employee yoda = new Employee(1L, new Name("Yoda"), 800);

  @Test public void should_throw_exception_when_given_name_is_null(){thrown.expectIllegalArgumentException("The names of the fields/properties to read should not be null");new ByNameMultipleExtractor<Employee>((String[])null).extract(yoda);}

}
