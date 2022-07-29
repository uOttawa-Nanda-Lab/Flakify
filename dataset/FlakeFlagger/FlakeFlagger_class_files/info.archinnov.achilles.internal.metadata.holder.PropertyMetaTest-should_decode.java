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

	@Test public void should_decode() throws Exception{PropertyMeta pm=new PropertyMeta();pm.setTranscoder(transcoder);assertThat(pm.decode((Object)null)).isNull();assertThat(pm.decodeKey((Object)null)).isNull();assertThat(pm.decode((List<?>)null)).isNull();assertThat(pm.decode((Set<?>)null)).isNull();assertThat(pm.decode((Map<?, ?>)null)).isNull();assertThat(pm.decodeFromComponents((List<?>)null)).isNull();Object value="";List<Object> list=new ArrayList<Object>();Set<Object> set=new HashSet<Object>();Map<Object, Object> map=new HashMap<Object, Object>();when(transcoder.decode(pm,value)).thenReturn(value);when(transcoder.decodeKey(pm,value)).thenReturn(value);when(transcoder.decode(pm,list)).thenReturn(list);when(transcoder.decode(pm,set)).thenReturn(set);when(transcoder.decode(pm,map)).thenReturn(map);when(transcoder.decodeFromComponents(pm,list)).thenReturn(list);assertThat(pm.decode(value)).isEqualTo(value);assertThat(pm.decodeKey(value)).isEqualTo(value);assertThat(pm.decode(list)).isEqualTo(list);assertThat(pm.decode(set)).isEqualTo(set);assertThat(pm.decode(map)).isEqualTo(map);assertThat(pm.decodeFromComponents(list)).isEqualTo(list);}
}
