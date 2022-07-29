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

import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder;
import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder.Sorting;
import static info.archinnov.achilles.type.BoundingMode.EXCLUSIVE_BOUNDS;
import static info.archinnov.achilles.type.ConsistencyLevel.EACH_QUORUM;
import static info.archinnov.achilles.type.OrderingMode.ASCENDING;
import static info.archinnov.achilles.type.OrderingMode.DESCENDING;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.persistence.operations.SliceQueryExecutor;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.test.mapping.entity.ClusteredEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

@RunWith(MockitoJUnitRunner.class)
public class RootSliceQueryBuilderTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private RootSliceQueryBuilder<ClusteredEntity> builder;

	private Class<ClusteredEntity> entityClass = ClusteredEntity.class;

	@Mock
	private SliceQueryExecutor sliceQueryExecutor;

	@Mock
	private ConfigurationContext configContext;

	@Mock
	private EntityMeta meta;

	@Mock
	private PropertyMeta idMeta;

	@Mock
	private List<ClusteredEntity> result;

	@Mock
	private Iterator<ClusteredEntity> iterator;

	@Before
	public void setUp() {
		Whitebox.setInternalState(builder, "sliceQueryExecutor", sliceQueryExecutor);
		Whitebox.setInternalState(builder, "entityClass", (Object) entityClass);
		Whitebox.setInternalState(builder, "meta", meta);
		Whitebox.setInternalState(builder, "idMeta", idMeta);
		Whitebox.setInternalState(builder, "partitionComponents", new ArrayList<>());
		Whitebox.setInternalState(builder, "fromClusterings", new ArrayList<>());
		Whitebox.setInternalState(builder, "toClusterings", new ArrayList<>());

		when(meta.getIdMeta()).thenReturn(idMeta);
		when(meta.getClassName()).thenReturn("entityClass");
        when(idMeta.getClusteringOrders()).thenReturn(Arrays.asList(new ClusteringOrder("clust", Sorting.DESC)));
		doCallRealMethod().when(builder).partitionComponentsInternal(any());
	}

	@Test public void should_get_last_n() throws Exception{Long partitionKey=RandomUtils.nextLong();when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(result);builder.partitionComponentsInternal(partitionKey).getLast(6);assertThat(Whitebox.getInternalState(builder,"ordering")).isEqualTo(DESCENDING);assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(6);}

	private SliceQuery<ClusteredEntity> anySliceQuery() {
		return Mockito.<SliceQuery<ClusteredEntity>> any();
	}
}
