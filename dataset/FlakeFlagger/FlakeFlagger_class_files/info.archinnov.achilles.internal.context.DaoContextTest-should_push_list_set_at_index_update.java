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

import static com.datastax.driver.core.querybuilder.QueryBuilder.update;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.ImmutableMap.of;
import static info.archinnov.achilles.counter.AchillesCounter.CQLQueryType.DELETE;
import static info.archinnov.achilles.counter.AchillesCounter.CQLQueryType.INCR;
import static info.archinnov.achilles.counter.AchillesCounter.CQLQueryType.SELECT;
import static info.archinnov.achilles.counter.AchillesCounter.ClusteredCounterStatement.DELETE_ALL;
import static info.archinnov.achilles.counter.AchillesCounter.ClusteredCounterStatement.SELECT_ALL;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_LIST_AT_INDEX;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.SET_TO_LIST_AT_INDEX;
import static info.archinnov.achilles.type.ConsistencyLevel.ALL;
import static info.archinnov.achilles.type.ConsistencyLevel.EACH_QUORUM;
import static info.archinnov.achilles.type.ConsistencyLevel.LOCAL_QUORUM;
import static info.archinnov.achilles.type.ConsistencyLevel.ONE;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.querybuilder.Update.Where;
import com.datastax.driver.core.querybuilder.Using;
import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import info.archinnov.achilles.counter.AchillesCounter.CQLQueryType;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.internal.consistency.ConsistencyOverrider;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet;
import info.archinnov.achilles.internal.statement.StatementGenerator;
import info.archinnov.achilles.internal.statement.cache.CacheManager;
import info.archinnov.achilles.internal.statement.cache.StatementCacheKey;
import info.archinnov.achilles.internal.statement.prepared.PreparedStatementBinder;
import info.archinnov.achilles.internal.statement.wrapper.BoundStatementWrapper;
import info.archinnov.achilles.internal.statement.wrapper.RegularStatementWrapper;
import info.archinnov.achilles.listener.CASResultListener;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.Pair;

@RunWith(MockitoJUnitRunner.class)
public class DaoContextTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private DaoContext daoContext;

    @Mock
    private StatementGenerator statementGenerator;


    @Mock
    private Cache<StatementCacheKey, PreparedStatement> dynamicPSCache;

    @Mock
    private Map<Class<?>, PreparedStatement> selectEagerPSs;

    @Mock
    private Map<Class<?>, Map<String, PreparedStatement>> removePSs;

    @Mock
    private Map<CQLQueryType, PreparedStatement> counterQueryMap;

    private Map<Class<?>, Map<CQLQueryType, Map<String, PreparedStatement>>> clusteredCounterQueryMap = new HashMap<>();

    @Mock
    private Session session;

    @Mock
    private PreparedStatementBinder binder;

    @Mock
    private CacheManager cacheManager;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private PersistenceContext.DaoFacade context;

    @Mock
    private EntityMeta entityMeta;

    @Mock
    private Insert insert;

    @Mock
    private Update.Where update;

    @Mock
    private PreparedStatement ps;

    @Mock
    private BoundStatementWrapper bsWrapper;

    @Mock
    private BoundStatement bs;

    @Mock
    private DirtyCheckChangeSet changeSet;

    @Mock
    private ConsistencyOverrider overrider;

    @Captor
    ArgumentCaptor<Using> usingCaptor;

    @Captor
    ArgumentCaptor<RegularStatementWrapper> statementWrapperCaptor;

    private CompleteBean entity = CompleteBeanTestBuilder.builder().randomId().buid();

    @Before
    public void setUp() {
        daoContext.binder = binder;
        daoContext.cacheManager = cacheManager;
        daoContext.dynamicPSCache = dynamicPSCache;
        daoContext.selectPSs = selectEagerPSs;
        daoContext.removePSs = removePSs;
        daoContext.counterQueryMap = counterQueryMap;
        daoContext.clusteredCounterQueryMap = clusteredCounterQueryMap;
        daoContext.session = session;
        daoContext.statementGenerator = statementGenerator;
        daoContext.overrider = overrider;
        clusteredCounterQueryMap.clear();
        entityMeta = new EntityMeta();
        entityMeta.setEntityClass(CompleteBean.class);
        entityMeta.setConsistencyLevels(Pair.create(ONE, EACH_QUORUM));

        when(context.getEntityMeta()).thenReturn(entityMeta);
        when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
        when(context.getEntity()).thenReturn(entity);
        when(context.getPrimaryKey()).thenReturn(entity.getId());
        when(context.getOptions().isIfNotExists()).thenReturn(false);

        selectEagerPSs.clear();
        removePSs.clear();
    }

    @Test public void should_push_list_set_at_index_update() throws Exception{final Where where=update("test").where();Object[] boundValues=new Object[]{"whatever"};Pair<Where, Object[]> pair=Pair.create(where,boundValues);final Optional<CASResultListener> casResultListener=Optional.absent();when(changeSet.getChangeType()).thenReturn(SET_TO_LIST_AT_INDEX);when(context.getSerialConsistencyLevel()).thenReturn(fromNullable(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL));when(overrider.getWriteLevel(context)).thenReturn(EACH_QUORUM);when(statementGenerator.generateCollectionAndMapUpdateOperation(context,changeSet)).thenReturn(pair);when(context.getCASResultListener()).thenReturn(casResultListener);daoContext.pushCollectionAndMapUpdateStatement(context,changeSet);verify(context).pushStatement(statementWrapperCaptor.capture());final RegularStatementWrapper statementWrapper=statementWrapperCaptor.getValue();assertThat(statementWrapper.getValues()).contains(boundValues);assertThat(statementWrapper.getStatement().getConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);assertThat(statementWrapper.getStatement().getSerialConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);assertThat(where.getConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);}
}
