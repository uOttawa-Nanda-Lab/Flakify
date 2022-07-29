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

import static info.archinnov.achilles.internal.metadata.holder.EntityMetaBuilder.entityMetaBuilder;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.COUNTER;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.EMBEDDED_ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.parser.entity.Bean;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.Pair;

@RunWith(MockitoJUnitRunner.class)
public class EntityMetaBuilderTest {

    @Mock
    private PropertyMeta idMeta;

    @Test public void should_build_clustered_counter_meta() throws Exception{Map<String, PropertyMeta> propertyMetas=new HashMap<>();PropertyMeta counterMeta=new PropertyMeta();counterMeta.setType(COUNTER);propertyMetas.put("id",idMeta);propertyMetas.put("counter",counterMeta);when(idMeta.type()).thenReturn(EMBEDDED_ID);when(idMeta.<Long>getValueClass()).thenReturn(Long.class);when(idMeta.isEmbeddedId()).thenReturn(true);when(idMeta.getClusteringComponentClasses()).thenReturn(Arrays.<Class<?>>asList(String.class));List<PropertyMeta> eagerMetas=new ArrayList<>();eagerMetas.add(counterMeta);EntityMeta meta=entityMetaBuilder(idMeta).entityClass(CompleteBean.class).className("Bean").columnFamilyName("cfName").propertyMetas(propertyMetas).build();assertThat(meta.isClusteredEntity()).isTrue();assertThat(meta.isClusteredCounter()).isTrue();}

}
