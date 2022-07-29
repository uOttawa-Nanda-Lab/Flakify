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

	@SuppressWarnings("rawtypes") @Test public void should_convert_cql_to_java() throws Exception{assertThat((Class)toJavaType(ASCII)).isSameAs(String.class);assertThat((Class)toJavaType(BIGINT)).isSameAs(Long.class);assertThat((Class)toJavaType(BLOB)).isSameAs(ByteBuffer.class);assertThat((Class)toJavaType(BOOLEAN)).isSameAs(Boolean.class);assertThat((Class)toJavaType(COUNTER)).isSameAs(Long.class);assertThat((Class)toJavaType(DECIMAL)).isSameAs(BigDecimal.class);assertThat((Class)toJavaType(DOUBLE)).isSameAs(Double.class);assertThat((Class)toJavaType(FLOAT)).isSameAs(Float.class);assertThat((Class)toJavaType(INET)).isSameAs(InetAddress.class);assertThat((Class)toJavaType(INT)).isSameAs(Integer.class);assertThat((Class)toJavaType(TEXT)).isSameAs(String.class);assertThat((Class)toJavaType(TIMESTAMP)).isSameAs(Date.class);assertThat((Class)toJavaType(UUID)).isSameAs(UUID.class);assertThat((Class)toJavaType(VARCHAR)).isSameAs(String.class);assertThat((Class)toJavaType(VARINT)).isSameAs(BigInteger.class);assertThat((Class)toJavaType(TIMEUUID)).isSameAs(UUID.class);assertThat((Class)toJavaType(LIST)).isSameAs(List.class);assertThat((Class)toJavaType(SET)).isSameAs(Set.class);assertThat((Class)toJavaType(MAP)).isSameAs(Map.class);assertThat((Class)toJavaType(CUSTOM)).isSameAs(ByteBuffer.class);}
}
