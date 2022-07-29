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

package info.archinnov.achilles.internal.metadata.discovery;

import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_TABLE;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.interceptor.Event;
import info.archinnov.achilles.interceptor.Interceptor;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.context.DaoContext;
import info.archinnov.achilles.internal.context.DaoContextFactory;
import info.archinnov.achilles.internal.context.SchemaContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.parsing.EntityParser;
import info.archinnov.achilles.internal.metadata.parsing.context.EntityParsingContext;
import info.archinnov.achilles.internal.metadata.parsing.context.ParsingResult;
import info.archinnov.achilles.test.parser.entity.BeanWithFieldLevelConstraint;
import info.archinnov.achilles.test.parser.entity.UserBean;

@RunWith(MockitoJUnitRunner.class)
public class AchillesBootstrapperTest {

    private AchillesBootstrapper bootstrapper = new AchillesBootstrapper();

    @Mock
    private EntityParser parser;

    @Mock
    private DaoContextFactory factory;

    @Mock
    private ConfigurationContext configContext;

    @Mock
    private SchemaContext schemaContext;

    @Mock
    private EntityMeta meta;

    @Mock
    private TableMetadata tableMeta;

    @Mock
    private Session session;

    @Mock
    private ParsingResult parsingResult;

    @Captor
    private ArgumentCaptor<EntityParsingContext> contextCaptor;

    @Before
    public void setUp() {
        Whitebox.setInternalState(bootstrapper, EntityParser.class, parser);
        Whitebox.setInternalState(bootstrapper, DaoContextFactory.class, factory);
    }

    @Test public void should_validate_and_update_tables() throws Exception{Map<Class<?>, EntityMeta> metas=ImmutableMap.<Class<?>,EntityMeta>of(UserBean.class,meta);Map<String, TableMetadata> tableMetaDatas=ImmutableMap.of("userbean",tableMeta);when(schemaContext.fetchTableMetaData()).thenReturn(tableMetaDatas);when(meta.getTableName()).thenReturn("UserBean");when(schemaContext.entityMetaEntrySet()).thenReturn(metas.entrySet());when(schemaContext.hasSimpleCounter()).thenReturn(false);bootstrapper.validateOrCreateTables(schemaContext);verify(schemaContext).validateForEntity(meta,tableMeta);verify(schemaContext).updateForEntity(meta,tableMeta);}

    private Interceptor<String> stringInterceptor1 = new Interceptor<String>() {
        @Override
        public void onEvent(String entity) {
        }

        @Override
        public List<Event> events() {
            return null;
        }
    };

    private Interceptor<String> stringInterceptor2 = new Interceptor<String>() {
        @Override
        public void onEvent(String entity) {
        }

        @Override
        public List<Event> events() {
            return null;
        }
    };

    private Interceptor<Long> longInterceptor = new Interceptor<Long>() {
        @Override
        public void onEvent(Long entity) {
        }

        @Override
        public List<Event> events() {
            return null;
        }
    };
}
