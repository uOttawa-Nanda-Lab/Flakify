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

package info.archinnov.achilles.internal.persistence.operations;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.type.Counter;

@RunWith(MockitoJUnitRunner.class)
public class CounterPersisterTest {

    private CounterPersister persister = new CounterPersister();

    @Mock
    private PersistenceContext.EntityFacade context;

    @Mock
    private EntityMeta meta;

    @Mock
    private PropertyMeta counterMeta;

    @Mock
    private PropertyMeta counterMeta2;

    @Mock
    private PropertyMeta counterMeta3;

    private Object entity = new Object();

    @Before
    public void setUp() {
        when(context.getEntityMeta()).thenReturn(meta);
        when(context.getEntity()).thenReturn(entity);
    }

    @Test(expected=IllegalStateException.class) public void should_exception_when_persisting_null_clustered_counters() throws Exception{when(context.getAllCountersMeta()).thenReturn(asList(counterMeta,counterMeta));persister.persistClusteredCounters(context);}

}
