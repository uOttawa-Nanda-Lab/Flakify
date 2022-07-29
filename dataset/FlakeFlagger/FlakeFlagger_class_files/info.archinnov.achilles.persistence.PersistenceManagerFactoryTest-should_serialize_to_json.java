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
package info.archinnov.achilles.persistence;

import static info.archinnov.achilles.configuration.ConfigurationParameters.ENTITY_PACKAGES;
import static info.archinnov.achilles.configuration.ConfigurationParameters.FORCE_TABLE_CREATION;
import static info.archinnov.achilles.configuration.ConfigurationParameters.KEYSPACE_NAME;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.configuration.ArgumentExtractor;
import info.archinnov.achilles.configuration.ConfigurationParameters;
import info.archinnov.achilles.interceptor.Interceptor;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.context.DaoContext;
import info.archinnov.achilles.internal.context.PersistenceContextFactory;
import info.archinnov.achilles.internal.context.SchemaContext;
import info.archinnov.achilles.internal.metadata.discovery.AchillesBootstrapper;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.parsing.context.ParsingResult;
import info.archinnov.achilles.internal.proxy.ProxyClassFactory;
import info.archinnov.achilles.internal.utils.ConfigMap;
import info.archinnov.achilles.test.builders.CompleteBeanTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;

@RunWith(MockitoJUnitRunner.class)
public class PersistenceManagerFactoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PersistenceManagerFactory pmf;

    @Mock
    private ConfigurationContext configContext;

    @Mock
    private ArgumentExtractor argumentExtractor;

    @Mock
    private AchillesBootstrapper boostrapper;

    @Mock
    private ProxyClassFactory proxyClassFactory;

    @Mock
    private Cluster cluster;

    @Mock
    private Session session;

    @Mock
    private DaoContext daoContext;

    @Mock
    private ConfigMap configMap;

    @Captor
    private ArgumentCaptor<SchemaContext> contextCaptor;

    @Before
    public void setUp() {
        pmf = new PersistenceManagerFactory(cluster, ImmutableMap.<ConfigurationParameters, Object>of(FORCE_TABLE_CREATION, true));
        pmf.configurationMap = configMap;
        Whitebox.setInternalState(pmf, ArgumentExtractor.class, argumentExtractor);
        Whitebox.setInternalState(pmf, AchillesBootstrapper.class, boostrapper);
        Whitebox.setInternalState(pmf, ProxyClassFactory.class, proxyClassFactory);
    }

    @Test public void should_serialize_to_json() throws Exception{pmf.configContext=configContext;ObjectMapper mapper=new ObjectMapper();mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);when(configContext.getMapperFor(CompleteBean.class)).thenReturn(mapper);CompleteBean entity=CompleteBeanTestBuilder.builder().id(10L).name("name").buid();final String serialized=pmf.jsonSerialize(entity);assertThat(serialized).isEqualTo("{\"id\":10,\"name\":\"name\",\"friends\":[],\"followers\":[],\"preferences\":{}}");}
}
