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

	@Test public void should_extract_partition_and_clustering_components_for_compound_partition_clustered_entity() throws Exception{PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),Arrays.asList("id","type"),noFields,noAccessors,noAccessors);ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(UUID.class,String.class),Arrays.asList("date","name"),noFields,noAccessors,noAccessors,noClusteringOrder);EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);UUID date=new UUID(10,10);List<Object> components=Arrays.<Object>asList(10L,"type",date,"name");assertThat(props.extractPartitionComponents(components)).containsExactly(10L,"type");assertThat(props.extractClusteringComponents(components)).containsExactly(date,"name");}
}
