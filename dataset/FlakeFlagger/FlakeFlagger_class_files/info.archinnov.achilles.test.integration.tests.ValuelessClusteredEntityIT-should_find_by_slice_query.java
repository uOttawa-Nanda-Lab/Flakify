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
package info.archinnov.achilles.test.integration.tests;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.junit.AchillesTestResource.Steps;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.ValuelessClusteredEntity;
import info.archinnov.achilles.test.integration.entity.ValuelessClusteredEntity.CompoundKey;
import info.archinnov.achilles.type.BoundingMode;
import info.archinnov.achilles.type.OptionsBuilder;
import info.archinnov.achilles.type.OrderingMode;

public class ValuelessClusteredEntityIT {

	@Rule
	public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(Steps.AFTER_TEST,
			"ValuelessClusteredEntity");

	private PersistenceManager manager = resource.getPersistenceManager();

	@Test public void should_find_by_slice_query() throws Exception{Long id=RandomUtils.nextLong();String name1="name1";String name2="name2";String name3="name3";String name4="name4";String name5="name5";manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name1)));manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name2)));manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name3)));manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name4)));manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name5)));List<ValuelessClusteredEntity> result=manager.sliceQuery(ValuelessClusteredEntity.class).partitionComponents(id).fromClusterings(name5).toClusterings(name2).bounding(BoundingMode.INCLUSIVE_START_BOUND_ONLY).ordering(OrderingMode.DESCENDING).limit(3).get();assertThat(result).hasSize(3);assertThat(result.get(0).getId().getName()).isEqualTo(name5);assertThat(result.get(1).getId().getName()).isEqualTo(name4);assertThat(result.get(2).getId().getName()).isEqualTo(name3);}
}
