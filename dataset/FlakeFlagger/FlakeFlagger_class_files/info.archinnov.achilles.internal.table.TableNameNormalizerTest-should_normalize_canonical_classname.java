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
package info.archinnov.achilles.internal.table;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.exception.AchillesInvalidTableException;

@RunWith(MockitoJUnitRunner.class)
public class TableNameNormalizerTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test public void should_normalize_canonical_classname() throws Exception{String canonicalName="org.achilles.entity.ClassName";String normalized=TableNameNormalizer.normalizerAndValidateColumnFamilyName(canonicalName);assertThat(normalized).isEqualTo("ClassName");}
}
