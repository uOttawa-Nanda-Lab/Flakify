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
package info.archinnov.achilles.internal.metadata.transcoding;

import static info.archinnov.achilles.internal.metadata.holder.PropertyType.*;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import info.archinnov.achilles.internal.metadata.holder.PropertyMeta;
import info.archinnov.achilles.test.builders.PropertyMetaTestBuilder;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class MapTranscoderTest {

	private MapTranscoder transcoder = new MapTranscoder(mock(ObjectMapper.class));

	@Test public void should_encode_value() throws Exception{PropertyMeta pm=PropertyMetaTestBuilder.keyValueClass(Integer.class,String.class).type(SIMPLE).build();assertThat(transcoder.encode(pm,"value")).isEqualTo("value");}

}
