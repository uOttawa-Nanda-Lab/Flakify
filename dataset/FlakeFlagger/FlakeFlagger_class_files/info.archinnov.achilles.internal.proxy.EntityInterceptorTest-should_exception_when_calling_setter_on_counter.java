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
package info.archinnov.achilles.internal.proxy;

import static com.google.common.collect.Sets.newHashSet;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ASSIGN_VALUE_TO_LIST;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ASSIGN_VALUE_TO_MAP;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.ASSIGN_VALUE_TO_SET;
import static info.archinnov.achilles.internal.persistence.operations.CollectionAndMapChangeType.REMOVE_COLLECTION_OR_MAP;
import static info.archinnov.achilles.test.builders.PropertyMetaTestBuilder.completeBean;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.persistence.operations.CounterLoader;
import info.archinnov.achilles.internal.persistence.operations.EntityLoader;
import info.archinnov.achilles.internal.persistence.operations.InternalCounterImpl;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyCheckChangeSet;
import info.archinnov.achilles.internal.proxy.dirtycheck.DirtyChecker;
import info.archinnov.achilles.internal.proxy.wrapper.ListWrapper;
import info.archinnov.achilles.internal.proxy.wrapper.MapWrapper;
import info.archinnov.achilles.internal.proxy.wrapper.SetWrapper;
import info.archinnov.achilles.internal.reflection.ReflectionInvoker;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.type.Counter;
import net.sf.cglib.proxy.MethodProxy;

@RunWith(MockitoJUnitRunner.class)
public class EntityInterceptorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private EntityInterceptor<CompleteBean> interceptor;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private PersistenceContext.EntityFacade context;

    @Mock
    private EntityLoader loader;

    @Mock
    private CounterLoader counterLoader;

    @Mock
    private ReflectionInvoker invoker;

    @Mock
    private MethodProxy proxy;

    @Mock
    private PropertyMeta pm;

    private Object[] args = new Object[] { };

    private Map<Method, PropertyMeta> getterMetas = new HashMap<>();
    private Map<Method, PropertyMeta> setterMetas = new HashMap<>();
    private Set<Method> alreadyLoaded = new HashSet<>();
    private Map<Method, DirtyChecker> dirtyMap = new HashMap<>();
    private CompleteBean target;
    private Long key = RandomUtils.nextLong();
    private Object rawValue = "raw";
    private PropertyMeta idMeta;

    @Before
    public void setUp() throws Throwable {

        getterMetas.clear();
        setterMetas.clear();

        interceptor.setGetterMetas(getterMetas);
        interceptor.setSetterMetas(setterMetas);

        target = CompleteBeanTestBuilder.builder().id(key).buid();
        interceptor.setTarget(target);
        interceptor.setPrimaryKey(key);
        interceptor.setEntityOperations(context);
        interceptor.setDirtyMap(dirtyMap);
        interceptor.setAlreadyLoaded(alreadyLoaded);

        dirtyMap.clear();
        alreadyLoaded.clear();

        idMeta = completeBean(Void.class, Long.class).field("id").accessors().build();

        interceptor.setIdGetter(idMeta.getGetter());
        interceptor.setIdSetter(idMeta.getSetter());

        Whitebox.setInternalState(interceptor, "loader", loader);
        Whitebox.setInternalState(interceptor, "counterLoader", counterLoader);
        Whitebox.setInternalState(interceptor, "invoker", invoker);
    }

    @Test public void should_exception_when_calling_setter_on_counter() throws Throwable{PropertyMeta propertyMeta=completeBean(Void.class,Counter.class).field("count").accessors().type(PropertyType.COUNTER).build();args=new Object[]{null};setterMetas.put(propertyMeta.getGetter(),propertyMeta);exception.expect(UnsupportedOperationException.class);exception.expectMessage("Cannot set value directly to a Counter type. Please call the getter first to get handle on the wrapper");interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);}

}
