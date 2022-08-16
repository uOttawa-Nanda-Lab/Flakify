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

import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_COLLECTION_OR_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_FROM_MAP;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.data.MapEntry.entry;
import static org.mockito.Mockito.*;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.persistence.operations.EntityProxifier;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyChecker;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapWrapperTest {

    private Map<Method, DirtyChecker> dirtyMap;

	private Method setter;

	@Mock
	private PropertyMeta propertyMeta;

	@Mock
	private EntityProxifier proxifier;

	@Mock
	private PersistenceContext context;

	@Before
	public void setUp() throws Exception {
		setter = CompleteBean.class.getDeclaredMethod("setFriends", List.class);
		when(propertyMeta.type()).thenReturn(PropertyType.MAP);
        dirtyMap = new HashMap<>();
	}

	@Test public void should_mark_dirty_when_clear_on_full_map() throws Exception{Map<Integer, String> target=prepareMap();MapWrapper wrapper=prepareMapWrapper(target);wrapper.clear();assertThat(target).isEmpty();DirtyChecker dirtyChecker=dirtyMap.get(setter);assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_COLLECTION_OR_MAP);assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);assertThat(changeSet.getRawMapChanges()).isEmpty();}

	private Map<Integer, String> prepareMap() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "FR");
		map.put(2, "Paris");
		map.put(3, "75014");

		return map;
	}

	private MapWrapper prepareMapWrapper(Map<Integer, String> target) {
		MapWrapper wrapper = new MapWrapper((Map) target);
		wrapper.setDirtyMap(dirtyMap);
		wrapper.setSetter(setter);
		wrapper.setPropertyMeta(propertyMeta);
		wrapper.setProxifier(proxifier);
		return wrapper;
	}
}
