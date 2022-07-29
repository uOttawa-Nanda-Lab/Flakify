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
package org.assertj.core.data;

import static junit.framework.Assert.assertFalse;
import static org.assertj.core.data.MapEntry.entry;
import static org.assertj.core.test.EqualsHashCodeContractAssert.*;


import org.assertj.core.data.MapEntry;
import org.junit.*;

/**
 * Tests for {@link MapEntry#equals(Object)} and {@link MapEntry#hashCode()}.
 *
 * @author Alex Ruiz
 */
public class MapEntry_equals_hashCode_Test {
  private static MapEntry<String, String> entry;

  @BeforeClass
  public static void setUpOnce() {
    entry = entry("key", "value");
  }

  @Test
  public void should_maintain_equals_and_hashCode_contract() {
    assertMaintainsEqualsAndHashCodeContract(entry, entry("key", "value"));
  }
}
