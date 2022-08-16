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

import static info.archinnov.achilles.test.integration.entity.ClusteredEntityWithCounter.TABLE_NAME;
import static info.archinnov.achilles.type.CounterBuilder.incr;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import com.datastax.driver.core.Session;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.junit.AchillesTestResource.Steps;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.ClusteredEntityWithCounter;
import info.archinnov.achilles.test.integration.entity.ClusteredEntityWithCounter.ClusteredKey;

public class ClusteredEntityWithCounterIT {

	@Rule
	public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(Steps.AFTER_TEST, TABLE_NAME);

	private PersistenceManager manager = resource.getPersistenceManager();

	private Session session = resource.getNativeSession();

	private ClusteredEntityWithCounter entity;

	private ClusteredKey compoundKey;

	@Test
	public void should_query_with_default_params() throws Exception {
		long partitionKey = RandomUtils.nextLong();
		List<ClusteredEntityWithCounter> entities = manager.sliceQuery(ClusteredEntityWithCounter.class)
				.partitionComponents(partitionKey).fromClusterings("name2").toClusterings("name4").get();

		assertThat(entities).isEmpty();

		insertValues(partitionKey, 5);

		entities = manager.sliceQuery(ClusteredEntityWithCounter.class).partitionComponents(partitionKey)
				.fromClusterings("name2").toClusterings("name4").get();

		assertThat(entities).hasSize(3);

		assertThat(entities.get(0).getCounter().get()).isEqualTo(2);
		assertThat(entities.get(0).getVersion().get()).isEqualTo(2);
		assertThat(entities.get(0).getId().getId()).isEqualTo(partitionKey);
		assertThat(entities.get(0).getId().getName()).isEqualTo("name2");
		assertThat(entities.get(1).getCounter().get()).isEqualTo(3);
        assertThat(entities.get(1).getVersion().get()).isEqualTo(3);
		assertThat(entities.get(1).getId().getId()).isEqualTo(partitionKey);
		assertThat(entities.get(1).getId().getName()).isEqualTo("name3");
		assertThat(entities.get(2).getCounter().get()).isEqualTo(4);
        assertThat(entities.get(2).getVersion().get()).isEqualTo(4);
		assertThat(entities.get(2).getId().getId()).isEqualTo(partitionKey);
		assertThat(entities.get(2).getId().getName()).isEqualTo("name4");

		entities = manager.sliceQuery(ClusteredEntityWithCounter.class)
				.fromEmbeddedId(new ClusteredKey(partitionKey, "name2"))
				.toEmbeddedId(new ClusteredKey(partitionKey, "name4")).get();

		assertThat(entities).hasSize(3);

		assertThat(entities.get(0).getCounter().get()).isEqualTo(2);
        assertThat(entities.get(0).getVersion().get()).isEqualTo(2);
		assertThat(entities.get(0).getId().getId()).isEqualTo(partitionKey);
		assertThat(entities.get(0).getId().getName()).isEqualTo("name2");
		assertThat(entities.get(1).getCounter().get()).isEqualTo(3);
        assertThat(entities.get(1).getVersion().get()).isEqualTo(3);
		assertThat(entities.get(1).getId().getId()).isEqualTo(partitionKey);
		assertThat(entities.get(1).getId().getName()).isEqualTo("name3");
		assertThat(entities.get(2).getCounter().get()).isEqualTo(4);
        assertThat(entities.get(2).getVersion().get()).isEqualTo(4);
		assertThat(entities.get(2).getId().getId()).isEqualTo(partitionKey);
		assertThat(entities.get(2).getId().getName()).isEqualTo("name4");
	}

	private void insertClusteredEntity(Long partitionKey, String name, Long counterValue) {
		ClusteredKey embeddedId = new ClusteredKey(partitionKey, name);
		ClusteredEntityWithCounter entity = new ClusteredEntityWithCounter(embeddedId,
				incr(counterValue),incr(counterValue));
		manager.persist(entity);
	}

	private void insertValues(long partitionKey, int count) {
		String namePrefix = "name";

		for (int i = 1; i <= count; i++) {
			insertClusteredEntity(partitionKey, namePrefix + i, new Long(i));
		}
	}
}
