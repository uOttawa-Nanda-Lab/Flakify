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
package org.assertj.core.api;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

/**
 * Tests for <code>{@link org.assertj.core.api.BDDAssertions#then(String)}</code>.
 *
 * @author Mariusz Smykula
 */
public class BDDAssertions_then_Test {

  @Test public void then_long_array(){then(new long[]{1L,2L}).contains(2L);}
}
