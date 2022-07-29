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

	@Test public void should_map_rows_with_map() throws Exception{Map<BigInteger, String> preferences=new HashMap<BigInteger, String>();def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","preferences",DataType.map(DataType.varint(),DataType.text()));columnDefs=ColumnDefinitionBuilder.buildColumnDefinitions(def1);when(row.getColumnDefinitions()).thenReturn(columnDefs);when(row.getMap("preferences",BigInteger.class,String.class)).thenReturn(preferences);List<TypedMap> result=mapper.mapRows(Arrays.asList(row));assertThat(result).hasSize(1);TypedMap line=result.get(0);assertThat(line).hasSize(1);assertThat(line.get("preferences")).isSameAs(preferences);}
}
