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

import static info.archinnov.achilles.counter.AchillesCounter.CQLQueryType.DECR;
import static info.archinnov.achilles.counter.AchillesCounter.CQLQueryType.DELETE;
import static info.archinnov.achilles.counter.AchillesCounter.CQLQueryType.INCR;
import static info.archinnov.achilles.counter.AchillesCounter.CQLQueryType.SELECT;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_FQCN;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_PRIMARY_KEY;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_PROPERTY_NAME;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_TABLE;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_VALUE;
import static info.archinnov.achilles.counter.AchillesCounter.ClusteredCounterStatement.DELETE_ALL;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.COUNTER;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.LIST;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.MAP;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.APPEND_TO_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.PREPEND_TO_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_COLLECTION_OR_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_LIST_AT_INDEX;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.SET_TO_LIST_AT_INDEX;
import static info.archinnov.achilles.test.builders.PropertyMetaTestBuilder.completeBean;
import static info.archinnov.achilles.type.Options.CASCondition;
import static info.archinnov.achilles.type.OptionsBuilder.ifConditions;
import static info.archinnov.achilles.type.OptionsBuilder.ifNotExists;
import static info.archinnov.achilles.type.OptionsBuilder.noOptions;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.Session;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.counter.AchillesCounter.CQLQueryType;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet;

@RunWith(MockitoJUnitRunner.class)
public class PreparedStatementGeneratorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PreparedStatementGenerator generator = new PreparedStatementGenerator();

    @Mock
    private Session session;

    @Mock
    private PreparedStatement ps;

    @Mock
    private PreparedStatement ps2;

    @Captor
    ArgumentCaptor<String> queryCaptor;

    @Captor
    ArgumentCaptor<RegularStatement> regularStatementCaptor;

    @Test(expected=IllegalStateException.class) public void should_not_prepare_statement_to_set_element_at_index_from_list() throws Exception{PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();PropertyMeta listMeta=completeBean(Void.class,String.class).field("friends").type(LIST).build();EntityMeta meta=new EntityMeta();meta.setIdMeta(idMeta);meta.setTableName("table");meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"friends",listMeta));when(session.prepare(regularStatementCaptor.capture())).thenReturn(ps);DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(listMeta,SET_TO_LIST_AT_INDEX);generator.prepareCollectionAndMapUpdate(session,meta,changeSet,noOptions());}
}
