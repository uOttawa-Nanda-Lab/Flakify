@Test public void should_to_string() throws Exception {
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class),Arrays.asList("id"),noFields,noAccessors,noAccessors);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(UUID.class),Arrays.asList("date"),noFields,noAccessors,noAccessors,noClusteringOrder);
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,noClasses,noNames,noFields,noAccessors,noAccessors,noTimeUUID);
  StringBuilder toString=new StringBuilder();
  toString.append("EmbeddedIdProperties{");
  toString.append("partitionComponents=PartitionComponents{componentClasses=java.lang.Long, componentNames=[id]}, ");
  toString.append("clusteringComponents=ClusteringComponents{componentClasses=java.util.UUID, componentNames=[date]}}");
  assertThat(props.toString()).isEqualTo(toString.toString());
}
