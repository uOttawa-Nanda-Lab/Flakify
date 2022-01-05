@Test public void should_extract_partition_and_clustering_components_for_non_cluster_compound_partition_key() throws Exception {
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),Arrays.asList("id","type"),noFields,noAccessors,noAccessors);
  ClusteringComponents clusteringComponents=new ClusteringComponents(noClasses,noNames,noFields,noAccessors,noAccessors,noClusteringOrder);
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);
  List<Object> components=Arrays.<Object>asList(10L,"type");
  assertThat(props.extractPartitionComponents(components)).containsExactly(10L,"type");
  assertThat(props.extractClusteringComponents(components)).isEmpty();
}
