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

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CounterBuilderTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test public void should_be_able_to_serialize_and_deserialize_counter_impl() throws Exception{ObjectMapper mapper=new ObjectMapper();Counter counter=CounterBuilder.incr(11L);String serialized=mapper.writeValueAsString(counter);assertThat(serialized).isEqualTo("\"11\"");Counter deserialized=mapper.readValue(serialized,Counter.class);assertThat(deserialized.get()).isEqualTo(11L);assertThat(mapper.writeValueAsString(CounterBuilder.incr(0))).isEqualTo("\"0\"");}

    private static class TestCounter implements Counter {

        private Long value;

        @Override
        public Long get() {
            return value;
        }

        @Override
        public void incr() {
            value++;
        }

        @Override
        public void incr(long increment) {
            value += increment;
        }

        @Override
        public void decr() {
            value--;
        }

        @Override
        public void decr(long decrement) {
            value -= decrement;
        }

    }
}
