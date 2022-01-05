@Test public void should_get_clustering_component_names_and_classes() throws Exception {
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class),Arrays.asList("id"),noFields,noAccessors,noAccessors);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(UUID.class,String.class),Arrays.asList("date","name"),noFields,noAccessors,noAccessors,noClusteringOrder);
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);
  assertThat(props.getClusteringComponentNames()).containsExactly("date","name");
  assertThat(props.getClusteringComponentClasses()).containsExactly(UUID.class,String.class);
  assertThat(props.isClustered()).isTrue();
}
