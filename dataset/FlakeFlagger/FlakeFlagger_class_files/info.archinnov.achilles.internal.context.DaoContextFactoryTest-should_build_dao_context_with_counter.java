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
package info.archinnov.achilles.internal.context;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static info.archinnov.achilles.test.builders.PropertyMetaTestBuilder.completeBean;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.google.common.base.Function;
import com.google.common.cache.Cache;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.counter.AchillesCounter.CQLQueryType;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.parsing.context.ParsingResult;
import info.archinnov.achilles.internal.statement.cache.StatementCacheKey;
import info.archinnov.achilles.internal.statement.prepared.PreparedStatementGenerator;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;

@RunWith(MockitoJUnitRunner.class)
public class DaoContextFactoryTest {
    @InjectMocks
    private DaoContextFactory builder;

    @Mock
    private EntityMeta entityMeta;

    @Mock
    private Session session;

    @Mock
    private PreparedStatementGenerator queryGenerator;

    @Mock
    private PreparedStatement selectForExistenceCheckPS;

    @Mock
    private PreparedStatement selectEagerPS;

    @Mock
    private Map<String, PreparedStatement> removePSs;

    @Mock
    private Map<CQLQueryType, PreparedStatement> counterQueryMap;

    @Mock
    private Map<CQLQueryType, Map<String, PreparedStatement>> clusteredCounterQueryMap;

    @Mock
    private ParsingResult parsingResult;

    @Mock
    private ConfigurationContext configContext;

    @Before
    public void setUp() {
        Whitebox.setInternalState(builder, PreparedStatementGenerator.class, queryGenerator);
    }

    @Test public void should_build_dao_context_with_counter() throws Exception{Map<Class<?>, EntityMeta> entityMetaMap=new HashMap<>();EntityMeta meta=new EntityMeta();PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();meta.setPropertyMetas(ImmutableMap.of("name",nameMeta));entityMetaMap.put(CompleteBean.class,meta);when(queryGenerator.prepareSelectAll(session,meta)).thenReturn(selectEagerPS);when(queryGenerator.prepareRemovePSs(session,meta)).thenReturn(removePSs);when(queryGenerator.prepareSimpleCounterQueryMap(session)).thenReturn(counterQueryMap);when(parsingResult.getMetaMap()).thenReturn(entityMetaMap);when(parsingResult.hasSimpleCounter()).thenReturn(true);when(configContext.getPreparedStatementLRUCacheSize()).thenReturn(100);DaoContext actual=builder.create(session,parsingResult,configContext);assertThat(Whitebox.<Map<Class<?>, PreparedStatement>>getInternalState(actual,"selectPSs")).containsValue(selectEagerPS);assertThat(Whitebox.<Map<Class<?>, Map<String, PreparedStatement>>>getInternalState(actual,"removePSs")).containsKey(CompleteBean.class);assertThat(Whitebox.<Cache<StatementCacheKey, PreparedStatement>>getInternalState(actual,"dynamicPSCache")).isInstanceOf(Cache.class);assertThat(Whitebox.<Map<CQLQueryType, PreparedStatement>>getInternalState(actual,"counterQueryMap")).isSameAs(counterQueryMap);}
}
