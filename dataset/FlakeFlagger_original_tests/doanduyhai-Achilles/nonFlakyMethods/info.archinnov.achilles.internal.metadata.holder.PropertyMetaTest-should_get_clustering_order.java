@Test public void should_get_clustering_order() throws Exception {
  PropertyMeta meta=new PropertyMeta();
  final ClusteringOrder clusteringOrder=new ClusteringOrder("test",DESC);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(Long.class,String.class),Arrays.asList("age","name"),null,null,null,Arrays.asList(clusteringOrder));
  EmbeddedIdProperties props=new EmbeddedIdProperties(null,clusteringComponents,null,Arrays.asList("a","b","c"),null,null,null,null);
  meta.setEmbeddedIdProperties(props);
  assertThat(meta.getClusteringOrders()).containsExactly(clusteringOrder);
}
