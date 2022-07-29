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
	public void should_refresh() throws Exception {
		long counterValue = RandomUtils.nextLong();
		long incr = RandomUtils.nextLong();

		long partitionKey = RandomUtils.nextLong();
		String name = "name";
		compoundKey = new ClusteredKey(partitionKey, name);

		entity = new ClusteredEntityWithCounter(compoundKey, incr(counterValue));

		entity = manager.persist(entity);

		session.execute("UPDATE " + TABLE_NAME + " SET counter = counter + " + incr + " WHERE id=" + partitionKey
				+ " AND name='name'");
        session.execute("UPDATE " + TABLE_NAME + " SET version = version + " + incr + " WHERE id=" + partitionKey
                                + " AND name='name'");
		// Wait for the counter to be updated
		Thread.sleep(100);

		manager.refresh(entity);

		assertThat(entity.getCounter().get()).isEqualTo(counterValue + incr);
		assertThat(entity.getVersion().get()).isEqualTo(incr);

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
