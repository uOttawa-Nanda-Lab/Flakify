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

import static info.archinnov.achilles.test.integration.entity.ClusteredEntity.TABLE_NAME;
import static info.archinnov.achilles.test.integration.entity.CompleteBeanTestBuilder.builder;
import static org.fest.assertions.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.cassandra.utils.UUIDGen;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import info.archinnov.achilles.counter.AchillesCounter;
import info.archinnov.achilles.internal.proxy.EntityInterceptor;
import info.archinnov.achilles.junit.AchillesTestResource.Steps;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.query.typed.TypedQuery;
import info.archinnov.achilles.test.builders.TweetTestBuilder;
import info.archinnov.achilles.test.integration.AchillesInternalCQLResource;
import info.archinnov.achilles.test.integration.entity.ClusteredEntity;
import info.archinnov.achilles.test.integration.entity.ClusteredEntity.ClusteredKey;
import info.archinnov.achilles.test.integration.entity.ClusteredEntityWithTimeUUID;
import info.archinnov.achilles.test.integration.entity.CompleteBean;
import info.archinnov.achilles.test.integration.entity.Tweet;
import info.archinnov.achilles.type.Counter;
import info.archinnov.achilles.type.CounterBuilder;
import info.archinnov.achilles.type.OptionsBuilder;
import info.archinnov.achilles.type.TypedMap;
import net.sf.cglib.proxy.Factory;

public class QueryIT {

    @Rule
    public AchillesInternalCQLResource resource = new AchillesInternalCQLResource(Steps.AFTER_TEST,
            CompleteBean.class.getSimpleName(), TABLE_NAME, ClusteredEntityWithTimeUUID.TABLE_NAME,
            AchillesCounter.CQL_COUNTER_TABLE);

    private PersistenceManager manager = resource.getPersistenceManager();

    @Test public void should_return_first_raw_entity_for_raw_typed_query_with_simple_select() throws Exception{CompleteBean entity=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").version(CounterBuilder.incr(15L)).buid();manager.persist(entity);String queryString="SELECT id,name,friends FROM CompleteBean LIMIT 3";CompleteBean actual=manager.rawTypedQuery(CompleteBean.class,queryString).getFirst();assertThat(Factory.class.isAssignableFrom(actual.getClass())).isFalse();assertThat(actual.getId()).isEqualTo(entity.getId());assertThat(actual.getName()).isEqualTo(entity.getName());assertThat(actual.getLabel()).isNull();assertThat(actual.getAge()).isNull();assertThat(actual.getFriends()).containsAll(entity.getFriends());assertThat(actual.getFollowers()).isNull();assertThat(actual.getPreferences()).isNull();assertThat(actual.getVersion()).isNull();assertThat(actual.getWelcomeTweet()).isNull();}
}
