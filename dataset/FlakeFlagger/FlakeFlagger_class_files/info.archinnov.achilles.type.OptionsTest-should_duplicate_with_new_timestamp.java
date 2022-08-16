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
package info.archinnov.achilles.type;

import static info.archinnov.achilles.type.ConsistencyLevel.EACH_QUORUM;
import static info.archinnov.achilles.type.ConsistencyLevel.LOCAL_QUORUM;
import static info.archinnov.achilles.type.Options.CASCondition;
import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;

public class OptionsTest {

    @Test public void should_duplicate_with_new_timestamp() throws Exception{Options options=OptionsBuilder.withConsistency(EACH_QUORUM).withTtl(10).withTimestamp(100L);final Options newOptions=options.duplicateWithNewTimestamp(101L);assertThat(newOptions.getTimestamp().get()).isEqualTo(101L);assertThat(newOptions.getConsistencyLevel().get()).isEqualTo(EACH_QUORUM);}
}
