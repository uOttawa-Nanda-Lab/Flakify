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

import static info.archinnov.achilles.test.integration.entity.EntityWithCompositePartitionKey.TABLE_NAME;
import static org.fest.assertions.api.Assertions.assertThat;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.junit.AchillesTestResource.Steps;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.EntityWithCompositePartitionKey;
import info.archinnov.achilles.test.integration.entity.EntityWithCompositePartitionKey.EmbeddedKey;
import net.sf.cglib.proxy.Factory;

public class EntityWithCompositePartitionKeyIT {

	@Rule
	public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(Steps.AFTER_TEST, TABLE_NAME);

	private PersistenceManager manager = resource.getPersistenceManager();

	private Session session = manager.getNativeSession();

	@Test
	public void should_refresh() throws Exception {
		long id = RandomUtils.nextLong();

		EntityWithCompositePartitionKey entity = new EntityWithCompositePartitionKey(id, "type", "value");

		entity = manager.persist(entity);

		session.execute("UPDATE " + TABLE_NAME + " SET value='new_value' WHERE id=" + id + " AND type='type'");

		manager.refresh(entity);

		assertThat(entity.getValue()).isEqualTo("new_value");
	}
}
