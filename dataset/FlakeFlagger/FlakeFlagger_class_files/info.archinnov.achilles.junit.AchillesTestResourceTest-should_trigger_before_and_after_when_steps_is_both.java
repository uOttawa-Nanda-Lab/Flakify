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
package info.archinnov.achilles.junit;

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.junit.AchillesTestResource.Steps;

@RunWith(MockitoJUnitRunner.class)
public class AchillesTestResourceTest {

    private AchillesTestResource resource;

    @Test public void should_trigger_before_and_after_when_steps_is_both() throws Throwable{final StringBuilder witness=new StringBuilder();resource=new AchillesTestResource(Steps.BOTH,"table"){@Override protected void truncateTables(){witness.append("called");}};resource.before();assertThat(witness.toString()).isEqualTo("called");witness.delete(0,witness.length());resource.after();assertThat(witness.toString()).isEqualTo("called");}
}
