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
import static org.mockito.Mockito.*;

import java.awt.Rectangle;

import org.assertj.core.internal.ComparatorBasedComparisonStrategy;
import org.junit.Test;

/**
 * Tests for {@link ComparatorBasedComparisonStrategy#isGreaterThan(Object, Object)}.
 * 
 * @author Joel Costigliola
 */
public class ComparatorBasedComparisonStrategy_isGreaterThan_Test extends AbstractTest_ComparatorBasedComparisonStrategy {

  @Test public void verify_that_isGreaterThan_delegates_to_compare_method(){caseInsensitiveStringComparator=mock(CaseInsensitiveStringComparator.class);caseInsensitiveComparisonStrategy=new ComparatorBasedComparisonStrategy(caseInsensitiveStringComparator);String s1="string1";String s2="string2";caseInsensitiveComparisonStrategy.isGreaterThan(s1,s2);verify(caseInsensitiveStringComparator).compare(s1,s2);}

}
