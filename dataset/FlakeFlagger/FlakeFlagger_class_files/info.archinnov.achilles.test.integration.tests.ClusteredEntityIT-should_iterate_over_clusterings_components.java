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

	@Test public void should_iterate_over_clusterings_components() throws Exception{long partitionKey=RandomUtils.nextLong();insertClusteredEntity(partitionKey,1,"name11","val11");insertClusteredEntity(partitionKey,1,"name12","val12");insertClusteredEntity(partitionKey,1,"name13","val13");insertClusteredEntity(partitionKey,2,"name21","val21");insertClusteredEntity(partitionKey,2,"name22","val22");insertClusteredEntity(partitionKey,3,"name31","val31");insertClusteredEntity(partitionKey,4,"name41","val41");final Iterator<ClusteredEntity> iterator=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).fromClusterings(1).bounding(INCLUSIVE_START_BOUND_ONLY).limit(6).iterator(2);assertThat(iterator.hasNext()).isTrue();assertThat(iterator.next().getValue()).isEqualTo("val11");assertThat(iterator.hasNext()).isTrue();assertThat(iterator.next().getValue()).isEqualTo("val12");assertThat(iterator.hasNext()).isTrue();assertThat(iterator.next().getValue()).isEqualTo("val13");assertThat(iterator.hasNext()).isTrue();assertThat(iterator.next().getValue()).isEqualTo("val21");assertThat(iterator.hasNext()).isTrue();assertThat(iterator.next().getValue()).isEqualTo("val22");assertThat(iterator.hasNext()).isTrue();assertThat(iterator.next().getValue()).isEqualTo("val31");assertThat(iterator.hasNext()).isFalse();}

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
