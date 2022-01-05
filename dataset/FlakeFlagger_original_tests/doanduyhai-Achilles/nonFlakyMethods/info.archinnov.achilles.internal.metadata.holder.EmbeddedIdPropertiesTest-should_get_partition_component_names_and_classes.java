@Test public void should_get_partition_component_names_and_classes() throws Exception {
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class,String.class),Arrays.asList("id","type"),noFields,noAccessors,noAccessors);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(UUID.class,String.class),Arrays.asList("date","name"),noFields,noAccessors,noAccessors,noClusteringOrder);
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);
  assertThat(props.getPartitionComponentNames()).containsExactly("id","type");
  assertThat(props.getPartitionComponentClasses()).containsExactly(Long.class,String.class);
  assertThat(props.isCompositePartitionKey()).isTrue();
}
