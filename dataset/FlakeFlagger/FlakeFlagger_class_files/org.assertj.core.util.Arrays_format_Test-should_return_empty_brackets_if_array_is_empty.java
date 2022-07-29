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
package org.assertj.core.util;

import static org.junit.Assert.*;

import org.assertj.core.presentation.HexadecimalRepresentation;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.Test;

/**
 * Tests for <code>{@link Arrays#format(org.assertj.core.presentation.Representation, Object)}</code>.
 * 
 * @author Alex Ruiz
 */
public class Arrays_format_Test {

  @Test public void should_return_empty_brackets_if_array_is_empty(){final Object[] array=new Object[0];assertEquals("[]",Arrays.format(new StandardRepresentation(),array));assertEquals("[]",Arrays.format(array));}
}
