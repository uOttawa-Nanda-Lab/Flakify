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

import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import info.archinnov.achilles.exception.AchillesException;
import info.archinnov.achilles.internal.metadata.holder.PartitionComponents;

@RunWith(MockitoJUnitRunner.class)
public class PartitionComponentsTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private PartitionComponents partitionComponents;

	@Test public void should_exception_when_partition_component_count_doest_not_match() throws Exception{partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),null,null,null,null);exception.expect(AchillesException.class);exception.expectMessage("There should be exactly '2' partition components for querying on entity 'entityClass'");partitionComponents.validatePartitionComponents("entityClass",Arrays.<Object>asList(11L,"test",11));}
}
