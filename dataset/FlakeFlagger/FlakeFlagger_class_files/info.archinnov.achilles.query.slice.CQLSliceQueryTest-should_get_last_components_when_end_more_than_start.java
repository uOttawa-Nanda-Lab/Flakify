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
package info.archinnov.achilles.query.slice;

import static info.archinnov.achilles.type.BoundingMode.INCLUSIVE_END_BOUND_ONLY;
import static info.archinnov.achilles.type.ConsistencyLevel.*;
import static info.archinnov.achilles.type.OrderingMode.*;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.test.mapping.entity.ClusteredEntity;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import com.datastax.driver.core.querybuilder.Ordering;

@RunWith(MockitoJUnitRunner.class)
public class CQLSliceQueryTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private CQLSliceQuery<ClusteredEntity> cqlSliceQuery;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private SliceQuery<ClusteredEntity> sliceQuery;

	@Mock
	private SliceQueryValidator validator;

	private List<Object> defaultStart = Arrays.<Object> asList(1, 2);
	private List<Object> defaultEnd = Arrays.<Object> asList(1, 2);

	@Before
	public void setUp() {
		when(sliceQuery.getOrdering()).thenReturn(ASCENDING);
		when(sliceQuery.getClusteringsFrom()).thenReturn(defaultStart);
		when(sliceQuery.getClusteringsTo()).thenReturn(defaultEnd);
	}

	@Test public void should_get_last_components_when_end_more_than_start() throws Exception{when(sliceQuery.getClusteringsFrom()).thenReturn(Arrays.<Object>asList(11L,"a"));when(sliceQuery.getClusteringsTo()).thenReturn(Arrays.<Object>asList(11L,"a",12.0));cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);assertThat(cqlSliceQuery.getLastStartComponent()).isNull();assertThat(cqlSliceQuery.getLastEndComponent()).isEqualTo(12.0);}
}
