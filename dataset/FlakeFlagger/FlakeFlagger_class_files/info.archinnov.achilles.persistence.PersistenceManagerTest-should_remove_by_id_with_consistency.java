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
package info.archinnov.achilles.persistence;

import static info.archinnov.achilles.type.ConsistencyLevel.EACH_QUORUM;
import static info.archinnov.achilles.type.ConsistencyLevel.LOCAL_QUORUM;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.context.DaoContext;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.context.PersistenceContextFactory;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.persistence.operations.EntityInitializer;
import info.archinnov.achilles.internal.persistence.operations.EntityProxifier;
import info.archinnov.achilles.internal.persistence.operations.EntityValidator;
import info.archinnov.achilles.internal.persistence.operations.OptionsValidator;
import info.archinnov.achilles.internal.persistence.operations.SliceQueryExecutor;
import info.archinnov.achilles.query.cql.NativeQuery;
import info.archinnov.achilles.query.slice.SliceQueryBuilder;
import info.archinnov.achilles.query.typed.TypedQuery;
import info.archinnov.achilles.query.typed.TypedQueryValidator;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.IndexCondition;
import info.archinnov.achilles.type.Options;
import info.archinnov.achilles.type.OptionsBuilder;

@RunWith(MockitoJUnitRunner.class)
public class PersistenceManagerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private EntityInitializer initializer;

    @Mock
    private EntityProxifier proxifier;

    @Mock
    private EntityValidator entityValidator;

    @Mock
    private TypedQueryValidator typedQueryValidator;

    @Mock
    private SliceQueryExecutor sliceQueryExecutor;

    @Mock
    private OptionsValidator optionsValidator;

    @Mock
    private PersistenceManagerFactory pmf;

    @Mock
    private PersistenceContextFactory contextFactory;

    @Mock
    private DaoContext daoContext;

    @Mock
    private ConfigurationContext configContext;

    @Mock
    private PersistenceContext context;

    @Mock
    private PersistenceContext.PersistenceManagerFacade facade;

    @Mock
    private PersistenceContext.EntityFacade entityFacade;

    @Mock
    private Map<Class<?>, EntityMeta> entityMetaMap;

    @Mock
    private EntityMeta meta;

    @Mock
    private PropertyMeta idMeta;

    @Captor
    private ArgumentCaptor<Options> optionsCaptor;

    private PersistenceManager manager;

    private Long primaryKey = RandomUtils.nextLong();
    private CompleteBean entity = CompleteBeanTestBuilder.builder().id(primaryKey).buid();

    @Before
    public void setUp() throws Exception {
        when(contextFactory.newContext(eq(entity), optionsCaptor.capture())).thenReturn(context);
        when(context.getPersistenceManagerFacade()).thenReturn(facade);
        when(context.getEntityFacade()).thenReturn(entityFacade);
        when(configContext.getDefaultReadConsistencyLevel()).thenReturn(ConsistencyLevel.EACH_QUORUM);
        when(meta.getIdMeta()).thenReturn(idMeta);

        manager = new PersistenceManager(entityMetaMap, contextFactory, daoContext, configContext);
        manager = Mockito.spy(this.manager);
        Whitebox.setInternalState(manager, EntityProxifier.class, proxifier);
        Whitebox.setInternalState(manager, EntityValidator.class, entityValidator);
        Whitebox.setInternalState(manager, OptionsValidator.class, optionsValidator);
        Whitebox.setInternalState(manager, SliceQueryExecutor.class, sliceQueryExecutor);
        Whitebox.setInternalState(manager, TypedQueryValidator.class, typedQueryValidator);
        Whitebox.setInternalState(manager, PersistenceContextFactory.class, contextFactory);

        manager.setEntityMetaMap(entityMetaMap);
        entityMetaMap.put(CompleteBean.class, meta);
    }

    @Test public void should_remove_by_id_with_consistency() throws Exception{when(contextFactory.newContext(eq(CompleteBean.class),eq(primaryKey),optionsCaptor.capture())).thenReturn(context);when(facade.getIdMeta()).thenReturn(idMeta);manager.removeById(CompleteBean.class,primaryKey,LOCAL_QUORUM);verify(entityValidator).validatePrimaryKey(idMeta,primaryKey);verify(facade).remove();Options options=optionsCaptor.getValue();assertThat(options.getConsistencyLevel().get()).isSameAs(LOCAL_QUORUM);assertThat(options.getTtl().isPresent()).isFalse();assertThat(options.getTimestamp().isPresent()).isFalse();}
}
