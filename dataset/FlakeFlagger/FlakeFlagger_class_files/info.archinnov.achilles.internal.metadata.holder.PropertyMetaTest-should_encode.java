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
package info.archinnov.achilles.internal.metadata.holder;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.*;
import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder;
import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder.Sorting.*;
import static info.archinnov.achilles.type.ConsistencyLevel.QUORUM;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import info.archinnov.achilles.internal.metadata.transcoding.DataTranscoder;
import info.archinnov.achilles.internal.metadata.transcoding.SimpleTranscoder;
import info.archinnov.achilles.internal.reflection.ReflectionInvoker;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

@RunWith(MockitoJUnitRunner.class)
public class PropertyMetaTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	private DataTranscoder transcoder;

	@Mock
	private ReflectionInvoker invoker;

	@Test public void should_encode() throws Exception{PropertyMeta pm=new PropertyMeta();pm.setTranscoder(transcoder);assertThat(pm.encode((Object)null)).isNull();assertThat(pm.encodeKey((Object)null)).isNull();assertThat(pm.encode((List<?>)null)).isNull();assertThat(pm.encode((Set<?>)null)).isNull();assertThat(pm.encode((Map<?, ?>)null)).isNull();assertThat(pm.encodeToComponents((List<?>)null)).isNull();Object value="";List<Object> list=new ArrayList<Object>();Set<Object> set=new HashSet<Object>();Map<Object, Object> map=new HashMap<Object, Object>();when(transcoder.encode(pm,value)).thenReturn(value);when(transcoder.encodeKey(pm,value)).thenReturn(value);when(transcoder.encode(pm,list)).thenReturn(list);when(transcoder.encode(pm,set)).thenReturn(set);when(transcoder.encode(pm,map)).thenReturn(map);when(transcoder.encodeToComponents(pm,list)).thenReturn(list);when(transcoder.encodeToComponents(pm,list)).thenReturn(list);assertThat(pm.encode(value)).isEqualTo(value);assertThat(pm.encodeKey(value)).isEqualTo(value);assertThat(pm.encode(list)).isEqualTo(list);assertThat(pm.encode(set)).isEqualTo(set);assertThat(pm.encode(map)).isEqualTo(map);assertThat(pm.encodeToComponents(list)).isEqualTo(list);assertThat(pm.encodeToComponents(list)).isEqualTo(list);}
}
