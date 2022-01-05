@Test public void should_exception_when_null_value_between_clustering_components() throws Exception {
  clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(String.class,Integer.class,UUID.class),null,null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("There should not be any null value between two non-null components of an @EmbeddedId");
  clusteringComponents.validateClusteringComponents("entityClass",Arrays.<Object>asList("name",null,UUID.randomUUID()));
}
