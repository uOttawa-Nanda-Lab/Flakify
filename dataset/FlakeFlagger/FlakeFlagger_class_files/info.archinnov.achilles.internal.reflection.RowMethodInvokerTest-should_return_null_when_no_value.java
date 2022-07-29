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
package info.archinnov.achilles.internal.reflection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ColumnDefinitions.Definition;
import com.datastax.driver.core.Row;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;

@RunWith(MockitoJUnitRunner.class)
public class RowMethodInvokerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private RowMethodInvoker invoker = new RowMethodInvoker();

    @Mock
    private Row row;

    @Mock
    private ColumnDefinitions columnDefs;

    @Mock
    private Definition definition;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private PropertyMeta pm;

    private List<String> compNames;

    private List<Class<?>> compClasses;

    @Before
    public void setUp() {

        compNames = new ArrayList<>();
        compClasses = new ArrayList<>();

        when(pm.getPropertyName()).thenReturn("property");
        when(pm.<Integer>getKeyClass()).thenReturn(Integer.class);
        when(pm.<String>getValueClass()).thenReturn(String.class);
        when(row.isNull("property")).thenReturn(false);
        when(pm.getComponentNames()).thenReturn(compNames);
        when(pm.getComponentClasses()).thenReturn(compClasses);
    }

    @Test public void should_return_null_when_no_value() throws Exception{when(pm.type()).thenReturn(PropertyType.SIMPLE);when(row.isNull("property")).thenReturn(true);assertThat(invoker.invokeOnRowForFields(row,pm)).isNull();}
}
