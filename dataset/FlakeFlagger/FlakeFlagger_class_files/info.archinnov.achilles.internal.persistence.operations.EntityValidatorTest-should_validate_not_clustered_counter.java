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

package info.archinnov.achilles.internal.persistence.operations;

import static org.mockito.Mockito.when;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.mapping.entity.ClusteredEntityWithCounter;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;

import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EntityValidatorTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@InjectMocks
	private EntityValidator entityValidator;

	@Mock
	private EntityProxifier proxifier;

	@Mock
	private Map<Class<?>, EntityMeta> entityMetaMap;

	@Mock
	private EntityMeta entityMeta;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private PropertyMeta idMeta;

	@Mock
	private PersistenceContext context;

	@Before
	public void setUp() {
		when(entityMeta.getIdMeta()).thenReturn(idMeta);
	}

	@Test public void should_validate_not_clustered_counter() throws Exception{ClusteredEntityWithCounter entity=new ClusteredEntityWithCounter();when(proxifier.<ClusteredEntityWithCounter>deriveBaseClass(entity)).thenReturn(ClusteredEntityWithCounter.class);when(entityMetaMap.get(ClusteredEntityWithCounter.class)).thenReturn(entityMeta);when(entityMeta.isClusteredCounter()).thenReturn(false);entityValidator.validateNotClusteredCounter(entity,entityMetaMap);}

}
