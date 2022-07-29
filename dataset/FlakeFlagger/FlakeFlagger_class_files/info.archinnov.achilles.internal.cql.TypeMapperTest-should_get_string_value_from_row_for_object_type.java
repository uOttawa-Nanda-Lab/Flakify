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
package info.archinnov.achilles.internal.cql;

import static com.datastax.driver.core.DataType.Name.*;
import static info.archinnov.achilles.internal.cql.TypeMapper.*;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import info.archinnov.achilles.internal.metadata.holder.InternalTimeUUID;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.test.mapping.entity.UserBean;
import info.archinnov.achilles.type.Counter;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.datastax.driver.core.Row;

public class TypeMapperTest {

	@Test public void should_get_string_value_from_row_for_object_type() throws Exception{Row row=mock(Row.class);when(row.getString("object")).thenReturn("object_value");Method method=getRowMethod(UserBean.class);assertThat(method.invoke(row,"object")).isEqualTo("object_value");}
}
