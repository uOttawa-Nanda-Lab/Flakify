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

    @Test public void should_build_meta() throws Exception{Map<String, PropertyMeta> propertyMetas=new HashMap<>();PropertyMeta simpleMeta=new PropertyMeta();simpleMeta.setType(SIMPLE);Method getter=Bean.class.getDeclaredMethod("getName",(Class<?>[])null);simpleMeta.setGetter(getter);Method setter=Bean.class.getDeclaredMethod("setName",String.class);simpleMeta.setSetter(setter);propertyMetas.put("name",simpleMeta);when(idMeta.<Long>getValueClass()).thenReturn(Long.class);List<PropertyMeta> eagerMetas=new ArrayList<>();eagerMetas.add(simpleMeta);EntityMeta meta=entityMetaBuilder(idMeta).entityClass(CompleteBean.class).className("Bean").columnFamilyName("cfName").propertyMetas(propertyMetas).build();assertThat(meta.<CompleteBean>getEntityClass()).isEqualTo(CompleteBean.class);assertThat(meta.getClassName()).isEqualTo("Bean");assertThat(meta.getTableName()).isEqualTo("cfName");assertThat(meta.getIdMeta()).isSameAs(idMeta);assertThat(meta.<Long>getIdClass()).isEqualTo(Long.class);assertThat(meta.getPropertyMetas()).containsKey("name");assertThat(meta.getPropertyMetas()).containsValue(simpleMeta);assertThat(meta.getGetterMetas()).hasSize(1);assertThat(meta.getGetterMetas().containsKey(getter));assertThat(meta.getGetterMetas().get(getter)).isSameAs(simpleMeta);assertThat(meta.getSetterMetas()).hasSize(1);assertThat(meta.getSetterMetas().containsKey(setter));assertThat(meta.getSetterMetas().get(setter)).isSameAs(simpleMeta);}

}
