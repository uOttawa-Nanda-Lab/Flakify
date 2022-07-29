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

import static com.datastax.driver.core.DataType.counter;
import static com.datastax.driver.core.DataType.text;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_FQCN;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_PRIMARY_KEY;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_PROPERTY_NAME;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_TABLE;
import static info.archinnov.achilles.counter.AchillesCounter.CQL_COUNTER_VALUE;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.COUNTER;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.EMBEDDED_ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.LIST;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.MAP;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SET;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static info.archinnov.achilles.test.builders.PropertyMetaTestBuilder.completeBean;
import static info.archinnov.achilles.test.builders.PropertyMetaTestBuilder.valueClass;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.UUID;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ColumnMetadata;
import com.datastax.driver.core.ColumnMetadata.IndexMetadata;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.TableMetadata;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import info.archinnov.achilles.exception.AchillesBeanMappingException;
import info.archinnov.achilles.exception.AchillesInvalidTableException;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.IndexProperties;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;
import info.archinnov.achilles.type.Counter;

@RunWith(MockitoJUnitRunner.class)
public class TableValidatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TableValidator validator;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Cluster cluster;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private TableMetadata tableMetaData;

    @Mock
    private ColumnMetaDataComparator columnMetaDataComparator;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ColumnMetadata columnMetadata;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ColumnMetadata columnMetadataForField;

    private String keyspaceName = "keyspace";

    private EntityMeta entityMeta;

    @Before
    public void setUp() {
        validator = new TableValidator();
        Whitebox.setInternalState(validator, "columnMetaDataComparator", columnMetaDataComparator);
        entityMeta = new EntityMeta();
        when(columnMetadata.getIndex()).thenReturn(null);
        when(columnMetadataForField.getIndex()).thenReturn(null);
    }

    @Test public void should_validate_embedded_id_with_time_uuid_for_entity() throws Exception{PropertyMeta idMeta=valueClass(EmbeddedKey.class).compNames("userId","date").compClasses(Long.class,UUID.class).type(EMBEDDED_ID).compTimeUUID("date").build();PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();entityMeta.setIdMeta(idMeta);entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(nameMeta));entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());ColumnMetadata userColumn=mock(ColumnMetadata.class);ColumnMetadata nameColumn=mock(ColumnMetadata.class);when(tableMetaData.getName()).thenReturn("table");ColumnMetadata userIdMetadata=mock(ColumnMetadata.class);when(tableMetaData.getColumn("userid")).thenReturn(userIdMetadata);when(userIdMetadata.getType()).thenReturn(DataType.bigint());when(tableMetaData.getPartitionKey()).thenReturn(Arrays.asList(userColumn));when(columnMetaDataComparator.isEqual(userIdMetadata,userColumn)).thenReturn(true);ColumnMetadata nameMetadata=mock(ColumnMetadata.class);when(tableMetaData.getColumn("date")).thenReturn(nameMetadata);when(nameMetadata.getType()).thenReturn(DataType.timeuuid());when(tableMetaData.getClusteringColumns()).thenReturn(Arrays.asList(nameColumn));when(columnMetaDataComparator.isEqual(nameMetadata,nameColumn)).thenReturn(true);when(tableMetaData.getColumn("name")).thenReturn(columnMetadataForField);when(columnMetadataForField.getType()).thenReturn(DataType.text());validator.validateForEntity(entityMeta,tableMetaData);}
}
