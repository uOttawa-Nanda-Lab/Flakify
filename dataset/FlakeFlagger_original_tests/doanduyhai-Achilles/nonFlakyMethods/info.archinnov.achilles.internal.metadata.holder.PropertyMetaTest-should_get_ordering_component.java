@Test public void should_get_ordering_component() throws Exception {
  PropertyMeta meta=new PropertyMeta();
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(Long.class,String.class),Arrays.asList("age","name"),null,null,null,null);
  EmbeddedIdProperties props=new EmbeddedIdProperties(null,clusteringComponents,null,Arrays.asList("a","b","c"),null,null,null,null);
  meta.setEmbeddedIdProperties(props);
  assertThat(meta.getOrderingComponent()).isEqualTo("age");
}
