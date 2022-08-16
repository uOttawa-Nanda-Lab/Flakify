/*
 * Copyright (C) 2012-2014 DuyHai DOAN
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package info.archinnov.achilles.internal.persistence.operations;

import static info.archinnov.achilles.internal.persistence.operations.InternalCounterBuilder.*;
import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class InternalCounterBuilderTest {

	@Test public void should_decrement() throws Exception{InternalCounterImpl counter=(InternalCounterImpl)decr();assertThat(counter.get()).isEqualTo(-1L);assertThat(counter.getInternalCounterDelta()).isEqualTo(-1L);}
}
