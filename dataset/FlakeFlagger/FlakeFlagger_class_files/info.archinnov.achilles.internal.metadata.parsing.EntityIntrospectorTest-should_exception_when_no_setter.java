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
package info.archinnov.achilles.internal.metadata.parsing;

import static info.archinnov.achilles.type.ConsistencyLevel.ALL;
import static info.archinnov.achilles.type.ConsistencyLevel.ANY;
import static info.archinnov.achilles.type.ConsistencyLevel.LOCAL_QUORUM;
import static info.archinnov.achilles.type.ConsistencyLevel.ONE;
import static info.archinnov.achilles.type.ConsistencyLevel.THREE;
import static info.archinnov.achilles.type.ConsistencyLevel.TWO;
import static info.archinnov.achilles.type.InsertStrategy.ALL_FIELDS;
import static info.archinnov.achilles.type.InsertStrategy.NOT_NULL_FIELDS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.Consistency;
import info.archinnov.achilles.annotations.Entity;
import info.archinnov.achilles.annotations.Id;
import info.archinnov.achilles.annotations.Strategy;
import info.archinnov.achilles.annotations.TimeUUID;
import info.archinnov.achilles.exception.AchillesBeanMappingException;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.parsing.context.EntityParsingContext;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.parser.entity.BeanWithColumnFamilyName;
import info.archinnov.achilles.test.parser.entity.ChildBean;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.InsertStrategy;
import info.archinnov.achilles.type.Pair;

@RunWith(MockitoJUnitRunner.class)
public class EntityIntrospectorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private EntityMeta entityMeta;

    @Mock
    private PropertyMeta idMeta;

    @Mock
    private PropertyMeta wideMapMeta;

    @Mock
    private Map<Method, PropertyMeta> getterMetas;

    @Mock
    private Map<Method, PropertyMeta> setterMetas;

    @Mock
    private EntityParsingContext parsingContext;

    @Mock
    private ConfigurationContext configContext;

    private final EntityIntrospector introspector = new EntityIntrospector();

    @Test public void should_exception_when_no_setter() throws Exception{class Test {String name;@SuppressWarnings("unused") public String getA(){return name;}}expectedEx.expect(AchillesBeanMappingException.class);expectedEx.expectMessage("The setter for field 'name' of type 'null' does not exist");introspector.findSetter(Test.class,Test.class.getDeclaredField("name"));}

    class Bean {

        private String complicatedAttributeName;

        public String getComplicatedAttributeName() {
            return complicatedAttributeName;
        }

        public void setComplicatedAttributeName(String complicatedAttributeName) {
            this.complicatedAttributeName = complicatedAttributeName;
        }
    }

    @Strategy(insert = InsertStrategy.NOT_NULL_FIELDS)
    class ComplexBean {
        private List<String> friends;

        public List<String> getFriends() {
            return friends;
        }

        public void setFriends(List<String> friends) {
            this.friends = friends;
        }
    }
}
