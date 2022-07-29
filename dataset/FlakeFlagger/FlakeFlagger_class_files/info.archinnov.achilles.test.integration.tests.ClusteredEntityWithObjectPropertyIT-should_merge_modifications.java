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

import static info.archinnov.achilles.test.integration.entity.ClusteredEntityWithObjectValue.TABLE_NAME;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.math.RandomUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import com.datastax.driver.core.Session;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.junit.AchillesTestResource.Steps;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.ClusteredEntityWithObjectValue;
import info.archinnov.achilles.test.integration.entity.ClusteredEntityWithObjectValue.ClusteredKey;
import info.archinnov.achilles.test.integration.entity.ClusteredEntityWithObjectValue.Holder;

public class ClusteredEntityWithObjectPropertyIT {

	@Rule
	public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(Steps.AFTER_TEST, TABLE_NAME);

	private PersistenceManager manager = resource.getPersistenceManager();

	private Session session = resource.getNativeSession();

	private ClusteredEntityWithObjectValue entity;

	private ClusteredKey compoundKey;

	private ObjectMapper mapper = new ObjectMapper();

	@Test public void should_merge_modifications() throws Exception{compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");Holder holder=new Holder("content");Holder newHolder=new Holder("new_content");entity=new ClusteredEntityWithObjectValue(compoundKey,holder);entity=manager.persist(entity);entity.setValue(newHolder);manager.update(entity);entity=manager.find(ClusteredEntityWithObjectValue.class,compoundKey);assertThat(entity.getValue()).isEqualTo(newHolder);}

	private void insertClusteredEntity(Long partitionKey, String name, Holder clusteredValue) {
		ClusteredKey embeddedId = new ClusteredKey(partitionKey, name);
		ClusteredEntityWithObjectValue entity = new ClusteredEntityWithObjectValue(embeddedId, clusteredValue);
		manager.persist(entity);
	}

	private void insertValues(long partitionKey, int count) {
		String namePrefix = "name";

		for (int i = 1; i <= count; i++) {
			insertClusteredEntity(partitionKey, namePrefix + i, new Holder(namePrefix + i));
		}
	}
}
