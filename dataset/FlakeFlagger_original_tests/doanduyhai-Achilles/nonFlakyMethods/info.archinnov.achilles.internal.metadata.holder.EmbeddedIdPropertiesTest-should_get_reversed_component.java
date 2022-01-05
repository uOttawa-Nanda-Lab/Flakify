@Test public void should_get_reversed_component() throws Exception {
  final ClusteringOrder clusteringOrder=new ClusteringOrder("col",DESC);
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class),Arrays.asList("id"),noFields,noAccessors,noAccessors);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(UUID.class,String.class),Arrays.asList("date","name"),noFields,noAccessors,noAccessors,Arrays.asList(clusteringOrder));
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);
  assertThat(props.getCluseringOrders()).containsExactly(clusteringOrder);
}
