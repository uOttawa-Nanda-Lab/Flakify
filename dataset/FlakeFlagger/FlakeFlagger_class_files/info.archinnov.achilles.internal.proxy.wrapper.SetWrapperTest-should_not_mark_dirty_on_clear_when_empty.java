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
package info.archinnov.achilles.internal.proxy.wrapper;

import com.google.common.collect.Sets;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.persistence.operations.EntityProxifier;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyChecker;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_COLLECTION_OR_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_SET;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SetWrapperTest {

    private Map<Method, DirtyChecker> dirtyMap;

    private Method setter;

    @Mock
    private PropertyMeta propertyMeta;

    @Mock
    private EntityProxifier proxifier;

    private EntityMeta entityMeta;

    @Before
    public void setUp() throws Exception {
        setter = CompleteBean.class.getDeclaredMethod("setFriends", List.class);
        when(propertyMeta.type()).thenReturn(PropertyType.LIST);

        PropertyMeta idMeta = PropertyMetaTestBuilder.completeBean(Void.class, Long.class).field("id")
                .type(PropertyType.SIMPLE).accessors().build();

        entityMeta = new EntityMeta();
        entityMeta.setIdMeta(idMeta);
        dirtyMap = new HashMap<>();
    }

    @Test
    public void should_not_mark_dirty_on_clear_when_empty() throws Exception {

        Set<Object> target = new HashSet<>();
        SetWrapper wrapper = prepareSetWrapper(target);
        wrapper.clear();

        assertThat(dirtyMap).isEmpty();
    }

    private SetWrapper prepareSetWrapper(Set<Object> target) {
        SetWrapper wrapper = new SetWrapper(target);
        wrapper.setDirtyMap(dirtyMap);
        wrapper.setSetter(setter);
        wrapper.setPropertyMeta(propertyMeta);
        wrapper.setProxifier(proxifier);
        return wrapper;
    }
}
