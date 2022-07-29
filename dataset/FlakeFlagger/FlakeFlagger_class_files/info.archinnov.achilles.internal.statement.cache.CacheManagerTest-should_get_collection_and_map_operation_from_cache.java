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
package info.archinnov.achilles.internal.statement.cache;

import static com.google.common.collect.Sets.newHashSet;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_SET;
import static info.archinnov.achilles.test.builders.PropertyMetaTestBuilder.completeBean;
import static info.archinnov.achilles.type.OptionsBuilder.noOptions;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.google.common.cache.Cache;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet;
import info.archinnov.achilles.internal.statement.prepared.PreparedStatementGenerator;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;

@RunWith(MockitoJUnitRunner.class)
public class CacheManagerTest {
    @InjectMocks
    private CacheManager manager = new CacheManager(100);

    @Mock
    private PreparedStatementGenerator generator;

    @Mock
    private Session session;

    @Mock
    private Cache<StatementCacheKey, PreparedStatement> cache;

    @Mock
    private PersistenceContext.StateHolderFacade context;

    @Mock
    private PreparedStatement ps;

    @Captor
    ArgumentCaptor<StatementCacheKey> cacheKeyCaptor;

    @Before
    public void setUp() {
        when(context.getOptions()).thenReturn(noOptions());
    }

    @Test public void should_get_collection_and_map_operation_from_cache() throws Exception{EntityMeta meta=mock(EntityMeta.class);PropertyMeta pm=mock(PropertyMeta.class);DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(pm,ADD_TO_SET);when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);when(context.getEntityMeta()).thenReturn(meta);when(pm.getPropertyName()).thenReturn("property");StatementCacheKey cacheKey=new StatementCacheKey(CacheType.ADD_TO_SET,newHashSet("property"),CompleteBean.class,noOptions());when(cache.getIfPresent(cacheKey)).thenReturn(ps);final PreparedStatement actual=manager.getCacheForCollectionAndMapOperation(session,cache,context,pm,changeSet);assertThat(actual).isSameAs(ps);verify(cache,never()).put(cacheKey,ps);verifyZeroInteractions(generator);}
}
