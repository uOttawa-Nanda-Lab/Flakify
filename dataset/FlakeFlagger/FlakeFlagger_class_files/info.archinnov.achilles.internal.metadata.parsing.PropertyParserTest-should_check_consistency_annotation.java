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

import static info.archinnov.achilles.internal.metadata.parsing.PropertyParser.isAssignableFromNativeType;
import static info.archinnov.achilles.internal.metadata.parsing.PropertyParser.isSupportedNativeType;
import static info.archinnov.achilles.internal.metadata.parsing.PropertyParser.isSupportedType;
import static info.archinnov.achilles.type.ConsistencyLevel.ALL;
import static info.archinnov.achilles.type.ConsistencyLevel.ANY;
import static info.archinnov.achilles.type.ConsistencyLevel.ONE;
import static info.archinnov.achilles.type.ConsistencyLevel.THREE;
import static info.archinnov.achilles.type.ConsistencyLevel.TWO;
import static org.fest.assertions.api.Assertions.assertThat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.Consistency;
import info.archinnov.achilles.annotations.EmbeddedId;
import info.archinnov.achilles.annotations.EmptyCollectionIfNull;
import info.archinnov.achilles.annotations.Id;
import info.archinnov.achilles.annotations.Index;
import info.archinnov.achilles.annotations.TimeUUID;
import info.archinnov.achilles.exception.AchillesBeanMappingException;
import info.archinnov.achilles.interceptor.Event;
import info.archinnov.achilles.interceptor.Interceptor;
import info.archinnov.achilles.internal.context.ConfigurationContext;
import info.archinnov.achilles.internal.metadata.holder.EmbeddedIdProperties;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.internal.metadata.parsing.context.EntityParsingContext;
import info.archinnov.achilles.internal.metadata.parsing.context.PropertyParsingContext;
import info.archinnov.achilles.internal.reflection.ReflectionInvoker;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;
import info.archinnov.achilles.type.ConsistencyLevel;
import info.archinnov.achilles.type.Counter;
import info.archinnov.achilles.type.Pair;

@RunWith(MockitoJUnitRunner.class)
public class PropertyParserTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private PropertyParser parser = new PropertyParser();

    @Mock
    private ReflectionInvoker invoker;

    @Mock
    private List<Method> componentGetters;

    private EntityParsingContext entityContext;
    private ConfigurationContext configContext;

    @Before
    public void setUp() {
        configContext = new ConfigurationContext();
        configContext.setDefaultReadConsistencyLevel(ConsistencyLevel.ONE);
        configContext.setDefaultWriteConsistencyLevel(ConsistencyLevel.ALL);
    }

    @Test public void should_check_consistency_annotation() throws Exception{class Test {@Consistency private String consistency;}Field field=Test.class.getDeclaredField("consistency");assertThat(parser.hasConsistencyAnnotation(field)).isTrue();}

    private Interceptor<Long> longInterceptor = new Interceptor<Long>() {
        @Override
        public void onEvent(Long entity) {

        }

        @Override
        public List<Event> events() {
            return null;
        }
    };

    private <T> PropertyParsingContext newContext(Class<T> entityClass, Field field) {
        entityContext = new EntityParsingContext(configContext, entityClass);

        return entityContext.newPropertyContext(field);
    }

}
