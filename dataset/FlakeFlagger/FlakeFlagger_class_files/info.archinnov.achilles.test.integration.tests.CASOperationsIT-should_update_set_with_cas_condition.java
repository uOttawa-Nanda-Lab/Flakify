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

import static info.archinnov.achilles.listener.CASResultListener.CASResult;
import static info.archinnov.achilles.listener.CASResultListener.CASResult.Operation.INSERT;
import static info.archinnov.achilles.listener.CASResultListener.CASResult.Operation.UPDATE;
import static info.archinnov.achilles.test.integration.entity.CompleteBeanTestBuilder.builder;
import static info.archinnov.achilles.type.ConsistencyLevel.EACH_QUORUM;
import static info.archinnov.achilles.type.ConsistencyLevel.LOCAL_SERIAL;
import static info.archinnov.achilles.type.ConsistencyLevel.ONE;
import static info.archinnov.achilles.type.Options.CASCondition;
import static info.archinnov.achilles.type.OptionsBuilder.casResultListener;
import static info.archinnov.achilles.type.OptionsBuilder.ifConditions;
import static org.fest.assertions.api.Assertions.assertThat;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import com.google.common.collect.ImmutableMap;
import info.archinnov.achilles.exception.AchillesCASException;
import info.archinnov.achilles.junit.AchillesTestResource;
import info.archinnov.achilles.listener.CASResultListener;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.query.cql.NativeQuery;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.CompleteBean;
import info.archinnov.achilles.test.integration.entity.EntityWithEnum;
import info.archinnov.achilles.test.integration.utils.CassandraLogAsserter;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.OptionsBuilder;

public class CASOperationsIT {

    @Rule
    public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(AchillesTestResource.Steps.AFTER_TEST, EntityWithEnum.TABLE_NAME, CompleteBean.TABLE_NAME);

    private PersistenceManager manager = resource.getPersistenceManager();

    private CassandraLogAsserter logAsserter = new CassandraLogAsserter();

    @Test public void should_update_set_with_cas_condition() throws Exception{CompleteBean entity=builder().randomId().name("John").addFollowers("Paul","Andrew").buid();final CompleteBean managed=manager.persist(entity);managed.getFollowers().add("Helen");managed.getFollowers().remove("Paul");manager.update(managed,ifConditions(new CASCondition("name","John")).withTtl(100));final CompleteBean actual=manager.find(CompleteBean.class,entity.getId());assertThat(actual.getFollowers()).containsOnly("Helen","Andrew");}
}
