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
package info.archinnov.achilles.internal.statement.prepared;

import static com.google.common.base.Optional.fromNullable;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.EMBEDDED_ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.LIST;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.MAP;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SET;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.APPEND_TO_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ASSIGN_VALUE_TO_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ASSIGN_VALUE_TO_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ASSIGN_VALUE_TO_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.PREPEND_TO_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_COLLECTION_OR_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_LIST_AT_INDEX;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.SET_TO_LIST_AT_INDEX;
import static info.archinnov.achilles.test.builders.PropertyMetaTestBuilder.completeBean;
import static info.archinnov.achilles.type.ConsistencyLevel.ALL;
import static info.archinnov.achilles.type.Options.CASCondition;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import info.archinnov.achilles.internal.consistency.ConsistencyOverrider;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.transcoding.DataTranscoder;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet;
import info.archinnov.achilles.internal.reflection.ReflectionInvoker;
import info.archinnov.achilles.internal.statement.wrapper.BoundStatementWrapper;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;

@RunWith(MockitoJUnitRunner.class)
public class PreparedStatementBinderTest {

    @InjectMocks
    private PreparedStatementBinder binder;

    private static final Optional<Integer> NO_TTL = Optional.absent();
    private static final Optional<Long> NO_TIMESTAMP = Optional.absent();
    private static final Optional<info.archinnov.achilles.type.ConsistencyLevel> NO_CONSISTENCY = Optional.absent();
    private  static final Optional<com.datastax.driver.core.ConsistencyLevel> NO_SERIAL_CONSISTENCY = Optional.absent();

    @Mock
    private ReflectionInvoker invoker;

    @Mock
    private PreparedStatement ps;

    @Mock
    private BoundStatement bs;

    @Mock
    private DataTranscoder transcoder;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private DirtyCheckChangeSet changeSet;

    @Mock
    private PersistenceContext.StateHolderFacade context;

    @Mock
    private ConsistencyOverrider overrider;

    private EntityMeta entityMeta;

    private CompleteBean entity = CompleteBeanTestBuilder.builder().randomId().buid();

    @Before
    public void setUp() {
        entityMeta = new EntityMeta();
        when(context.getEntity()).thenReturn(entity);
        when(context.getEntityMeta()).thenReturn(entityMeta);
        when(context.getTtl()).thenReturn(NO_TTL);
        when(context.getTimestamp()).thenReturn(NO_TIMESTAMP);
        when(context.getConsistencyLevel()).thenReturn(NO_CONSISTENCY);
        when(context.getSerialConsistencyLevel()).thenReturn(NO_SERIAL_CONSISTENCY);
    }

    @Test public void should_bind_for_remove_entry_from_map_with_cas_condition() throws Exception{PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").transcoder(transcoder).type(ID).invoker(invoker).build();Long primaryKey=RandomUtils.nextLong();final CASCondition CASCondition=new CASCondition("name","John");EntityMeta meta=mock(EntityMeta.class);when(meta.getClassName()).thenReturn("CompleteBean");when(meta.getIdMeta()).thenReturn(idMeta);when(meta.getPrimaryKey(entity)).thenReturn(primaryKey);when(meta.encodeCasConditionValue(CASCondition)).thenReturn("John");when(context.getEntityMeta()).thenReturn(meta);when(context.getIdMeta()).thenReturn(idMeta);when(context.getPrimaryKey()).thenReturn(primaryKey);when(context.hasCasConditions()).thenReturn(true);when(context.getCasConditions()).thenReturn(asList(CASCondition));when(overrider.getWriteLevel(context)).thenReturn(ALL);when(invoker.getPrimaryKey(entity,idMeta)).thenReturn(primaryKey);when(transcoder.encode(idMeta,primaryKey)).thenReturn(primaryKey);final Map<Object, Object> values=ImmutableMap.<Object,Object>of(1,"whatever");when(changeSet.getChangeType()).thenReturn(REMOVE_FROM_MAP);when(changeSet.getEncodedMapChanges()).thenReturn(values);when(ps.bind(0,1,null,primaryKey,"John")).thenReturn(bs);final BoundStatementWrapper actual=binder.bindForCollectionAndMapUpdate(context,ps,changeSet);verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);assertThat(asList(actual.getValues())).containsExactly(0,1,null,primaryKey,"John");}
}
