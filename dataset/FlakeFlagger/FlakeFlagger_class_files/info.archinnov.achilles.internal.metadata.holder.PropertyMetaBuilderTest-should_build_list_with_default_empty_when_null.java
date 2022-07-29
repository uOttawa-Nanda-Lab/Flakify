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
package info.archinnov.achilles.internal.metadata.holder;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.*;
import static info.archinnov.achilles.type.ConsistencyLevel.*;
import static org.fest.assertions.api.Assertions.assertThat;
import info.archinnov.achilles.internal.metadata.holder.EmbeddedIdProperties;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.internal.metadata.holder.PropertyMetaBuilder;
import info.archinnov.achilles.internal.metadata.transcoding.CompoundTranscoder;
import info.archinnov.achilles.internal.metadata.transcoding.ListTranscoder;
import info.archinnov.achilles.internal.metadata.transcoding.MapTranscoder;
import info.archinnov.achilles.internal.metadata.transcoding.SetTranscoder;
import info.archinnov.achilles.internal.metadata.transcoding.SimpleTranscoder;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.parser.entity.Bean;
import info.archinnov.achilles.test.parser.entity.EmbeddedKey;
import info.archinnov.achilles.type.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

public class PropertyMetaBuilderTest {
	private Method[] accessors = new Method[2];
    private Field field;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		accessors[0] = Bean.class.getDeclaredMethod("getId");
		accessors[1] = Bean.class.getDeclaredMethod("setId", Long.class);
        field = CompleteBean.class.getDeclaredField("id");
	}

	@Test public void should_build_list_with_default_empty_when_null() throws Exception{PropertyMeta built=PropertyMetaBuilder.factory().type(LIST).propertyName("prop").accessors(accessors).objectMapper(objectMapper).emptyCollectionAndMapIfNull(true).build(Void.class,String.class);assertThat(built.type()).isEqualTo(LIST);assertThat(built.getPropertyName()).isEqualTo("prop");assertThat(built.<String>getValueClass()).isEqualTo(String.class);assertThat(built.isEmbeddedId()).isFalse();assertThat(built.nullValueForCollectionAndMap()).isNotNull().isInstanceOf(List.class);assertThat(built.getTranscoder()).isInstanceOf(ListTranscoder.class);}
}
