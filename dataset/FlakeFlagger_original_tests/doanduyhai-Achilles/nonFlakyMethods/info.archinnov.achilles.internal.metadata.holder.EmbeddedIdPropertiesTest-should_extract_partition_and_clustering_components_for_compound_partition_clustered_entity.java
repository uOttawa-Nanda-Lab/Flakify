@Test public void should_extract_partition_and_clustering_components_for_compound_partition_clustered_entity() throws Exception {
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),Arrays.asList("id","type"),noFields,noAccessors,noAccessors);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(UUID.class,String.class),Arrays.asList("date","name"),noFields,noAccessors,noAccessors,noClusteringOrder);
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);
  UUID date=new UUID(10,10);
  List<Object> components=Arrays.<Object>asList(10L,"type",date,"name");
  assertThat(props.extractPartitionComponents(components)).containsExactly(10L,"type");
  assertThat(props.extractClusteringComponents(components)).containsExactly(date,"name");
}
