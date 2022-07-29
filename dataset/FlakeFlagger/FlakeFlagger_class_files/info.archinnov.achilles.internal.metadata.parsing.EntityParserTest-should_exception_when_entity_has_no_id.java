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
package info.archinnov.achilles.internal.metadata.parsing;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.COUNTER;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.EMBEDDED_ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static org.fest.assertions.api.Assertions.assertThat;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.exception.AchillesBeanMappingException;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.metadata.holder.CounterProperties;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.metadata.parsing.context.EntityParsingContext;
import info.archinnov.achilles.json.ObjectMapperFactory;
import info.archinnov.achilles.test.parser.entity.Bean;
import info.archinnov.achilles.test.parser.entity.BeanWithClusteredId;
import info.archinnov.achilles.test.parser.entity.BeanWithColumnFamilyName;
import info.archinnov.achilles.test.parser.entity.BeanWithDuplicatedColumnName;
import info.archinnov.achilles.test.parser.entity.BeanWithIdAndColumnAnnotationsOnSameField;
import info.archinnov.achilles.test.parser.entity.BeanWithInsertStrategy;
import info.archinnov.achilles.test.parser.entity.BeanWithNoId;
import info.archinnov.achilles.test.parser.entity.BeanWithSimpleCounter;
import info.archinnov.achilles.test.parser.entity.ChildBean;
import info.archinnov.achilles.test.parser.entity.ClusteredEntity;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;
import info.archinnov.achilles.test.parser.entity.UserBean;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.Counter;
import info.archinnov.achilles.type.InsertStrategy;

@RunWith(MockitoJUnitRunner.class)
public class EntityParserTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private EntityParser parser = new EntityParser();

    private ConfigurationContext configContext = new ConfigurationContext();

    @Mock
    private Map<Class<?>, EntityMeta> entityMetaMap;

    private ObjectMapperFactory objectMapperFactory = new ObjectMapperFactory() {
        @Override
        public <T> ObjectMapper getMapper(Class<T> type) {
            return objectMapper;
        }
    };
    private ObjectMapper objectMapper = new ObjectMapper();

    private EntityParsingContext entityContext;

    @Before
    public void setUp() {
        configContext.setDefaultReadConsistencyLevel(ConsistencyLevel.ONE);
        configContext.setDefaultWriteConsistencyLevel(ConsistencyLevel.ALL);
        configContext.setEnableSchemaUpdateForTables(ImmutableMap.<String, Boolean>of());
        configContext.setObjectMapperFactory(objectMapperFactory);
        configContext.setInsertStrategy(InsertStrategy.ALL_FIELDS);
    }

    @Test public void should_exception_when_entity_has_no_id() throws Exception{initEntityParsingContext(BeanWithNoId.class);expectedEx.expect(AchillesBeanMappingException.class);expectedEx.expectMessage("The entity '" + BeanWithNoId.class.getCanonicalName() + "' should have at least one field with javax.persistence.Id/javax.persistence.EmbeddedId annotation");parser.parseEntity(entityContext);}

    private <T> void initEntityParsingContext(Class<T> entityClass) {
        entityContext = new EntityParsingContext(configContext, entityClass);
    }
}
