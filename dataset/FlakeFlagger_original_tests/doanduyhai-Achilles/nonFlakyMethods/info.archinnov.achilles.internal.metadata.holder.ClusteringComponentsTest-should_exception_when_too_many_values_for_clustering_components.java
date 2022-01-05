@Test public void should_exception_when_too_many_values_for_clustering_components() throws Exception {
  clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(String.class,Integer.class,UUID.class),null,null,null,null,null);
  exception.expect(AchillesException.class);
  exception.expectMessage("There should be at most 3 value(s) of clustering component(s) provided for querying on entity 'entityClass'");
  clusteringComponents.validateClusteringComponents("entityClass",Arrays.<Object>asList("name",15L,UUID.randomUUID(),15));
}
