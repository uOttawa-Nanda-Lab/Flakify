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

package info.archinnov.achilles.schemabuilder;

import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder.Sorting.ASC;
import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder.Sorting.DESC;
import static info.archinnov.achilles.schemabuilder.TableOptions.Caching.ROWS_ONLY;
import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.datastax.driver.core.DataType;
import info.archinnov.achilles.schemabuilder.Create;
import info.archinnov.achilles.schemabuilder.SchemaBuilder;
import info.archinnov.achilles.schemabuilder.TableOptions;

public class CreateTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test public void should_create_table_with_composite_partition_key_and_clustering_keys() throws Exception{final String built=SchemaBuilder.createTable("test").addPartitionKey("id1",DataType.bigint()).addPartitionKey("id2",DataType.text()).addClusteringKey("col1",DataType.uuid()).addClusteringKey("col2",DataType.uuid()).addColumn("name",DataType.text()).build();assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id1 bigint,\n\t\t" + "id2 text,\n\t\t" + "col1 uuid,\n\t\t" + "col2 uuid,\n\t\t" + "name text,\n\t\t" + "PRIMARY KEY((id1, id2), col1, col2))");}
}
