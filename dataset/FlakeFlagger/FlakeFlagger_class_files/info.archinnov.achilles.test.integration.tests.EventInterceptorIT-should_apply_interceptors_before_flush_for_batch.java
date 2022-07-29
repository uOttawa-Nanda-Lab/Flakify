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
package info.archinnov.achilles.test.integration.tests;

import static info.archinnov.achilles.configuration.ConfigurationParameters.EVENT_INTERCEPTORS;
import static info.archinnov.achilles.configuration.ConfigurationParameters.FORCE_TABLE_CREATION;
import static info.archinnov.achilles.interceptor.Event.POST_LOAD;
import static info.archinnov.achilles.interceptor.Event.POST_PERSIST;
import static info.archinnov.achilles.interceptor.Event.POST_REMOVE;
import static info.archinnov.achilles.interceptor.Event.POST_UPDATE;
import static info.archinnov.achilles.interceptor.Event.PRE_PERSIST;
import static info.archinnov.achilles.interceptor.Event.PRE_REMOVE;
import static info.archinnov.achilles.interceptor.Event.PRE_UPDATE;
import static info.archinnov.achilles.test.integration.entity.CompleteBeanTestBuilder.builder;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.embedded.CassandraEmbeddedServerBuilder;
import info.archinnov.achilles.interceptor.Event;
import info.archinnov.achilles.interceptor.Interceptor;
import info.archinnov.achilles.persistence.Batch;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.persistence.PersistenceManagerFactory;
import info.archinnov.achilles.test.integration.entity.ClusteredEntity;
import info.archinnov.achilles.test.integration.entity.CompleteBean;

public class EventInterceptorIT {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Interceptor<CompleteBean> prePersist = new Interceptor<CompleteBean>() {

        @Override
        public void onEvent(CompleteBean entity) {
            entity.setName("prePersist");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(PRE_PERSIST);
        }
    };

    private Interceptor<CompleteBean> postPersist = new Interceptor<CompleteBean>() {
        @Override
        public void onEvent(CompleteBean entity) {
            entity.setLabel("postPersist");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(POST_PERSIST);

        }
    };

    private Interceptor<CompleteBean> preUpdate = new Interceptor<CompleteBean>() {
        @Override
        public void onEvent(CompleteBean entity) {
            entity.setName("preUpdate");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(PRE_UPDATE);
        }
    };

    private Interceptor<CompleteBean> postUpdate = new Interceptor<CompleteBean>() {
        @Override
        public void onEvent(CompleteBean entity) {
            entity.setLabel("postUpdate");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(POST_UPDATE);
        }
    };

    private Interceptor<CompleteBean> preRemove = new Interceptor<CompleteBean>() {
        @Override
        public void onEvent(CompleteBean entity) {
            entity.setName("preRemove");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(PRE_REMOVE);
        }
    };

    private Interceptor<CompleteBean> postRemove = new Interceptor<CompleteBean>() {
        @Override
        public void onEvent(CompleteBean entity) {
            entity.setLabel("postRemove");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(POST_REMOVE);
        }
    };

    private Interceptor<CompleteBean> postLoad = new Interceptor<CompleteBean>() {
        @Override
        public void onEvent(CompleteBean entity) {
            entity.setLabel("postLoad");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(POST_LOAD);
        }
    };

    private Interceptor<ClusteredEntity> postLoadForClustered = new Interceptor<ClusteredEntity>() {
        @Override
        public void onEvent(ClusteredEntity entity) {
            entity.setValue("postLoad");
        }

        @Override
        public List<Event> events() {
            return Arrays.asList(POST_LOAD);
        }
    };

    private List<Interceptor<CompleteBean>> interceptors = Arrays.asList(prePersist, postPersist, preUpdate,
            postUpdate, preRemove, postLoad);

    private List<Interceptor<CompleteBean>> postRemoveInterceptors = Arrays.asList(postRemove);

    private PersistenceManagerFactory pmf = CassandraEmbeddedServerBuilder
            .withEntityPackages(CompleteBean.class.getPackage().getName()).withKeyspaceName("interceptor_keyspace1")
            .withAchillesConfigParams(ImmutableMap.of(EVENT_INTERCEPTORS, interceptors, FORCE_TABLE_CREATION, true))
            .buildPersistenceManagerFactory();

    private PersistenceManager manager = pmf.createPersistenceManager();
    private Session session = manager.getNativeSession();

    private PersistenceManager manager2 = CassandraEmbeddedServerBuilder
            .withEntities(CompleteBean.class)
            .withKeyspaceName("interceptor_keyspace2")
            .withAchillesConfigParams(ImmutableMap.of(EVENT_INTERCEPTORS, postRemoveInterceptors, FORCE_TABLE_CREATION, true))
            .cleanDataFilesAtStartup(true)
            .buildPersistenceManager();

    private PersistenceManager manager3 = CassandraEmbeddedServerBuilder
            .withEntities(ClusteredEntity.class)
            .withKeyspaceName("interceptor_keyspace3")
            .withAchillesConfigParams(ImmutableMap.of(EVENT_INTERCEPTORS, asList(postLoadForClustered), FORCE_TABLE_CREATION, true))
            .cleanDataFilesAtStartup(true)
            .buildPersistenceManager();

    @Test public void should_apply_interceptors_before_flush_for_batch() throws Exception{final Batch batchingPM=pmf.createBatch();batchingPM.startBatch();CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();batchingPM.persist(entity);assertThat(entity.getName()).isEqualTo("DuyHai");assertThat(entity.getLabel()).isEqualTo("label");batchingPM.endBatch();assertThat(entity.getName()).isEqualTo("prePersist");assertThat(entity.getLabel()).isEqualTo("postPersist");}
}
