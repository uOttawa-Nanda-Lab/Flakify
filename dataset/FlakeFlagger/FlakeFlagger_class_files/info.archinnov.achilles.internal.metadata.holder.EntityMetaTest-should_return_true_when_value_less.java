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

import static info.archinnov.achilles.interceptor.Event.POST_PERSIST;
import static info.archinnov.achilles.interceptor.Event.PRE_PERSIST;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.COUNTER;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.EMBEDDED_ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static info.archinnov.achilles.type.ConsistencyLevel.ALL;
import static info.archinnov.achilles.type.ConsistencyLevel.ONE;
import static info.archinnov.achilles.type.InsertStrategy.ALL_FIELDS;
import static info.archinnov.achilles.type.InsertStrategy.NOT_NULL_FIELDS;
import static info.archinnov.achilles.type.Options.CASCondition;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.fest.assertions.api.Assertions;
import org.junit.Test;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.interceptor.Event;
import info.archinnov.achilles.interceptor.Interceptor;
import info.archinnov.achilles.internal.reflection.ReflectionInvoker;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.type.IndexCondition;
import info.archinnov.achilles.type.Pair;

public class EntityMetaTest {
    @Test public void should_return_true_when_value_less() throws Exception{EntityMeta entityMeta=new EntityMeta();PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.ID).build();entityMeta.setPropertyMetas(ImmutableMap.of("idMeta",idMeta));assertThat(entityMeta.isValueless()).isTrue();}

    private Interceptor<String> createInterceptor(final Event event) {
        Interceptor<String> interceptor = new Interceptor<String>() {

            @Override
            public void onEvent(String entity) {
            }

            @Override
            public List<Event> events() {
                List<Event> events = new ArrayList<>();
                events.add(event);
                return events;
            }
        };
        return interceptor;
    }

    private Interceptor<CompleteBean> createInterceptorForCompleteBean(final Event event, final long age) {
        Interceptor<CompleteBean> interceptor = new Interceptor<CompleteBean>() {

            @Override
            public void onEvent(CompleteBean entity) {
                entity.setAge(age);
            }

            @Override
            public List<Event> events() {
                return asList(event);
            }
        };
        return interceptor;
    }
}
