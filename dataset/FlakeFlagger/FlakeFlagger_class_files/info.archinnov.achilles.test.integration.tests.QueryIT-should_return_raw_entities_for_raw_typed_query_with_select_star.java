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

    @Test public void should_return_raw_entities_for_raw_typed_query_with_select_star() throws Exception{Counter counter1=CounterBuilder.incr(15L);CompleteBean entity1=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").version(counter1).buid();Counter counter2=CounterBuilder.incr(17L);CompleteBean entity2=builder().randomId().name("John DOO").age(34L).addFriends("qux","twix").addFollowers("Isaac","Lara").addPreference(1,"US").addPreference(2,"NewYork").version(counter2).buid();manager.persist(entity1);manager.persist(entity2);String queryString="SELECT * FROM CompleteBean LIMIT :lim";List<CompleteBean> actual=manager.rawTypedQuery(CompleteBean.class,queryString,3).get();assertThat(actual).hasSize(2);CompleteBean found1=actual.get(0);CompleteBean found2=actual.get(1);if (found1.getId().equals(entity1.getId())){CompleteBean reference=entity1;assertThat(Factory.class.isAssignableFrom(found1.getClass())).isFalse();assertThat(found1.getId()).isEqualTo(reference.getId());assertThat(found1.getName()).isEqualTo(reference.getName());assertThat(found1.getAge()).isEqualTo(reference.getAge());assertThat(found1.getFriends()).containsAll(reference.getFriends());assertThat(found1.getFollowers()).containsAll(reference.getFollowers());assertThat(found1.getPreferences().get(1)).isEqualTo("FR");assertThat(found1.getPreferences().get(2)).isEqualTo("Paris");assertThat(found1.getPreferences().get(3)).isEqualTo("75014");assertThat(found1.getVersion()).isNull();reference=entity2;assertThat(Factory.class.isAssignableFrom(found2.getClass())).isFalse();assertThat(found2.getId()).isEqualTo(reference.getId());assertThat(found2.getName()).isEqualTo(reference.getName());assertThat(found2.getAge()).isEqualTo(reference.getAge());assertThat(found2.getFriends()).containsAll(reference.getFriends());assertThat(found2.getFollowers()).containsAll(reference.getFollowers());assertThat(found2.getPreferences().get(1)).isEqualTo("US");assertThat(found2.getPreferences().get(2)).isEqualTo("NewYork");assertThat(found2.getVersion()).isNull();} else {CompleteBean reference=entity2;assertThat(Factory.class.isAssignableFrom(found1.getClass())).isFalse();assertThat(found1.getId()).isEqualTo(reference.getId());assertThat(found1.getName()).isEqualTo(reference.getName());assertThat(found1.getFriends()).containsAll(reference.getFriends());assertThat(found1.getFollowers()).containsAll(reference.getFollowers());assertThat(found1.getPreferences().get(1)).isEqualTo("US");assertThat(found1.getPreferences().get(2)).isEqualTo("NewYork");assertThat(found1.getVersion()).isNull();reference=entity1;assertThat(Factory.class.isAssignableFrom(found2.getClass())).isFalse();assertThat(found2.getId()).isEqualTo(reference.getId());assertThat(found2.getName()).isEqualTo(reference.getName());assertThat(found2.getFriends()).containsAll(reference.getFriends());assertThat(found2.getFollowers()).containsAll(reference.getFollowers());assertThat(found2.getPreferences().get(1)).isEqualTo("FR");assertThat(found2.getPreferences().get(2)).isEqualTo("Paris");assertThat(found2.getPreferences().get(3)).isEqualTo("75014");assertThat(found2.getVersion()).isNull();}}
}
