@Test public void should_get_ordering_component() throws Exception {
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class),Arrays.asList("id"),noFields,noAccessors,noAccessors);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(UUID.class,String.class),Arrays.asList("date","name"),noFields,noAccessors,noAccessors,noClusteringOrder);
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);
  assertThat(props.getOrderingComponent()).isEqualTo("date");
}
