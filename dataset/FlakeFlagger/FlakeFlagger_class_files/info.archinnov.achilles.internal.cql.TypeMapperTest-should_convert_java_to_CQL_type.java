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

	@Test public void should_convert_java_to_CQL_type() throws Exception{assertThat(toCQLType(String.class)).isSameAs(TEXT);assertThat(toCQLType(Long.class)).isSameAs(BIGINT);assertThat(toCQLType(long.class)).isSameAs(BIGINT);assertThat(toCQLType(ByteBuffer.class)).isSameAs(BLOB);assertThat(toCQLType(Boolean.class)).isSameAs(BOOLEAN);assertThat(toCQLType(boolean.class)).isSameAs(BOOLEAN);assertThat(toCQLType(BigDecimal.class)).isSameAs(DECIMAL);assertThat(toCQLType(Double.class)).isSameAs(DOUBLE);assertThat(toCQLType(double.class)).isSameAs(DOUBLE);assertThat(toCQLType(Float.class)).isSameAs(FLOAT);assertThat(toCQLType(float.class)).isSameAs(FLOAT);assertThat(toCQLType(InetAddress.class)).isSameAs(INET);assertThat(toCQLType(Integer.class)).isSameAs(INT);assertThat(toCQLType(int.class)).isSameAs(INT);assertThat(toCQLType(BigInteger.class)).isSameAs(VARINT);assertThat(toCQLType(Date.class)).isSameAs(TIMESTAMP);assertThat(toCQLType(UUID.class)).isSameAs(UUID);assertThat(toCQLType(List.class)).isSameAs(LIST);assertThat(toCQLType(Set.class)).isSameAs(SET);assertThat(toCQLType(Map.class)).isSameAs(MAP);assertThat(toCQLType(Object.class)).isSameAs(TEXT);assertThat(toCQLType(UserBean.class)).isSameAs(TEXT);assertThat(toCQLType(Counter.class)).isSameAs(COUNTER);assertThat(toCQLType(InternalTimeUUID.class)).isSameAs(TIMEUUID);}
}
