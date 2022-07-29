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
package info.archinnov.achilles.internal.reflection;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.ID;
import static org.fest.assertions.api.Assertions.assertThat;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyType;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

public class ReflectionInvokerTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private ReflectionInvoker invoker = new ReflectionInvoker();

	@Test public void should_instanciate_embedded_id_with_partition_key_using_default_constructor() throws Exception{Long partitionKey=RandomUtils.nextLong();Field userIdField=EmbeddedKey.class.getDeclaredField("userId");PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compFields(userIdField).build();Object actual=invoker.instantiateEmbeddedIdWithPartitionComponents(idMeta,Arrays.<Object>asList(partitionKey));assertThat(actual).isNotNull();EmbeddedKey embeddedKey=(EmbeddedKey)actual;assertThat(embeddedKey.getUserId()).isEqualTo(partitionKey);assertThat(embeddedKey.getName()).isNull();}

	private class Bean {

		private String complicatedAttributeName;

		public String getComplicatedAttributeName() {
			return complicatedAttributeName;
		}

		public void setComplicatedAttributeName(String complicatedAttributeName) {
			this.complicatedAttributeName = complicatedAttributeName;
		}
	}

	@SuppressWarnings("unused")
	private class BeanWithPrimitive {
		private int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}

	public class BeanWithoutPublicConstructor {
		public BeanWithoutPublicConstructor(String name) {

		}
	}
}
