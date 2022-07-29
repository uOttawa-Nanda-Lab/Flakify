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

import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder;
import static info.archinnov.achilles.schemabuilder.Create.Options.ClusteringOrder.Sorting.DESC;
import static org.fest.assertions.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Test;

public class EmbeddedIdPropertiesTest {

	private static final List<Class<?>> noClasses = Arrays.asList();
	private static final List<String> noNames = Arrays.asList();
	private static final List<Method> noAccessors = Arrays.asList();
	private static final List<Field> noFields = Arrays.asList();
	private static final List<String> noTimeUUID = Arrays.asList();
	private static final List<ClusteringOrder> noClusteringOrder = Arrays.asList();

	@Test
	public void should_to_string() throws Exception {

		PartitionComponents partitionComponents = new PartitionComponents(Arrays.<Class<?>> asList(Long.class), Arrays.asList("id"),
				noFields, noAccessors, noAccessors);

		ClusteringComponents clusteringComponents = new ClusteringComponents(Arrays.<Class<?>> asList(UUID.class), Arrays.asList("date"),
				noFields, noAccessors, noAccessors, noClusteringOrder);

		EmbeddedIdProperties props = new EmbeddedIdProperties(partitionComponents, clusteringComponents, noClasses, noNames,
				noFields, noAccessors, noAccessors, noTimeUUID);

		StringBuilder toString = new StringBuilder();
		toString.append("EmbeddedIdProperties{");
		toString.append("partitionComponents=PartitionComponents{componentClasses=java.lang.Long, componentNames=[id]}, ");
		toString.append("clusteringComponents=ClusteringComponents{componentClasses=java.util.UUID, componentNames=[date]}}");

		assertThat(props.toString()).isEqualTo(toString.toString());
	}
}
