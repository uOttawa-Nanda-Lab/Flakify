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

package info.archinnov.achilles.schemabuilder;

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;
import info.archinnov.achilles.schemabuilder.TableOptions;


public class CompressionOptionsTest {

    @Test public void should_create_snappy_compressions_options() throws Exception{final String build=TableOptions.CompressionOptions.snappy().withChunkLengthInKb(128).withCRCCheckChance(0.6D).build();assertThat(build).isEqualTo("{'sstable_compression' : 'SnappyCompressor', 'chunk_length_kb' : 128, 'crc_check_chance' : 0.6}");}
}
