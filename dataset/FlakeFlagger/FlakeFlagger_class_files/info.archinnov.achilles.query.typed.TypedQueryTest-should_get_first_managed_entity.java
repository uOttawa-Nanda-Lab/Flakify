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
package info.archinnov.achilles.query.typed;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.ID;
import static info.archinnov.achilles.internal.metadata.holder.PropertyType.SIMPLE;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import com.datastax.driver.core.Row;
import info.archinnov.achilles.interceptor.Event;
import info.archinnov.achilles.internal.context.DaoContext;
import info.archinnov.achilles.internal.context.PersistenceContext;
import info.archinnov.achilles.internal.context.PersistenceContextFactory;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.persistence.operations.EntityMapper;
import info.archinnov.achilles.internal.persistence.operations.EntityProxifier;
import info.archinnov.achilles.internal.statement.wrapper.AbstractStatementWrapper;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;

@RunWith(MockitoJUnitRunner.class)
public class TypedQueryTest {

    private TypedQuery<CompleteBean> builder;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DaoContext daoContext;

    @Mock
    private EntityMapper mapper;

    @Mock
    private EntityProxifier proxifier;

    @Mock
    private PersistenceContextFactory contextFactory;

    @Mock
    private PersistenceContext context;

    @Mock
    private PersistenceContext.EntityFacade entityFacade;

    @Mock
    private Row row;

    @Mock
    private EntityMeta meta;

    private Class<CompleteBean> entityClass = CompleteBean.class;

    private CompleteBean entity = new CompleteBean();

    @Before
    public void setUp() {
        when(context.getEntityFacade()).thenReturn(entityFacade);
    }

    @Test public void should_get_first_managed_entity() throws Exception{PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.ID).accessors().build();PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).accessors().build();EntityMeta meta=buildEntityMeta(idMeta,nameMeta);String queryString="select id from test";initBuilder(queryString,meta,meta.getPropertyMetas(),true);when(daoContext.execute(any(AbstractStatementWrapper.class)).one()).thenReturn(row);when(mapper.mapRowToEntityWithPrimaryKey(eq(meta),eq(row),Mockito.<Map<String, PropertyMeta>>any(),eq(true))).thenReturn(entity);when(contextFactory.newContext(entity)).thenReturn(context);when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,entityFacade)).thenReturn(entity);CompleteBean actual=builder.getFirst();assertThat(actual).isSameAs(entity);verify(meta).intercept(entity,Event.POST_LOAD);}

    private EntityMeta buildEntityMeta(PropertyMeta... pms) {
        EntityMeta meta = mock(EntityMeta.class);
        Map<String, PropertyMeta> propertyMetas = new HashMap<>();
        for (PropertyMeta pm : pms) {
            propertyMetas.put(pm.getPropertyName(), pm);
        }

        when(meta.getPropertyMetas()).thenReturn(propertyMetas);
        return meta;
    }

    private void initBuilder(String queryString, EntityMeta meta, Map<String, PropertyMeta> propertyMetas,
            boolean managed) {
        builder = new TypedQuery<>(entityClass, daoContext, queryString, meta, contextFactory, managed, true, new Object[] { "a" });

        Whitebox.setInternalState(builder, String.class, queryString);
        Whitebox.setInternalState(builder, Map.class, propertyMetas);
        Whitebox.setInternalState(builder, EntityMapper.class, mapper);
        Whitebox.setInternalState(builder, PersistenceContextFactory.class, contextFactory);
        Whitebox.setInternalState(builder, EntityProxifier.class, proxifier);
    }
}
