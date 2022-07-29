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

import static info.archinnov.achilles.test.integration.entity.ClusteredEntity.TABLE_NAME;
import static info.archinnov.achilles.type.BoundingMode.INCLUSIVE_END_BOUND_ONLY;
import static info.archinnov.achilles.type.BoundingMode.INCLUSIVE_START_BOUND_ONLY;
import static info.archinnov.achilles.type.ConsistencyLevel.EACH_QUORUM;
import static info.archinnov.achilles.type.OrderingMode.DESCENDING;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.junit.AchillesTestResource.Steps;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.ClusteredEntity;
import info.archinnov.achilles.test.integration.entity.ClusteredEntity.ClusteredKey;
import info.archinnov.achilles.type.OptionsBuilder;

public class ClusteredEntityIT {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Rule
	public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(Steps.AFTER_TEST, TABLE_NAME);

	private PersistenceManager manager = resource.getPersistenceManager();

	private Session session = resource.getNativeSession();

	private ClusteredEntity entity;

	private ClusteredKey compoundKey;

	@Test public void should_query_with_getFirst() throws Exception{long partitionKey=RandomUtils.nextLong();ClusteredEntity entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirstOccurence();assertThat(entity).isNull();String clusteredValuePrefix=insertValues(partitionKey,1,5);entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirstOccurence();assertThat(entity.getValue()).isEqualTo(clusteredValuePrefix + 1);entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirstOccurence();assertThat(entity.getValue()).isEqualTo(clusteredValuePrefix + 1);List<ClusteredEntity> entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirst(3);assertThat(entities).hasSize(3);assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 1);assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 2);assertThat(entities.get(2).getValue()).isEqualTo(clusteredValuePrefix + 3);insertClusteredEntity(partitionKey,4,"name41",clusteredValuePrefix + 41);insertClusteredEntity(partitionKey,4,"name42",clusteredValuePrefix + 42);entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirst(3,4);assertThat(entities).hasSize(2);assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 41);assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 42);}

	private String insertValues(long partitionKey, int countValue, int size) {
		String namePrefix = "name";
		String clusteredValuePrefix = "value";

		for (int i = 1; i <= size; i++) {
			insertClusteredEntity(partitionKey, countValue, namePrefix + i, clusteredValuePrefix + i);
		}
		return clusteredValuePrefix;
	}

	private void insertClusteredEntity(Long partitionKey, int count, String name, String clusteredValue) {
		ClusteredKey embeddedId = new ClusteredKey(partitionKey, count, name);
		ClusteredEntity entity = new ClusteredEntity(embeddedId, clusteredValue);
		manager.persist(entity);
	}
}
