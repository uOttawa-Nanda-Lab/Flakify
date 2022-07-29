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
package info.archinnov.achilles.internal.table;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.COUNTER;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.LIST;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.MAP;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SET;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.IndexProperties;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.type.Counter;

@RunWith(MockitoJUnitRunner.class)
public class TableUpdaterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private TableUpdater updater;

    @Mock
    private Session session;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Cluster cluster;

    @Mock
    private KeyspaceMetadata keyspaceMeta;

    @Mock
    private TableMetadata tableMeta;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    private String keyspaceName = "achilles";

    private EntityMeta meta;

    @Before
    public void setUp() throws Exception {
        when(cluster.getMetadata().getKeyspace(keyspaceName)).thenReturn(keyspaceMeta);
        when(keyspaceMeta.getTables()).thenReturn(new ArrayList<TableMetadata>());
        when(keyspaceMeta.getTables()).thenReturn(asList(tableMeta));
        when(tableMeta.getName()).thenReturn("tableName");
    }

    @Test public void should_update_table_with_new_counter_field() throws Exception{PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Counter.class).type(COUNTER).field("count").build();meta=new EntityMeta();meta.setAllMetasExceptId(asList(longColPM));meta.setIdMeta(idMeta);meta.setTableName("tableName");meta.setClassName("entityName");meta.setSchemaUpdateEnabled(true);updater.updateTableForEntity(session,meta,tableMeta);verify(session).execute(stringCaptor.capture());assertThat(stringCaptor.getValue()).isEqualTo("\n\tALTER TABLE tableName ADD count counter");}

}
