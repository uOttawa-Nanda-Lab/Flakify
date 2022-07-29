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

package info.archinnov.achilles.internal.context;

import static info.archinnov.achilles.interceptor.Event.POST_LOAD;
import static info.archinnov.achilles.interceptor.Event.POST_PERSIST;
import static info.archinnov.achilles.interceptor.Event.POST_REMOVE;
import static info.archinnov.achilles.interceptor.Event.POST_UPDATE;
import static info.archinnov.achilles.interceptor.Event.PRE_PERSIST;
import static info.archinnov.achilles.interceptor.Event.PRE_REMOVE;
import static info.archinnov.achilles.interceptor.Event.PRE_UPDATE;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import com.google.common.collect.Sets;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.persistence.operations.EntityInitializer;
import info.archinnov.achilles.internal.persistence.operations.EntityLoader;
import info.archinnov.achilles.internal.persistence.operations.EntityPersister;
import info.archinnov.achilles.internal.persistence.operations.EntityProxifier;
import info.archinnov.achilles.internal.persistence.operations.EntityRefresher;
import info.archinnov.achilles.internal.persistence.operations.EntityUpdater;
import info.archinnov.achilles.internal.reflection.ReflectionInvoker;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.OptionsBuilder;

@RunWith(MockitoJUnitRunner.class)
public class PersistenceManagerFacadeTest {

    private PersistenceContext context;

    private PersistenceContext.PersistenceManagerFacade facade;

    @Mock
    private DaoContext daoContext;

    @Mock
    private AbstractFlushContext flushContext;

    @Mock
    private ConfigurationContext configurationContext;

    @Mock
    private EntityLoader loader;

    @Mock
    private EntityPersister persister;

    @Mock
    private EntityUpdater updater;

    @Mock
    private EntityProxifier proxifier;

    @Mock
    private EntityInitializer initializer;

    @Mock
    private EntityRefresher refresher;

    @Mock
    private ReflectionInvoker invoker;


    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private EntityMeta meta;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private PropertyMeta idMeta;

    private Long primaryKey = RandomUtils.nextLong();

    private CompleteBean entity = CompleteBeanTestBuilder.builder().id(primaryKey).buid();

    @Before
    public void setUp() throws Exception {
        when(meta.getIdMeta()).thenReturn(idMeta);
        when(meta.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
        when(configurationContext.getDefaultWriteConsistencyLevel()).thenReturn(ConsistencyLevel.ONE);

        context = new PersistenceContext(meta, configurationContext, daoContext, flushContext, CompleteBean.class, primaryKey, OptionsBuilder.noOptions());
        facade = context.persistenceManagerFacade;

        Whitebox.setInternalState(context, "initializer", initializer);
        Whitebox.setInternalState(context, "persister", persister);
        Whitebox.setInternalState(context, "proxifier", proxifier);
        Whitebox.setInternalState(context, "refresher", refresher);
        Whitebox.setInternalState(context, "loader", loader);
        Whitebox.setInternalState(context, "updater", updater);
    }

    @Test public void should_update() throws Exception{final CompleteBean rawEntity=new CompleteBean();context.entity=rawEntity;facade.update(entity);InOrder inOrder=Mockito.inOrder(flushContext,updater);inOrder.verify(flushContext).triggerInterceptor(meta,rawEntity,PRE_UPDATE);inOrder.verify(updater).update(context.entityFacade,entity);inOrder.verify(flushContext).flush();inOrder.verify(flushContext).triggerInterceptor(meta,rawEntity,POST_UPDATE);}
}
