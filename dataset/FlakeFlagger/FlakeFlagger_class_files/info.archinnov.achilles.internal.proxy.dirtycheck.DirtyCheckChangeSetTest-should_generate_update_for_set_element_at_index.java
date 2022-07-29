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

package info.archinnov.achilles.internal.proxy.dirtycheck;

import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ADD_TO_MAP;
import static info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet.ElementAtIndex;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.data.MapEntry.entry;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;

@RunWith(MockitoJUnitRunner.class)
public class DirtyCheckChangeSetTest {

    @Mock
    private PropertyMeta pm;

    private DirtyCheckChangeSet changeSet;

    private Update.Conditions conditions;

    @Before
    public void setUp() {
        changeSet = new DirtyCheckChangeSet(pm, ADD_TO_MAP);
        when(pm.getPropertyName()).thenReturn("property");
    }

    @Test public void should_generate_update_for_set_element_at_index() throws Exception{changeSet.listChangeAtIndex=new ElementAtIndex(1,"a");when(pm.encode("a")).thenReturn("a");Object[] vals=changeSet.generateUpdateForSetAtIndexElement(update()).right;assertThat(vals[0]).isEqualTo(1);assertThat(vals[1]).isEqualTo("a");assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property[1]=?;");}

    private Update.Conditions update() {
        conditions = QueryBuilder.update("table").onlyIf();
        return conditions;
    }
}
