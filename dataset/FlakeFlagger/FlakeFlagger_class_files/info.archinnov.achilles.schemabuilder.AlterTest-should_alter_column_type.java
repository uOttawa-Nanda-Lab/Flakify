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

import static info.archinnov.achilles.schemabuilder.TableOptions.Caching.ROWS_ONLY;
import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.datastax.driver.core.DataType;
import info.archinnov.achilles.schemabuilder.SchemaBuilder;
import info.archinnov.achilles.schemabuilder.TableOptions;

public class AlterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test public void should_alter_column_type() throws Exception{final String built=SchemaBuilder.alterTable("test").alterColumn("name").type(DataType.ascii());assertThat(built).isEqualTo("\n\tALTER TABLE test ALTER name TYPE ascii");}
}
