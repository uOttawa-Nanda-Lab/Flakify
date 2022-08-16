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

import static org.fest.assertions.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ObjectInstantiatorTest {

    private ObjectInstantiator instantiator = new ObjectInstantiator();

    @Test public void should_instantiate_class_without_public_constructor() throws Exception{BeanWithoutPublicConstructor instance=instantiator.instantiate(BeanWithoutPublicConstructor.class);assertThat(instance).isInstanceOf(BeanWithoutPublicConstructor.class);}

    public class BeanWithoutPublicConstructor {
        public BeanWithoutPublicConstructor(String name) {

        }
    }
}
