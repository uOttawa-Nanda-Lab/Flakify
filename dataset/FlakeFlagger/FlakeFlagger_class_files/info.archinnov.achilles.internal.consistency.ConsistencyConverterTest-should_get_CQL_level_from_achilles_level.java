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
package info.archinnov.achilles.internal.consistency;

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;
import info.archinnov.achilles.type.ConsistencyLevel;

public class ConsistencyConverterTest {
    @Test public void should_get_CQL_level_from_achilles_level() throws Exception{assertThat(ConsistencyConverter.getCQLLevel(ConsistencyLevel.EACH_QUORUM)).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);}
}
