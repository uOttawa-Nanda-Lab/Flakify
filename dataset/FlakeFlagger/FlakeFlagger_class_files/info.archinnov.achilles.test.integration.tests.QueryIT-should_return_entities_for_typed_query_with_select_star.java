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

    @Test public void should_return_entities_for_typed_query_with_select_star() throws Exception{CompleteBean entity1=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();CompleteBean entity2=builder().randomId().name("John DOO").age(34L).addFriends("qux","twix").addFollowers("Isaac","Lara").addPreference(1,"US").addPreference(2,"NewYork").buid();manager.persist(entity1);manager.persist(entity2);String queryString="SELECT * FROM CompleteBean LIMIT 3";List<CompleteBean> actual=manager.typedQuery(CompleteBean.class,queryString).get();assertThat(actual).hasSize(2);CompleteBean found1=actual.get(0);CompleteBean found2=actual.get(1);Factory factory1=(Factory)found1;@SuppressWarnings("unchecked") EntityInterceptor<CompleteBean> interceptor1=(EntityInterceptor<CompleteBean>)factory1.getCallback(0);CompleteBean target1=(CompleteBean)interceptor1.getTarget();assertThat(target1.getLabel()).isNull();assertThat(target1.getWelcomeTweet()).isNull();Factory factory2=(Factory)found1;@SuppressWarnings("unchecked") EntityInterceptor<CompleteBean> interceptor2=(EntityInterceptor<CompleteBean>)factory2.getCallback(0);CompleteBean target2=(CompleteBean)interceptor2.getTarget();assertThat(target2.getLabel()).isNull();assertThat(target2.getWelcomeTweet()).isNull();if (found1.getId().equals(entity1.getId())){CompleteBean reference=entity1;assertThat(Factory.class.isAssignableFrom(found1.getClass())).isTrue();assertThat(found1.getId()).isEqualTo(reference.getId());assertThat(found1.getName()).isEqualTo(reference.getName());assertThat(found1.getAge()).isEqualTo(reference.getAge());assertThat(found1.getFriends()).containsAll(reference.getFriends());assertThat(found1.getFollowers()).containsAll(reference.getFollowers());assertThat(found1.getPreferences().get(1)).isEqualTo("FR");assertThat(found1.getPreferences().get(2)).isEqualTo("Paris");assertThat(found1.getPreferences().get(3)).isEqualTo("75014");reference=entity2;assertThat(Factory.class.isAssignableFrom(found2.getClass())).isTrue();assertThat(found2.getId()).isEqualTo(reference.getId());assertThat(found2.getName()).isEqualTo(reference.getName());assertThat(found2.getAge()).isEqualTo(reference.getAge());assertThat(found2.getFriends()).containsAll(reference.getFriends());assertThat(found2.getFollowers()).containsAll(reference.getFollowers());assertThat(found2.getPreferences().get(1)).isEqualTo("US");assertThat(found2.getPreferences().get(2)).isEqualTo("NewYork");} else {CompleteBean reference=entity2;assertThat(Factory.class.isAssignableFrom(found1.getClass())).isTrue();assertThat(found1.getId()).isEqualTo(reference.getId());assertThat(found1.getName()).isEqualTo(reference.getName());assertThat(found1.getFriends()).containsAll(reference.getFriends());assertThat(found1.getFollowers()).containsAll(reference.getFollowers());assertThat(found1.getPreferences().get(1)).isEqualTo("US");assertThat(found1.getPreferences().get(2)).isEqualTo("NewYork");reference=entity1;assertThat(Factory.class.isAssignableFrom(found2.getClass())).isTrue();assertThat(found2.getId()).isEqualTo(reference.getId());assertThat(found2.getName()).isEqualTo(reference.getName());assertThat(found2.getFriends()).containsAll(reference.getFriends());assertThat(found2.getFollowers()).containsAll(reference.getFollowers());assertThat(found2.getPreferences().get(1)).isEqualTo("FR");assertThat(found2.getPreferences().get(2)).isEqualTo("Paris");assertThat(found2.getPreferences().get(3)).isEqualTo("75014");}}
}
