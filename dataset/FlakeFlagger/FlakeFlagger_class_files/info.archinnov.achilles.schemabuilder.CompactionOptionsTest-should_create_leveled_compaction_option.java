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

public class CompactionOptionsTest {

    @Test public void should_create_leveled_compaction_option() throws Exception{final String build=TableOptions.CompactionOptions.leveledStrategy().ssTableSizeInMB(160).enableAutoCompaction(true).maxThreshold(5).tombstoneCompactionIntervalInDay(3).tombstoneThreshold(0.7).build();assertThat(build).isEqualTo("{'class' : 'LeveledCompactionStrategy', 'enabled' : true, 'max_threshold' : 5, 'tombstone_compaction_interval' : 3, 'tombstone_threshold' : 0.7, 'sstable_size_in_mb' : 160}");}
}
