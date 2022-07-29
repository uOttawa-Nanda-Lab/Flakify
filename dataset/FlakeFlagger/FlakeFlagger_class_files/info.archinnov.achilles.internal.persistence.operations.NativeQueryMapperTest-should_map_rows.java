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
package info.archinnov.achilles.internal.persistence.operations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import info.archinnov.achilles.internal.reflection.RowMethodInvoker;
import info.archinnov.achilles.type.TypedMap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.datastax.driver.core.ColumnDefinitionBuilder;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ColumnDefinitions.Definition;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Row;

@RunWith(MockitoJUnitRunner.class)
public class NativeQueryMapperTest {

	@InjectMocks
	private NativeQueryMapper mapper;

	@Mock
	private RowMethodInvoker cqlRowInvoker;

	@Mock
	private Row row;

	private ColumnDefinitions columnDefs;

	private Definition def1;

	private Definition def2;

	@Test public void should_map_rows() throws Exception{Long id=RandomUtils.nextLong();String name="name";def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","id",DataType.bigint());def2=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","name",DataType.text());columnDefs=ColumnDefinitionBuilder.buildColumnDefinitions(def1,def2);when(row.getColumnDefinitions()).thenReturn(columnDefs);when(cqlRowInvoker.invokeOnRowForType(row,Long.class,"id")).thenReturn(id);when(cqlRowInvoker.invokeOnRowForType(row,String.class,"name")).thenReturn(name);List<TypedMap> result=mapper.mapRows(Arrays.asList(row));verify(cqlRowInvoker).invokeOnRowForType(row,Long.class,"id");verify(cqlRowInvoker).invokeOnRowForType(row,String.class,"name");assertThat(result).hasSize(1);TypedMap line=result.get(0);assertThat(line).hasSize(2);assertThat(line.get("id")).isEqualTo(id);assertThat(line.get("name")).isEqualTo(name);}
}
