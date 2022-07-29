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

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.proxy.EntityInterceptor;
import info.archinnov.achilles.internal.proxy.ProxyClassFactory;
import info.archinnov.achilles.internal.reflection.ObjectInstantiator;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.mapping.entity.UserBean;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.NoOp;

@RunWith(MockitoJUnitRunner.class)
public class EntityProxifierTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private EntityProxifier proxifier;

    @Mock
    private ObjectInstantiator instantiator;

    @Mock
    private ProxyClassFactory factory;

    @Mock
    private EntityInterceptor<CompleteBean> interceptor;

    @Mock
    private PersistenceContext.EntityFacade context;

    @Mock
    private ConfigurationContext configContext;

    @Mock
    private EntityMeta entityMeta;

    @Mock
    private PropertyMeta idMeta;

    @Test public void should_build_proxy_with_all_fields_loaded() throws Exception{long primaryKey=RandomUtils.nextLong();PropertyMeta pm=mock(PropertyMeta.class);Object value=new Object();CompleteBean entity=CompleteBeanTestBuilder.builder().id(primaryKey).name("name").buid();proxifier=spy(proxifier);doReturn(interceptor).when(proxifier).buildInterceptor(eq(context),eq(entity),anySetOf(Method.class));when(context.getEntityMeta()).thenReturn(entityMeta);when(entityMeta.getIdMeta()).thenReturn(idMeta);when(entityMeta.getAllMetas()).thenReturn(Arrays.asList(pm));when(pm.getValueFromField(entity)).thenReturn(value);when(context.getConfigContext()).thenReturn(configContext);when(factory.createProxyClass(entity.getClass(),configContext)).thenReturn((Class)entity.getClass());when(instantiator.instantiate(Mockito.<Class<Factory>>any())).thenReturn(realProxy);Object proxy=proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,context);assertThat(proxy).isNotNull();assertThat(proxy).isInstanceOf(Factory.class);Factory factory=(Factory)proxy;assertThat(factory.getCallbacks()).hasSize(1);assertThat(factory.getCallback(0)).isInstanceOf(EntityInterceptor.class);verify(pm).getValueFromField(entity);verify(pm).setValueToField(realProxy,value);}

    private Factory realProxy = new Factory() {

        @Override
        public Object newInstance(Callback callback) {
            return null;
        }

        @Override
        public Object newInstance(Callback[] callbacks) {
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Object newInstance(Class[] types, Object[] args, Callback[] callbacks) {
            return null;
        }

        @Override
        public Callback getCallback(int index) {
            return interceptor;
        }

        @Override
        public void setCallback(int index, Callback callback) {

        }

        @Override
        public void setCallbacks(Callback[] callbacks) {

        }

        @Override
        public Callback[] getCallbacks() {
            return new Callback[] { interceptor };
        }

    };
}
