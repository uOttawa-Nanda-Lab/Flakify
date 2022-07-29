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
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.transcoding.DataTranscoder;
import info.archinnov.achilles.test.mapping.entity.ClusteredEntity;
import info.archinnov.achilles.type.BoundingMode;
import info.archinnov.achilles.type.OrderingMode;

@RunWith(MockitoJUnitRunner.class)
public class SliceQueryTest {

	@Mock
	private DataTranscoder transcoder;

	@Test public void should_return_true_when_no_component() throws Exception{PropertyMeta idMeta=mock(PropertyMeta.class);when(idMeta.getClusteringOrders()).thenReturn(Arrays.asList(new ClusteringOrder("clust",Sorting.DESC)));EntityMeta meta=new EntityMeta();meta.setIdMeta(idMeta);SliceQuery<ClusteredEntity> sliceQuery=new SliceQuery<ClusteredEntity>(ClusteredEntity.class,meta,Arrays.<Object>asList(11L),Arrays.asList(),Arrays.asList(),OrderingMode.ASCENDING,BoundingMode.INCLUSIVE_BOUNDS,null,100,99,false);assertThat(sliceQuery.hasNoComponent()).isTrue();}
}
