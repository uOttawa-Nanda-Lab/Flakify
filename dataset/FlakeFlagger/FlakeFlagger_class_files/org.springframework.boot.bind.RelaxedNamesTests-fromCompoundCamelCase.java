/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.bind;

import java.util.Iterator;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link RelaxedNames}.
 *
 * @author Phillip Webb
 * @author Dave Syer
 */
public class RelaxedNamesTests {

	@Test public void fromCompoundCamelCase() throws Exception{Iterator<String> iterator=new RelaxedNames("caMelCase").iterator();assertThat(iterator.next(),equalTo("caMelCase"));assertThat(iterator.next(),equalTo("ca_mel_case"));assertThat(iterator.next(),equalTo("ca-mel-case"));assertThat(iterator.next(),equalTo("camelcase"));assertThat(iterator.next(),equalTo("CAMELCASE"));assertThat(iterator.next(),equalTo("CA_MEL_CASE"));assertThat(iterator.next(),equalTo("CA-MEL-CASE"));assertThat(iterator.hasNext(),equalTo(false));}

}
