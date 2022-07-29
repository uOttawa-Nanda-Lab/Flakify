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

import static info.archinnov.achilles.configuration.ConfigurationParameters.INSERT_STRATEGY;
import static info.archinnov.achilles.type.InsertStrategy.ALL_FIELDS;
import static info.archinnov.achilles.type.InsertStrategy.NOT_NULL_FIELDS;
import static org.fest.assertions.api.Assertions.assertThat;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.configuration.ConfigurationParameters;
import info.archinnov.achilles.embedded.CassandraEmbeddedServerBuilder;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.test.integration.entity.CompleteBean;
import info.archinnov.achilles.test.integration.entity.EntityWithNotNullInsertStrategy;

public class InsertStrategyIT {

    private PersistenceManager manager1 = CassandraEmbeddedServerBuilder
            .withEntities(CompleteBean.class, EntityWithNotNullInsertStrategy.class)
            .withKeyspaceName("ALL_FIELDS_INSERT")
            .withAchillesConfigParams(ImmutableMap.<ConfigurationParameters, Object>of(INSERT_STRATEGY, ALL_FIELDS))
            .cleanDataFilesAtStartup(true)
            .buildPersistenceManager();

    private PersistenceManager manager2 = CassandraEmbeddedServerBuilder
            .withEntities(CompleteBean.class)
            .withKeyspaceName("NOT_NULL_FIELDS_INSERT")
            .withAchillesConfigParams(ImmutableMap.<ConfigurationParameters, Object>of(INSERT_STRATEGY, NOT_NULL_FIELDS))
            .cleanDataFilesAtStartup(true)
            .buildPersistenceManager();


    @Test public void should_insert_only_fields_that_are_not_null() throws Exception{Long id=RandomUtils.nextLong();CompleteBean entity=new CompleteBean();entity.setId(id);entity.setName("John");entity.setAge(33L);manager2.persist(entity);entity.setName("Helen");entity.setAge(null);manager2.persist(entity);final CompleteBean found=manager2.find(CompleteBean.class,id);assertThat(found.getName()).isEqualTo("Helen");assertThat(found.getAge()).isEqualTo(33L);}
}
